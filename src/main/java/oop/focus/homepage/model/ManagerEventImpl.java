package oop.focus.homepage.model;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

/**
 * This class adds new methods to manage events.
 * For example for search for an event by a specific date.
 */
public class ManagerEventImpl implements ManagerEvent {

    private final Set<Event> events;
    private final TimeProperty time;

    /**
     * This is the class constructor.
     */
    public ManagerEventImpl() {
        this.events = new HashSet<>();
        this.time = new TimePropertyImpl();
    }

    /**
     * This method is use to add new event.
     * @param e is the event that must be added to events list.
     */
    public final void addEvent(final Event e) {
        if (this.time.getValidity(e) && this.time.getHourDuration(e) && this.getAnswer(e) || !this.time.getHourDuration(e)) {
            this.events.add(e);
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * This method is use to add new events.
     * @param eventsSet is an event list that must be added to events list.
     */
    public final void addEventsSet(final Set<Event> eventsSet) {
        for (final Event event : eventsSet) {
            this.addEvent(event);
        }
    }

    /**
     * This method is use to find the events events that take place on a certain date.
     * @param date is the date on which to search for events.
     * @return a list of events taking place on that particular date.
     */
    public final List<Event> findByDate(final LocalDate date) {
        return this.events.stream().filter(e -> {
        return e.getStartDate().equals(date) || e.getEndDate().equals(date) || e.getStartDate().isBefore(date) && e.getEndDate().isAfter(date);
        }).filter(e -> !this.isAdequate(e)).collect(Collectors.toList());
    }

    /**
     * This method is use to find the events that have a specific start hour.
     * @param hour is the start hour on which to search for events.
     * @return a list of events with the hour parameter as start hour.
     */
    public final Set<Event> findByHour(final LocalTime hour) {
        return this.events.stream().filter(e -> e.getStartHour().equals(hour)).collect(Collectors.toSet());
    }

    /**
     * This method is use to find the events that have a specific name.
     * @param name is the name on which to search for events.
     * @return a list of events with the name parameter as name.
     */
    public final Set<Event> findByName(final String name) {
        return this.events.stream().filter(e -> e.getName().equals(name)).collect(Collectors.toSet());
    }

    /**
     * This method is use to add a new events only if this is possible.
     * @param e is the event that must be add.
     * @return true if the event could be added, false otherview.
     */
    public final boolean getAnswer(final Event e) {
        if (e.getStartDate().isEqual(e.getEndDate())) {
            return this.time.areCompatibleEquals(e, this.orderByHour(this.takeOnly(this.findByDate(e.getStartDate()))));
        } else {
            return this.time.areCompatibleDifferent(e, this.orderByHour(this.takeOnly(this.findByDate(e.getStartDate()))), this.takeOnly(this.orderByHour(this.findByDate(e.getEndDate()))));
        }
    }

    /**
     * This event is use to get the event that is closest to the event that must be added.
     * @param date is the date by which to find the closest event.
     * @return an event.
     */
    public final Optional<LocalTime> getClosestEvent(final LocalDateTime date) {
    	for (final Event event : this.events) {
    		if (date.toLocalTime().isEqual(event.getStartHour()) || date.toLocalTime().isEqual(event.getEndHour()) || date.toLocalTime().isBefore(event.getEndHour()) && date.toLocalTime().isAfter(event.getEndHour())) {
    			throw new IllegalStateException();
    		}
    	}
        return Optional.of(this.takeOnly(this.orderByHour(this.findByDate(date.toLocalDate()))).stream().filter(e -> e.getStartHour().isAfter(date.toLocalTime())).findFirst().get().getStartHour());
    }

    /**
     * This method is use to get only the daily events.
     * @return a set composed by only the daily events.
     */
    public final Set<Event> getDailyEvents() {
        return this.events.stream().filter(e -> !this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toSet());
    }

    /**
     * This method is use for get all the scheduled events.
     * @return the list with all the scheduled events.
     */
    public final Set<Event> getEvents() {
        return this.events.stream().filter(e -> {
            return this.time.getHourDuration(e) && !this.isAdequate(e);
        }).collect(Collectors.toSet());
    }

    /**
     * This method is use for get all the scheduled events with a minimum duration.
     * @param eventsSet is the set from which to take the events that respect a minimum duration.
     * @return the list with all the scheduled events with a minimum duration.
     */
    public final Set<Event> getEventsWithDuration(final Set<Event> eventsSet) {
        return eventsSet.stream().filter(e -> this.time.getMinEventTime(e)).collect(Collectors.toSet());
    }

    /**
     * This method is used to sort a set of events by time.
     * @param eventsList is the set of events to order by time.
     * @return a set consisting of events sorted by time.
     */
    public final List<Event> orderByHour(final List<Event> eventsList) {
        eventsList.sort((e1, e2) -> e1.getEnd().compareTo(e2.getEnd()));
        return eventsList;
    }

    /**
     * This method is use to get only the events that have a duration less then 24 hours.
     * @param eventsList is the list of events from which to take only events with a duration of less than 24 hours.
     * @return a list of event.
     */
    public final List<Event> takeOnly(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toList());
    }

    /**
     * This method is use to get only the daily event.
     * @param eventsList is the list of events from which to take only events with a duration greatest than 24 hours.
     * @return a list of event.
     */
    public final List<Event> takeOnlyDailyEvent(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> !this.time.getHourDuration(e) && !this.isAdequate(e)).collect(Collectors.toList());
    }

    /**
     * This method is use to remove a specific event from the events list.
     * @param event is the event that must be removed from the events list.
     */
    public final void removeEvent(final Event event) {
        this.events.remove(event);
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
}
