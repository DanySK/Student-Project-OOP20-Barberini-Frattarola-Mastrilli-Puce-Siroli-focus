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
     * @return  Day : all the information about a day
     */
    Day getDay(LocalDate day);

    /**
     * Used for build a day from a date.
     * @param day : date of the day that we want to generate
     * @return Day : an generated day
     */
    Day generateDay(LocalDate day);

    /**
     * Used for get the week list.
     * @return a list of 7 days
     */
    List<Day> getWeek();

    /**
     * Used for get the month list.
     * @return a list of x days
     */
    List<Day> getMonth();

    /**
     * Used for get the year list.
     * @return a list of 365 days
     */
    List<Day> getYear();

    /**
     * Used for generate one of the Calendar List.
     * @param numberOfDays : is the number of day of the list
     * @param startingDate : is the date of the day from it start to generate the calendar
     * @return List<DayImpl> : a generated list of number number of days.
     */
    List<Day> generate(int numberOfDays, LocalDate startingDate);

    /**
     * Generate a list of 7 day.
     * @return Set of 7 generated days 
     */
    List<Day> generateWeek();

    /**
     * Generate a list of x day.
     * @return Set of x generated days 
     */
    List<Day> generateMonth();

    /**
     * Generate a list of 365 day.
     * @return Set of 365 generated days 
     */
    List<Day> generateYear();

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
