package oop.focus.application.controller;

import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.controller.GeneralDiaryController;
import oop.focus.finance.controller.BaseControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.homepage.view.GeneralHomePageController;

/**
 * Implementation of {@link SectionsControllerFactory}.
 */
public class SectionsControllerFactoryImpl implements SectionsControllerFactory {
    private final DataSource dataSource;
    private final FinanceManager financeManager;
    private final Controller generalHomePageController;
    public SectionsControllerFactoryImpl() {
        this.dataSource = new DataSourceImpl();
        this.financeManager = new FinanceManagerImpl(this.dataSource);
        this.generalHomePageController = new GeneralHomePageController(this.dataSource, this.financeManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getHomePageController() {
        return  this.generalHomePageController;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getFinanceController() {
        return new BaseControllerImpl(this.financeManager);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getCalendarController() {
        return new CalendarControllerImpl(this.dataSource);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getDiaryController() {
        return new GeneralDiaryController(this.dataSource);
    }
}
