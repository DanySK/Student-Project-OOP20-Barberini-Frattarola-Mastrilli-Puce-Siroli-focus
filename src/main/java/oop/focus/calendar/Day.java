package oop.focus.calendar;

import java.util.List;


import oop.focus.homepage.model.Event;
/**
 * The Days interface models the object day.
 * It defines the standard methods for manage the days of Calendar
 */

public interface Day {

     /**
     * Can be used to get the number of the day.
     *
     * @return the number of the day of the month 
     *
     */
    int getNumber();

    /**
     * Can be used to get the day of the week.
     *
     * @return the day of the week 
     *
     */
    int getDayOfTheWeek();


    /**
     * Can be used to get the name of the day.
     *
     * @return the name of the day 
     *
     */
    String getName();

    /**
     * Can be used to get the Month of the day.
     *
     * @return the name of the Month 
     *
     */
    String getMonth();

    /**
     * Can be used to get the Year of the day.
     *
     * @return the number of the Year 
     *
     */
    int getYear();

    /**
     * Can be used to see if is Sunday.
     *
     * @return true if is Sunday 
     *
     */
    boolean isSunday();

    /**
     * Can be used to get the list of the events of the day.
     *
     * @return the list of event of the day 
     *
     */
    List<Event> getEvents();
}
