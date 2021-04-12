package oop.focus.calendar.model;


import java.util.List;
import org.joda.time.LocalDate;

/**
 * The interface models the Calendar Logic.
 * It defines the standard methods for manage the Calendar List
 * that are Month, Week and Year
 */
public interface CalendarLogic {

    /**
     * Used for get an Day from a list.
     * @param day : date of the day that we want to generate
     * @return  DayImpl : all the information about a day
     */
    DayImpl getDay(LocalDate day);

    /**
     * Used for build a day from a date.
     * @param day : date of the day that we want to generate
     * @return DayImpl : an generated day
     */
    DayImpl generateDay(LocalDate day);

    /**
     * Used for get the week list.
     * @return a list of 7 days
     */
    List<DayImpl> getWeek();

    /**
     * Used for get the month list.
     * @return a list of x days
     */
    List<DayImpl> getMonth();

    /**
     * Used for get the year list.
     * @return a list of 365 days
     */
    List<DayImpl> getYear();

    /**
     * Used for generate one of the Calendar List.
     * @param numberOfDays : is the number of day of the list
     * @param startingDate : is the date of the day from it start to generate the calendar
     * @return List<DayImpl> : a generated list of number number of days.
     */
    List<DayImpl> generate(int numberOfDays, LocalDate startingDate);

    /**
     * Generate a list of 7 day.
     * @return Set of 7 generated days 
     */
    List<DayImpl> generateWeek();

    /**
     * Generate a list of x day.
     * @return Set of x generated days 
     */
    List<DayImpl> generateMonth();

    /**
     * Generate a list of 365 day.
     * @return Set of 365 generated days 
     */
    List<DayImpl> generateYear();

    /**
     * Used for change week.
     * @param change , if is true get the previous week, if is false the next one
     */
    void changeWeek(boolean change);


    /**
     * Used for change month.
     * @param change , if is true get the previous month, if is false the next one
     */
    void changeMonth(boolean change);


    /**
     * Used for change year.
     * @param change , if is true get the previous year, if is false the next one
     */
    void changeYear(boolean change);



}
