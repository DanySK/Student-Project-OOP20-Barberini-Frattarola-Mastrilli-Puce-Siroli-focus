package oop.focus.homePage.model;

import java.util.*;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/*
 * This class implements Event interface.
 */
public class EventImpl implements Event {

	private String name;
	private HotKeyType category;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startHour; 
	private LocalTime endHour;
	private Ripetitions ripetition;
	private List<Person> persons;
	
	/**
	 * 
	 * This is the class constructor.
	 * @param name is the name of the event to create.
	 * @param startDate is the start day of the event to create.
	 * @param endDate is the end day of the event to create.
	 * @param startHour is the start hour of the event to create.
	 * @param endHour is the end hour of the event to create.
	 * @param ripetition is the field that tells if and when an event will repeat itself.
	 */
	public EventImpl(final String name, final LocalDate startDate, final LocalDate endDate, final LocalTime startHour, final LocalTime endHour, final Ripetitions ripetition) {
		this.name = name;
		this.category = HotKeyType.EVENT;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.endHour = endHour;
		this.ripetition = ripetition;
		this.persons = new ArrayList<>();
	}
	
	/**
	 * 
	 * This method is use for get the event name.
	 * @return a string that rappresent the event name.
	 */
	public final String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * This method is use to get the color of an event.
	 * @return a String that rappresent the color of the event.
	 */
	public final String getColor() {
	    return this.category.getColor();
	}
	/**
	 * 
         * This method is use for get the event start hour.
         * @return a LocalDateTime that rappresent the event startHour.
         */
	public final LocalTime getStartHour() {
		return this.startHour;
	}
	
	/**
	 * 
         * This method is use for get the event end hour.
         * @return a LocalDateTime that rappresent the event endHour.
         */
	public final LocalTime getEndHour() {
		return this.endHour;
	}
	
	/**
	 * 
         * This method is use for get the event start day.
         * @return a LocalDate that rappresent the event startDay.
         */
	public final LocalDate getStartDate() {
		return this.startDate;
	}
	
	/**
	 * 
         * This method is use for get the event end day.
         * @return a LocalDate that rappresent the event endDay.
         */
	public final LocalDate getEndDate() {
		return this.endDate;
	}

	/**
	 * 
         * This method is used to know if an event repeats itself or not, and if it recurs, to know how often.
         * @return a member of the Ripetitions enum.
         */
	public final Ripetitions getRipetition() {
		return this.ripetition;
	}
	
	/**
	 * 
         * This method is used to add a new person who will attend the event.
         * @param p is the person to add.
         */
	public final void addPerson(final Person p) {
		if (!this.persons.contains(p)) {
			this.persons.add(p);
		}
	}
	
}
