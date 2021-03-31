package oop.focus.homepage.model;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public interface EventManager {

    /**
     * This method is used to add new event.
     * @param event is the event that must be added to events list.
     */
    void addEvent(Event event);

    /**
     * This method is used to add new events.
     * @param events is an event list that must be added to events list.
     */
    void addEventsSet(Set<Event> events);

    /**
     * This method is utilized from timer to check if the journey is empt(there aren't events).
     * @param date is the timer date and hour of start.
     * @return true if the journey is empty , false otherwise.
     */
    boolean checkEmptyJourney(LocalDateTime date);

    /**
     * This method is used to find the events that have a specific start date.
     * @param date is the date on which to search for events.
     * @return a list of events with the date parameter as start date.
     */
    List<Event> findByDate(LocalDate date);

    /**
     * This method is used to find the events that have a specific start hour.
     * @param hour is the start hour on which to search for events.
     * @return a list of events with the hour parameter as start hour.
     */
    Set<Event> findByHour(LocalTime hour);

    /**
     * This method is used to find the events that have a specific name.
     * @param name is the name on which to search for events.
     * @return a list of events with the name parameter as name.
     */
    Set<Event> findByName(String name);

    /**
     * This method is used to generate the next events that repeats.
     * @param date is the date on which we take events.
     */
    void generateRepeatedEvents(LocalDate date);

    /**
     * This event is used to get the event that is closest to the event that must be added.
     * @param date is the date by which to find the closest event.
     * @return an event.
     */
    LocalTime getClosestEvent(LocalDateTime date);

    /**
     * It return all the saved events.
     * @return a list of events.
     */
    List<Event> getAll();

    /**
     * This method is used to get only the daily events.
     * @return a set composed by only the daily events.
     */
    Set<Event> getDailyEvents();

    /**
     * This method is used for get all the scheduled events.
     * @return the list with all the scheduled events.
     */
    Set<Event> getEvents();

    /**
     * This method is used to obtain all the events that respect a minimum duration.
     * @return set of event that respect a minimum duration.
     */
    Set<Event> getEventsWithDuration();

    /**
     * This method is used to obtain all the events that respect are generate after clicking hot keys.
     * @return list of event that are generate after clicking hot keys.
     */
    List<Event> getHotKeyEvents();

    /**
     * This method is used to sort a set of events by time.
     * @param eventsSet is the set of events to order by time.
     * @return a set consisting of events sorted by time.
     */
    List<Event> orderByHour(List<Event> eventsSet);

    /**
     * This method is used to remove all the save events.
     */
    void removeAll();
    /**
     * This method is used to remove a specific event from the events list.
     * @param event is the event that must be removed from the events list.
     */
    void removeEvent(Event event);

    /**
     * This method is used to get only the events that have a duration less then 24 hours.
     * @param eventsList is the list of events from which to take only events with a duration of less than 24 hours.
     * @return a list of event.
     */
    List<Event> takeOnly(List<Event> eventsList);

    /**
     * This method is used to get only the daily event.
     * @param eventsList is the list of events from which to take only events with a duration greatest than 24 hours.
     * @return a list of event.
     */
    List<Event> takeOnlyDailyEvent(List<Event> eventsList);

    /**
     * This method is used to know if a timer can start.
     * @param date represents the date and time to check if a timer can be started.
     * @return true if it's possible, false otherwise.
     */
    boolean timerCanStart(LocalDateTime date);
}
