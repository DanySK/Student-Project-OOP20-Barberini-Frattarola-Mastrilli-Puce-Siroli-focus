package oop.focus.calendar.controller;


import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.calendar.settings.controller.CalendarSettingsController;
import oop.focus.calendar.settings.controller.CalendarSettingsControllerImpl;
import oop.focus.calendar.view.CalendarView;
import oop.focus.calendar.view.CalendarViewImpl;
import oop.focus.calendar.week.controller.NewEventController;
import oop.focus.calendar.week.controller.NewEventControllerImpl;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.calendar.week.controller.WeekControllerImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.statistics.controller.EventsStatistics;




public class CalendarControllerImpl implements CalendarController {

    //Classes
    private final CalendarSettingsController settingsController;
    private final CalendarMonthController monthController;
    private final EventsStatistics statisticsController;
    private final WeekController weekController;
    private final NewEventController newEventController;
    private final PersonsController personController;
    private final CalendarView calendarview;


    /**
     * Used for initialize the calendar controller.
     * @param dataSource
     */
    public CalendarControllerImpl(final DataSource dataSource) {

        this.monthController = new CalendarMonthControllerImpl(CalendarType.NORMAL, dataSource);

        settingsController = new CalendarSettingsControllerImpl(monthController);

        this.statisticsController  = new EventsStatistics(dataSource);

        weekController = new WeekControllerImpl(dataSource);
        this.newEventController = new NewEventControllerImpl(dataSource, weekController, this.monthController);

        personController = new PersonsControllerImpl(dataSource);

        calendarview = new CalendarViewImpl(this);
        calendarview.setCalendarPage(); 
    }

    public final CalendarSettingsController getSettingsController() {
        return this.settingsController;
    }

    public final CalendarMonthController getMonthController() {
        return this.monthController;
    }

    public final EventsStatistics getStatisticsController() {
        return this.statisticsController;
    }

    public final WeekController getWeekController() {
        return this.weekController;
    }

    public final PersonsController getPersonController() {
        return this.personController;
    }

    public final NewEventController getNewEventController() {
        return this.newEventController;
    }

    public final View getView() {
        return this.calendarview;
    }


}
