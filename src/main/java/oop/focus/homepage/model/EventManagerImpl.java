package oop.focus.homepage.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.common.Repetition;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        if (this.isAdequate(event) || this.time.getValidity(event) && this.time.getHourDuration(event) && this.getAnswer(event) || !this.time.getHourDuration(event)) {
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

    public final List<Event> generateListOfNextEvent(final LocalDate date) {
         return this.events.getAll().stream()
               .flatMap(e -> this.generateNext(e, date).stream()).collect(Collectors.toList());
    }

    public final List<Event> generateNext(final Event event, final LocalDate date) {
        if (event.getRipetition().equals(Repetition.ONCE) || new LocalDate(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth())
               .isBefore(new LocalDate(event.getNextRenewal().getStartDate()))) {
           return new ArrayList<>();
        }
        event.stopRepeat();
        final var newEvent = event.getNextRenewal();
        return Stream.concat(List.of(newEvent).stream(),
               this.generateNext(newEvent, date).stream()).collect(Collectors.toList());
    }

    public final void generateRepeatedEvents(final LocalDate date) {
        this.generateListOfNextEvent(date).forEach(e -> {
            try {
                this.events.save(e);
            } catch (DaoAccessException daoAccessException) {
                daoAccessException.printStackTrace();
            }
        });
    }

    public final Set<Event> getAll() {
        return this.events.getAll();
    }

    public final boolean getAnswer(final Event event) {
        if (event.getStartDate().isEqual(event.getEndDate())) {
            return this.time.areCompatibleEquals(event, this.orderByHour(this.takeOnly(this.findByDate(event.getStartDate()))));
        } else {
            return this.time.areCompatibleDifferent(event, this.orderByHour(this.takeOnly(this.findByDate(event.getStartDate()))), this.takeOnly(this.orderByHour(this.findByDate(event.getEndDate()))));
        }
    }

    public final Optional<LocalTime> getClosestEvent(final LocalDateTime date) {
        if (this.takeOnly(this.orderByHour(this.findByDate(date.toLocalDate()))).stream().anyMatch(e -> e.getStartHour().isAfter(date.toLocalTime()))) {
            return Optional.of(this.takeOnly(this.orderByHour(this.findByDate(date.toLocalDate()))).stream().filter(e -> e.getStartHour().isAfter(date.toLocalTime())).findFirst().get().getStartHour());
        }
        return Optional.empty();
    }

    public final Set<Event> getDailyEvents() {
        return this.events.getAll().stream().filter(e -> !this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toSet());
    }

    public final Set<Event> getEvents() {
        return this.events.getAll().stream().filter(e -> {
            return this.time.getHourDuration(e) && !this.isAdequate(e);
        }).collect(Collectors.toSet());
    }

    public final List<Event> getEventsWithDuration(final List<Event> listOfEvents) {
        return listOfEvents.stream().filter(e -> this.time.getMinEventTime(e)).collect(Collectors.toList());
    }

    public final List<Event> getFutureEvent(final LocalDate date) {
        final List<Event> eventsToShow = new ArrayList<>();

        for (final Event event : this.getAll()) {
            if (!event.getRipetition().equals(Repetition.ONCE) && event.isRepeated()) {
                LocalDate startDate = event.getStartDate();
                LocalDate endDate = event.getEndDate();

                while (startDate.isBefore(date) && endDate.isBefore(date)) {
                    startDate = event.getRipetition().getNextRenewalFunction().apply(startDate);
                    endDate = event.getRipetition().getNextRenewalFunction().apply(endDate);
                }
                if (startDate.equals(date) || endDate.equals(date) || startDate.isBefore(date) && endDate.isAfter(date)) {
                    eventsToShow.add(new EventImpl(event.getName(), startDate.toLocalDateTime(event.getStartHour()), endDate.toLocalDateTime(event.getEndHour()), event.getRipetition()));
                }
            }
        }
        return eventsToShow;
    }

    public final List<Event> getHotKeyEvents() {
        return this.events.getAll().stream().filter(e -> this.isAdequate(e)).collect(Collectors.toList());
    }

    private boolean isAdequate(final Event event) {
        return event.getStart().isEqual(event.getEnd());
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

    public final void saveTimer(final Event event) {
        try {
            this.events.save(event);
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

    public final List<Event> takeOnlyHotKeyEvent(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> this.isAdequate(e)).collect(Collectors.toList());
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
