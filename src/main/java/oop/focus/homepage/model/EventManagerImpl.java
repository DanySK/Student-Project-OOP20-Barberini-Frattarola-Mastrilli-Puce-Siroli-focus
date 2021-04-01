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

    /**
     * This method is used to save a new event.
     * @param event is the event that must be added to events list.
     */
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

    /**
     * This method is used to add new events.
     * @param eventsSet is an event list that must be added to events list.
     */
    public final void addEventsSet(final Set<Event> eventsSet) {
        for (final Event event : eventsSet) {
            this.addEvent(event);
        }
    }

    /**
     * This method is utilize from timer to check if the journey is empty(there aren't events).
     * @param date is the timer date and hour of start.
     * @return true if the journey is empty , false otherwise.
     */
    public final boolean checkEmptyJourney(final LocalDateTime date) {
        return this.takeOnly(this.findByDate(date.toLocalDate())).isEmpty();
    }

    /**
     * This method is used to find the events events that take place on a certain date.
     * @param date is the date on which to search for events.
     * @return a list of events taking place on that particular date.
     */
    public final List<Event> findByDate(final LocalDate date) {
       return this.events.getAll().stream().filter(e -> {
            return e.getStartDate().equals(date) || e.getEndDate().equals(date) 
            || e.getStartDate().isBefore(date) && e.getEndDate().isAfter(date);
        }).filter(e -> !this.isAdequate(e)).collect(Collectors.toList());

    }

    /**
     * This method is used to find the events that have a specific start hour.
     * @param hour is the start hour on which to search for events.
     * @return a list of events with the hour parameter as start hour.
     */
    public final Set<Event> findByHour(final LocalTime hour) {
        return this.events.getAll().stream().filter(e -> e.getStartHour().equals(hour)).collect(Collectors.toSet());
    }

    /**
     * This method is used to find the events that have a specific name.
     * @param name is the name on which to search for events.
     * @return a list of events with the name parameter as name.
     */
    public final Set<Event> findByName(final String name) {
        return this.events.getAll().stream().filter(e -> e.getName().equals(name)).collect(Collectors.toSet());
    }

    /**
    * This method is used to generate the next events that repeats.
    * @param date is the date on which we take events.
    */
    public final void generateRepeatedEvents(final LocalDate date) {
        this.generateListOfNextEvent(date).forEach(this::addEvent);
    }

   /**
    * This method is used to generate the next events that repeats.
    * @param date is the date on which we take events.
    * @return the list of the events that have to be repeated.
    */
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

    /**
     * This method is used to add a new events only if this is possible.
     * @param event is the event that must be add.
     * @return true if the event could be added, false otherwise.
     */
    public final boolean getAnswer(final Event event) {
        if (event.getStartDate().isEqual(event.getEndDate())) {
            return this.time.areCompatibleEquals(event, this.orderByHour(this.takeOnly(this.findByDate(event.getStartDate()))));
        } else {
            return this.time.areCompatibleDifferent(event, this.orderByHour(this.takeOnly(this.findByDate(event.getStartDate()))), this.takeOnly(this.orderByHour(this.findByDate(event.getEndDate()))));
        }
    }

    /**
     * This event is used to get the event that is closest to the event that must be added.
     * @param date is the date by which to find the closest event.
     * @return an event.
     */
    public final LocalTime getClosestEvent(final LocalDateTime date) {
        return this.takeOnly(this.orderByHour(this.findByDate(date.toLocalDate()))).stream().filter(e -> e.getStartHour().isAfter(date.toLocalTime())).findFirst().get().getStartHour();
    }

    /**
     * It return all the saved events.
     * @return a list of events.
     */
    public final Set<Event> getAll() {
        return this.events.getAll();
    }

    /**
     * This method is used to get only the daily events.
     * @return a set composed by only the daily events.
     */
    public final Set<Event> getDailyEvents() {
        return this.events.getAll().stream().filter(e -> !this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toSet());
    }

    /**
     * This method is used for get all the scheduled events.
     * @return the list with all the scheduled events.
     */
    public final Set<Event> getEvents() {
        return this.events.getAll().stream().filter(e -> {
            return this.time.getHourDuration(e) && !this.isAdequate(e);
        }).collect(Collectors.toSet());
    }

    /**
     * This method is used for get all the scheduled events with a minimum duration.
     * @return the list with all the scheduled events with a minimum duration.
     */
    public final Set<Event> getEventsWithDuration() {
        return this.getEvents().stream().filter(e -> this.time.getMinEventTime(e)).collect(Collectors.toSet());
    }

    /**
     * It return all the events generate after clicking hot keys.
     * @return a list of event.
     */
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

    /**
     * This method is used to sort a set of events by time.
     * @param eventsList is the set of events to order by time.
     * @return a set consisting of events sorted by time.
     */
    public final List<Event> orderByHour(final List<Event> eventsList) {
        eventsList.sort(Comparator.comparing(Event::getEnd));
        return eventsList;
    }

    /**
     * This method is used to remove all the event.
     */
    public final void removeAll() {
        for (final Event e : this.events.getAll()) {
            this.removeEvent(e);
        }
    }

    /**
     * This method is used to remove a specific event from the events list.
     * @param event is the event that must be removed from the events list.
     */
    public final void removeEvent(final Event event) {
        try {
            this.events.delete(event);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get only the events that have a duration less then 24 hours.
     * @param eventsList is the list of events from which to take only events with a duration of less than 24 hours.
     * @return a list of event.
     */
    public final List<Event> takeOnly(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toList());
    }

    /**
     * This method is used to get only the daily event.
     * @param eventsList is the list of events from which to take only events with a duration greatest than 24 hours.
     * @return a list of event.
     */
    public final List<Event> takeOnlyDailyEvent(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> !this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toList());
    }

    /**
     * This method returns false if there are other events at the same time as we want to start the timer.
     * @param date is the timer date and hour of start.
     * @return false if there are other events at the same time as we want to start  the timer true otherwise.
     */
    public final boolean timerCanStart(final LocalDateTime date) {
        for (final Event e : this.takeOnly(this.findByDate(date.toLocalDate()))) {
            if (this.time.getStart(date, e)) {
                return false;
            }
        }
        return true;
    }

}
