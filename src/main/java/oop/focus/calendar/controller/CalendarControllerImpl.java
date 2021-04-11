package oop.focus.calendar.controller;


import oop.focus.calendar.view.CalendarView;
import oop.focus.calendar.view.CalendarViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSourceImpl;



public class CalendarControllerImpl implements CalendarController {

    //Classes
    private final CalendarSettingsController settingscontroller;
    private final CalendarMonthController monthcontroller;
    private final CalendarView calendarview;

    //Constants
    private static final double DAYWIDTH = 200;
    private static final double DAYHEIGHT = 500;

    /**
     * Used for initialize the calendar controller.
     * @param datasource
     */
    public CalendarControllerImpl(final DataSourceImpl datasource) {

        this.monthcontroller = new CalendarMonthControllerImpl(datasource, DAYWIDTH, DAYHEIGHT);

        settingscontroller = new CalendarSettingsControllerImpl(monthcontroller);

        calendarview = new CalendarViewImpl(this);
        calendarview.setCalendarPage(); 
    }

    public final CalendarSettingsController getSettingsController() {
        return settingscontroller;
    }

    public final CalendarMonthController getMonthController() {
        return monthcontroller;
    }

    public final View getView() {
        return calendarview;
    }


}
