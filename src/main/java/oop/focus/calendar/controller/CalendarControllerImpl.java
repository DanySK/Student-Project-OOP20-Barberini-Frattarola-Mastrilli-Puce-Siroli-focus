package oop.focus.calendar.controller;


import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.calendar.settings.controller.CalendarSettingsController;
import oop.focus.calendar.settings.controller.CalendarSettingsControllerImpl;
import oop.focus.calendar.view.CalendarViewImpl;
import oop.focus.calendar.week.controller.NewEventController;
import oop.focus.calendar.week.controller.NewEventControllerImpl;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.calendar.week.controller.WeekControllerImpl;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.event.controller.EventMenuController;
import oop.focus.event.controller.EventMenuControllerImpl;
import oop.focus.homepage.controller.GeneralHomePageController;
import oop.focus.statistics.controller.EventsStatistics;




public class CalendarControllerImpl implements CalendarController {

    //Classes
    private final CalendarSettingsController settingsController;
    private final CalendarMonthController monthController;
    private final EventsStatistics statisticsController;
    private final WeekController weekController;
    private final NewEventController newEventController;
    private final PersonsController personController;
    private final EventMenuController eventController;
    private final View calendarView;


    /**
     * Used for initialize the calendar controller.
     * @param dataSource
     * @param controller
     */
    public CalendarControllerImpl(final DataSource dataSource, final Controller controller) {

        this.monthController = new CalendarMonthControllerImpl(CalendarType.NORMAL, dataSource);

        settingsController = new CalendarSettingsControllerImpl(monthController);

        this.statisticsController  = new EventsStatistics(dataSource);

        weekController = new WeekControllerImpl(dataSource, ((GeneralHomePageController) controller).getHomePageController());
        this.newEventController = new NewEventControllerImpl(dataSource, weekController, this.monthController);

        this.eventController = new EventMenuControllerImpl(dataSource,  this.weekController, this.monthController);

        personController = new PersonsControllerImpl(dataSource);

        this.calendarView = new CalendarViewImpl(this);
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

    public final EventMenuController getEventInfoController() {
        return this.eventController;
    }

    public final View getView() {
        return this.calendarView;
    }


}
