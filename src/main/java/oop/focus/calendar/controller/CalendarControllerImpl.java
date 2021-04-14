package oop.focus.calendar.controller;


import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.calendar.persons.view.PersonsView;
import oop.focus.calendar.persons.view.PersonsViewImpl;
import oop.focus.calendar.view.CalendarView;
import oop.focus.calendar.view.CalendarViewImpl;
import oop.focus.calendar.week.controller.NewEventController;
import oop.focus.calendar.week.controller.NewEventControllerImpl;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.calendar.week.controller.WeekControllerImpl;
import oop.focus.calendar.week.view.NewEventWeekViewImpl;
import oop.focus.calendar.week.view.WeekView;
import oop.focus.calendar.week.view.WeekViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.statistics.controller.EventsStatistics;




public class CalendarControllerImpl implements CalendarController {

    //Classes
    private final CalendarSettingsController settingscontroller;
    private final CalendarMonthController monthcontroller;

    private final EventsStatistics statisticscontroller;

    private final NewEventController newevent;
    private final WeekView week;
    private final PersonsView personsview;

    private final CalendarView calendarview;


    /**
     * Used for initialize the calendar controller.
     * @param datasource
     */
    public CalendarControllerImpl(final DataSource datasource) {

        this.monthcontroller = new CalendarMonthControllerImpl(CalendarType.NORMAL, datasource);

        settingscontroller = new CalendarSettingsControllerImpl(monthcontroller);

        this.statisticscontroller  = new EventsStatistics(datasource);

        final WeekController weekcontroller = new WeekControllerImpl(datasource);
        this.week = new WeekViewImpl(weekcontroller);
        this.newevent = new NewEventControllerImpl(datasource,weekcontroller,  this.monthcontroller);

        final PersonsController personcontroller = new PersonsControllerImpl(datasource);
        personsview = new PersonsViewImpl(personcontroller);

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
        return this.statisticscontroller;
    }

    public final WeekView getWeek() {
        return this.week;
    }

    public final PersonsView getPerson() {
        return this.personsview;
    }

    public final NewEventController getNewEvent() {
        return this.newevent;
    }

    public final View getView() {
        return this.calendarview;
    }


}
