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
     * @param day : {@link LocalDate} of the day that we want to generate
     * @return  Day : all the information about a day.
     */
    Day getDay(LocalDate day);

    /**
     * Used for get the week list.
     * @return a list of 7 {@link Day}
     */
    List<Day> getWeek();

    /**
     * Used for get the month list.
     * @return a list of X {@link Day}
     */
    List<Day> getMonth();

    /**
     * Used for get the year list.
     * @return a list of 365 {@link Day}
     */
    List<Day> getYear();

    /**
     * Generate a list of 7 day.
     * @return Set of 7 generated days 
     */
    List<Day> generateWeek();

    /**
     * Generate a list of x {@link Day}.
     * @return Set of x generated days 
     */
    List<Day> generateMonth();

    /**
     * Generate a list of 365 {@link Day}.
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
