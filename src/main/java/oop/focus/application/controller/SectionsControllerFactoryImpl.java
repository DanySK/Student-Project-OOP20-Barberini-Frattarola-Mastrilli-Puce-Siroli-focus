package oop.focus.application.controller;

import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.diary.controller.GeneralDiaryController;
import oop.focus.finance.controller.ChangeViewControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.calendarhomepage.controller.GeneralHomePageControllerImpl;

/**
 * Implementation of {@link SectionsControllerFactory}.
 */
public class SectionsControllerFactoryImpl implements SectionsControllerFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getHomePageController(final DataSource dataSource, final FinanceManager financeManager) {
        return new GeneralHomePageControllerImpl(dataSource, financeManager);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getFinanceController(final FinanceManager financeManager) {
        return new ChangeViewControllerImpl(financeManager);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getCalendarController(final DataSource dataSource, final Controller controller) {
        return new CalendarControllerImpl(dataSource,  controller);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getDiaryController(final DataSource dataSource) {
        return new GeneralDiaryController(dataSource);
    }
}
