package oop.focus.calendar.month.controller;


import java.util.List;

import oop.focus.calendar.day.controller.CalendarDayController;
import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;




/**
 * Interface that models a Month Controller.
 * Is used for set and get the format and the the spacing or for update the month view.
 */
public interface CalendarMonthController extends  Controller {

    /**
     * Used for set the Format and the Spacing of the hoursbox of the day.
     * @param dayController : controller of the month
     */
    void configureDay(CalendarDayController dayController);

    /**
     * Used for set the List of the days (Month).
     */
    void setMonth();

    /**
     * 
     * Used to get the list with the days of the month.
     * @return List : month
     */
    List<Day> getMonth();

    /**
     * Used for get the logic of the calendar.
     * @return CalendarLogic
     */
     CalendarLogic getCalendarLogic();

    /**
     * Used for set the font size of the texts.
     * @param fontSize : double
     */
    void setFontSize(double fontSize);

    /**
     * Used for get the font size of texts.
     * @return double
     */
    double getFontSize();

    /**
     * Used for set the Format of the hoursbox.
     * @param format : format of the hours box
     */
    void setFormat(Format format);

    /**
     * Used for set the Spacing of the hoursbox.
     * @param spacing : space between two numbers in the hours box
     */
    void setSpacing(double spacing);

    /**
     * Used for update the view of the month.
     */
    void updateView();


    /**
     * Used for get the DataSource.
     * @return DataSource
     */
    DataSource getDataSource();

}
