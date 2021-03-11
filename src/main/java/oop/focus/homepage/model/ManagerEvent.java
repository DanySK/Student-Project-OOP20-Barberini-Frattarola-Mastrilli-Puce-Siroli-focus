package oop.focus.homepage.model;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public interface ManagerEvent {

    /**
     * This method is use to add new event.
     * @param event is the event that must be added to events list.
     */
    void addEvent(Event event);

    /**
     * This method is use to add new events.
     * @param events is an event list that must be added to events list.
     */
    void addEventsSet(Set<Event> events);

    /**
     * This method is use to find the events that have a specific start date.
     * @param date is the date on which to search for events.
     * @return a list of events with the date parameter as start date.
     */
    List<Event> findByDate(LocalDate date);

    /**
     * This method is use to find the events that have a specific start hour.
     * @param hour is the start hour on which to search for events.
     * @return a list of events with the hour parameter as start hour.
     */
    Set<Event> findByHour(LocalTime hour);

    /**
     * This method is use to find the events that have a specific name.
     * @param name is the name on which to search for events.
     * @return a list of events with the name parameter as name.
     */
    Set<Event> findByName(String name);

    /**
     * This method is use for get all the scheduled events.
     * @return the list with all the scheduled events.
     */
    Set<Event> getEvents();
    /**
     * This method is used to sort a set of events by time.
     * @param eventsSet is the set of events to order by time.
     * @return a set consisting of events sorted by time.
     */
    List<Event> orderByHour(List<Event> eventsSet);

    /**
     * This method is use to remove a specific event from the events list.
     * @param event is the event that must be removed from the events list.
     */
    void removeEvent(Event event);

    /**
     * This method is use to remove a specific event from the events list.
     * @param events  is the events list that must be removed from the events list.
     */
    void removeEventsSet(Set<Event> events);
}
