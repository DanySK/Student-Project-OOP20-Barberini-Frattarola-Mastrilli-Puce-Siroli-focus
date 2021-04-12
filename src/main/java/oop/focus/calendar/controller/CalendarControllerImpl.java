package oop.focus.calendar.controller;


import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.view.CalendarView;
import oop.focus.calendar.view.CalendarViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.statistics.controller.EventsStatistics;
import oop.focus.week.controller.WeekController;
import oop.focus.week.controller.WeekControllerImpl;
import oop.focus.week.view.AddNewEventWeekView;
import oop.focus.week.view.WeekView;
import oop.focus.week.view.WeekViewImpl;



public class CalendarControllerImpl implements CalendarController {

    //Classes
    private final CalendarSettingsController settingscontroller;
    private final CalendarMonthController monthcontroller;

    private final EventsStatistics statisticspage;

    private final AddNewEventWeekView newevent;
    private final WeekView week;
    private final CalendarView calendarview;


    /**
     * Used for initialize the calendar controller.
     * @param datasource
     */
    public CalendarControllerImpl(final DataSource datasource) {

        this.monthcontroller = new CalendarMonthControllerImpl(CalendarType.NORMAL, datasource);

        settingscontroller = new CalendarSettingsControllerImpl(monthcontroller);

        this.statisticspage  = new EventsStatistics(datasource);

        final WeekController weekcontroller = new  WeekControllerImpl(datasource);
        this.week = new WeekViewImpl(weekcontroller);
        this.newevent = new AddNewEventWeekView(weekcontroller);

        calendarview = new CalendarViewImpl(this);
        calendarview.setCalendarPage(); 
    }

    public final CalendarSettingsController getSettingsController() {
        return this.settingscontroller;
    }

    public final CalendarMonthController getMonthController() {
        return this.monthcontroller;
    }

    public final EventsStatistics getStatisticsController() {
        return this.statisticspage;
    }

    public final WeekView getWeek() {
        return this.week;
    }

    public final AddNewEventWeekView getNewEvent() {
        return this.newevent;
    }

    public final View getView() {
        return this.calendarview;
    }


}
