package oop.focus.homepage.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import javafx.collections.ObservableSet;

public interface EventManager {

    /**
     * This method is used to add new event.
     * @param event is the event that must be added to events list.
     */
    void addEvent(Event event);

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
    Optional<LocalTime> getClosestEvent(LocalDateTime date);

    /**
     * It return all the saved events.
     * @return a list of events.
     */
    Set<Event> getAll();

    /**
     * 
     * @param date
     * @return a list of event.
     */
    List<Event> getFutureEvent(LocalDate date);

    /**
     * This method is used to obtain all the events that respect are generate after clicking hot keys.
     * @return list of event that are generate after clicking hot keys.
     */
    List<Event> getHotKeyEvents();

    /**
     * This method is used to remove a specific event from the events list.
     * @param event is the event that must be removed from the events list.
     */
    void removeEvent(Event event);

    void saveTimer(Event event);

    /**
     * This method is used to know if a timer can start.
     * @param date represents the date and time to check if a timer can be started.
     * @return true if it's possible, false otherwise.
     */
    boolean timerCanStart(LocalDateTime date);

	ObservableSet<Event> getAller();

}
