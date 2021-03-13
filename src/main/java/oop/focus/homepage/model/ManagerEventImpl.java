package oop.focus.homepage.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
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
        this.time = new TimeProperty();
    }

    /**
     * This method is use to add new event.
     * @param e is the event that must be added to events list.
     */
    public final void addEvent(final Event e) {
        if (this.time.areCompatible(e, this.orderByHour(findByDate(e.getStartDate())))) {
            this.events.add(e);
        }
    }

    /**
     * This method is use to add new events.
     * @param e is an event list that must be added to events list.
     */
    public final void addEventsSet(final Set<Event> e) {
        this.events.addAll(e);
    }

    /**
     * This method is use to find the events that have a specific start date.
     * @param date is the date on which to search for events.
     * @return a list of events with the date parameter as start date.
     */
    public final List<Event> findByDate(final LocalDate date) {
        return this.events.stream().filter(e -> e.getStartDate().equals(date)).collect(Collectors.toList());
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
     * This method is use for get all the scheduled events.
     * @return the list with all the scheduled events.
     */
    public final Set<Event> getEvents() {
        return this.events;
    }

    /**
     * This method is used to sort a set of events by time.
     * @param eventsList is the set of events to order by time.
     * @return a set consisting of events sorted by time.
     */
    public final List<Event> orderByHour(final List<Event> eventsList) {
        eventsList.sort((e1, e2) -> e1.getStartHour().getHourOfDay() - e2.getStartHour().getHourOfDay());
        return eventsList;
    }

    /**
     * This method is use to remove a specific event from the events list.
     * @param event is the event that must be removed from the events list.
     */
    public final void removeEvent(final Event event) {
        this.events.remove(event);
    }

    /**
     * This method is use to remove a specific event from the events list.
     * @param events  is the events list that must be removed from the events list.
     */
    public final void removeEventsSet(final Set<Event> events) {
        this.events.removeAll(events);
    }

}
