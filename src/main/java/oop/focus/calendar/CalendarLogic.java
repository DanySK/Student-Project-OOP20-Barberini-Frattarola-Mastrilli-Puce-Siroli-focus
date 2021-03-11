package oop.focus.calendar;


import java.util.List;
import org.joda.time.LocalDate;

public interface CalendarLogic {

    /**
     * 
     * @param day is the number of the day that we want to generate
     * @return all the information about a day
     */
    DayImpl getDay(LocalDate day);

    /**
     * @param day is the number of the day that we want to generate
     * @return an generated day
     */
    DayImpl generateDay(LocalDate day);

    /**
     * 
     * @return a list of 7 days
     */
    List<DayImpl> getWeek();

    /**
     * 
     * @return a list of x days
     */
    List<DayImpl> getMonth();

    /**
     * 
     * @return a list of 365 days
     */
    List<DayImpl> getYear();

    /**
     * @param numberofdays is the number of day of the list
     * @param startingday is the day from it start to generate the calendar
     * @return a generated list of numberofdays days
     */
    List<DayImpl> generate(int numberofdays, LocalDate startingday);

    /**
     * generate a list of 7 day.
     * @return Set of 7 generated days 
     */
    List<DayImpl> generateWeek();

    /**
     * generate a list of x day.
     * @return Set of x generated days 
     */
    List<DayImpl> generateMonth();

    /**
     * generate a list of 365 day.
     * @return Set of 365 generated days 
     */
    List<DayImpl> generateYear();

    /**
     * ask to generate the next week.
     * @param change , if is true get the previous week, if is false the next one
     */
    void changeWeek(boolean change);


    /**
     * ask to generate the next month.
     * @param change , if is true get the previous month, if is false the next one
     */
    void changeMonth(boolean change);


    /**
     * ask to generate the next year.

     * @param change , if is true get the previous year, if is false the next one
     */
    void changeYear(boolean change);



}
