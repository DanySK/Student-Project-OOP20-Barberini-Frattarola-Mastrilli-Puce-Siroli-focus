package oop.focus.homePage.model;

import java.util.*;
import java.util.stream.Collectors;

import org.joda.time.*;

/**
 * 
 * This class adds new methods to manage events.
 * For example for search for an event by a specific date.
 */
public class ManagerEventImpl implements ManagerEvent {
	
	private Set<Event> events;
	
	/**
	 * This is the class constructor.
	 */
	public ManagerEventImpl() {
	    this.events = new HashSet<>();
	}
	
	/**
	 * 
	 * This method is use to add new event.
	 * @param e is the event that must be added to events list.
	 */
	public final void addEvent(final Event e) {
		this.events.add(e);
	}

	/**
	 * 
	 * This method is use to add new events.
	 * @param e is an event list that must be added to events list.
	 */
    	public final void addEventsSet(final Set<Event> e) {
  		this.events.addAll(e);
  	}
 
    	/**
    	 * 
    	 * This method is use to find the events that have a specific start date.
    	 * @param date is the date on which to search for events.
    	 * @return a list of events with the date parameter as start date.
    	 */
	public final Set<Event> findByDate(final LocalDate date) {
		return this.events.stream().filter(e -> e.getStartDate().equals(date)).collect(Collectors.toSet());
	}
	
	/**
	 * 
	 * This method is use to find the events that have a specific start hour.
	 * @param hour is the start hour on which to search for events.
	 * @return a list of events with the hour parameter as start hour.
	 */
	public final Set<Event> findByHour(final LocalDateTime hour) {
		return this.events.stream().filter(e -> e.getStartHour().equals(hour)).collect(Collectors.toSet());
	}

	/**
	 * 
	 * This method is use for get all the scheduled events.
	 * @return the list with all the scheduled events.
	 */
	public final Set<Event> getEvents() {
		return this.events;
	}
	
	/**
	 * 
	 * This method is use to remove a specific event from the events list.
	 * @param event is the event that must be removed from the events list.
	 */
	public final void removeEvent(final Event event) {
		this.events.remove(event);
	}
	
	/**
	 * 
	 * This method is use to remove a specific event from the events list.
	 * @param events  is the events list that must be removed from the events list.
	 */
	public final void removeEventsSet(final Set<Event> events) {
		this.events.removeAll(events);
	}
}
