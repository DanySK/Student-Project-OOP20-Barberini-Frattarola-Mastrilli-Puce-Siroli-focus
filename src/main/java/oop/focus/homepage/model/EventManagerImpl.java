package oop.focus.homepage.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.finance.model.Repetition;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class adds new methods to manage events.
 * For example for search for an event by a specific date.
 */
public class EventManagerImpl implements EventManager {

    private final Dao<Event> events;
    private final TimeProperty time;

    /**
     * This is the class constructor.
     * @param dsi is the DataSource.
     */
    public EventManagerImpl(final DataSource dsi) {
        this.time = new TimePropertyImpl();
        this.events = dsi.getEvents();
    }

    public final void addEvent(final Event event) {
        if (this.time.getValidity(event) && this.time.getHourDuration(event) && this.getAnswer(event) || !this.isAdequate(event) || !this.time.getHourDuration(event)) {
            if (!this.events.getAll().contains(event)) {
                try {
                    this.events.save(event);
                } catch (DaoAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public final void addEventsSet(final Set<Event> eventsSet) {
        for (final Event event : eventsSet) {
            this.addEvent(event);
        }
    }

    public final boolean checkEmptyJourney(final LocalDateTime date) {
        return this.takeOnly(this.findByDate(date.toLocalDate())).isEmpty();
    }


    public final boolean emptyFromNowOn(LocalDateTime time){
        List<Event> eventList = this.takeOnly(this.findByDate(LocalDateTime.now().toLocalDate()));
        for (Event event : eventList){
            if (event.getStartDate().isEqual(event.getEndDate())){
                if(event.getStartHour().isAfter(time.toLocalTime()) || event.getEndHour().isAfter(time.toLocalTime())){
                    return false;
                }
            } else {
                if(event.getStart().isAfter(time) || event.getEnd().isAfter(time)) {
                    return false;
                }
            }
        }
        return true;
    }

    public final List<Event> findByDate(final LocalDate date) {
       return this.events.getAll().stream().filter(e -> {
            return e.getStartDate().equals(date) || e.getEndDate().equals(date) 
            || e.getStartDate().isBefore(date) && e.getEndDate().isAfter(date);
        }).filter(e -> !this.isAdequate(e)).collect(Collectors.toList());

    }

    public final Set<Event> findByHour(final LocalTime hour) {
        return this.events.getAll().stream().filter(e -> e.getStartHour().equals(hour)).collect(Collectors.toSet());
    }

    public final Set<Event> findByName(final String name) {
        return this.events.getAll().stream().filter(e -> e.getName().equals(name)).collect(Collectors.toSet());
    }

    public final void generateRepeatedEvents(final LocalDate date) {
        this.generateListOfNextEvent(date).forEach(this::addEvent);
    }

    public final List<Event> generateListOfNextEvent(final LocalDate date) {
         return this.events.getAll().stream()
               .flatMap(e -> this.generateNext(e, date).stream()).collect(Collectors.toList());
    }

   /**
    * This method is used from the generateListOfNextEvent to generate the next event.
    * @param event is the event to find the next repeat date.
    * @param date is the date on which we take events.
    * @return a list of event that repeat them self.
    */
    private List<Event> generateNext(final Event event, final LocalDate date) {
        if (event.getRipetition().equals(Repetition.ONCE) || new LocalDate(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth())
               .isBefore(new LocalDate(event.getNextRenewal().getStartDate()))) {
           return new ArrayList<>();
        }
        event.stopRepeat();
        final var newEvent = event.getNextRenewal();
        return Stream.concat(List.of(newEvent).stream(),
               this.generateNext(newEvent, date).stream()).collect(Collectors.toList());
    }

    public final boolean getAnswer(final Event event) {
        if (event.getStartDate().isEqual(event.getEndDate())) {
            return this.time.areCompatibleEquals(event, this.orderByHour(this.takeOnly(this.findByDate(event.getStartDate()))));
        } else {
            return this.time.areCompatibleDifferent(event, this.orderByHour(this.takeOnly(this.findByDate(event.getStartDate()))), this.takeOnly(this.orderByHour(this.findByDate(event.getEndDate()))));
        }
    }

    public final LocalTime getClosestEvent(final LocalDateTime date) {
        return this.takeOnly(this.orderByHour(this.findByDate(date.toLocalDate()))).stream().filter(e -> e.getStartHour().isAfter(date.toLocalTime())).findFirst().get().getStartHour();
    }

    public final Set<Event> getAll() {
        return this.events.getAll();
    }

    public final Set<Event> getDailyEvents() {
        return this.events.getAll().stream().filter(e -> !this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toSet());
    }

    public final Set<Event> getEvents() {
        return this.events.getAll().stream().filter(e -> {
            return this.time.getHourDuration(e) && !this.isAdequate(e);
        }).collect(Collectors.toSet());
    }

    public final Set<Event> getEventsWithDuration() {
        return this.getEvents().stream().filter(e -> this.time.getMinEventTime(e)).collect(Collectors.toSet());
    }

    public final List<Event> getHotKeyEvents() {
        return this.events.getAll().stream().filter(e -> this.isAdequate(e)).collect(Collectors.toList());
    }

    /**
     * This method is used to not accept events that were saved when a hot key was clicked.
     * @param event is the event on which to check.
     * @return true if the event was saved after a hot key has been clicked false otherwise.
     */
    private boolean isAdequate(final Event event) {
        return event.getStartHour().getHourOfDay() == event.getEndHour().getHourOfDay() 
        && event.getStartHour().getMinuteOfHour() == event.getEndHour().getMinuteOfHour()
        && event.getStartHour().getSecondOfMinute() == event.getEndHour().getSecondOfMinute();
    }

    public final List<Event> orderByHour(final List<Event> eventsList) {
        eventsList.sort(Comparator.comparing(Event::getEnd));
        return eventsList;
    }

    public final void removeAll() {
        for (final Event e : this.events.getAll()) {
            this.removeEvent(e);
        }
    }

    public final void removeEvent(final Event event) {
        try {
            this.events.delete(event);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    public final List<Event> takeOnly(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toList());
    }

    public final List<Event> takeOnlyDailyEvent(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> !this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toList());
    }

    public final boolean timerCanStart(final LocalDateTime date) {
        for (final Event e : this.takeOnly(this.findByDate(date.toLocalDate()))) {
            if (this.time.getStart(date, e)) {
                return false;
            }
        }
        return true;
    }

}
