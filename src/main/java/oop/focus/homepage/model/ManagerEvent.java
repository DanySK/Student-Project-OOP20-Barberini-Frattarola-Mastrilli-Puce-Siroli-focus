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

    Set<Event> getDailyEvents();
    /**
     * This method is use for get all the scheduled events.
     * @return the list with all the scheduled events.
     */
    Set<Event> getEvents();

    /**
     * This method is use to obtain all the events that respect a minimum duration.
     * @param eventsSet is the set from which to take the events that respect a minimum duration.
     * @return set of event that respect a minimum duration.
     */
    Set<Event> getEventsWithDuration(Set<Event> eventsSet);

    /**
     * This method is used to sort a set of events by time.
     * @param eventsSet is the set of events to order by time.
     * @return a set consisting of events sorted by time.
     */
    List<Event> orderByHour(List<Event> eventsSet);

    List<Event> takeOnly(List<Event> events);

    List<Event> takeOnlyDailyEvent(List<Event> eventsList);
    /**
     * This method is use to remove a specific event from the events list.
     * @param event is the event that must be removed from the events list.
     */
    void removeEvent(Event event);
}
