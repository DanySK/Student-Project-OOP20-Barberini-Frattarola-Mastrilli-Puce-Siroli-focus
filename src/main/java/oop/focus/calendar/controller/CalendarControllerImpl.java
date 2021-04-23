package oop.focus.calendar.controller;


import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.view.CalendarMonthFactoryImpl;
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
import oop.focus.calendarhomepage.controller.GeneralHomePageController;
import oop.focus.statistics.controller.EventsStatistics;



/**
 * Implementation of {@link CalendarController}.
 */
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
     * @param dataSource : the {@link DataSource} from which to retrieve data
     * @param homePageController : the {@link GeneralHomePageController}
     */
    public CalendarControllerImpl(final DataSource dataSource, final Controller homePageController) {

        this.monthController = new CalendarMonthFactoryImpl().createNormal(dataSource);

        this.statisticsController  = new EventsStatistics(dataSource);

        this.weekController = new WeekControllerImpl(dataSource, ((GeneralHomePageController) homePageController).getHomePageController());
        this.newEventController = new NewEventControllerImpl(dataSource, this.weekController, this.monthController);

        this.settingsController = new CalendarSettingsControllerImpl(this.monthController, this.weekController);

        this.eventController = new EventMenuControllerImpl(dataSource,  this.weekController, this.monthController);

        this.personController = new PersonsControllerImpl(dataSource);

        this.calendarView = new CalendarViewImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    public final CalendarSettingsController getSettingsController() {
        return this.settingsController;
    }

    /**
     * {@inheritDoc}
     */
    public final CalendarMonthController getMonthController() {
        return this.monthController;
    }

    /**
     * {@inheritDoc}
     */
    public final EventsStatistics getStatisticsController() {
        return this.statisticsController;
    }

    /**
     * {@inheritDoc}
     */
    public final WeekController getWeekController() {
        return this.weekController;
    }

    /**
     * {@inheritDoc}
     */
    public final PersonsController getPersonController() {
        return this.personController;
    }

    /**
     * {@inheritDoc}
     */
    public final NewEventController getNewEventController() {
        return this.newEventController;
    }

    /**
     * {@inheritDoc}
     */
    public final EventMenuController getEventInfoController() {
        return this.eventController;
    }

    /**
     * {@inheritDoc}
     */
    public final View getView() {
        return this.calendarView;
    }


}
