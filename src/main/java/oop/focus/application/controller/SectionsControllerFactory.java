package oop.focus.application.controller;

import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.finance.model.FinanceManager;

/**
 * This interface defines methods to create new {@link Controller}. Each Controller manages a
 * section of application.
 */
public interface SectionsControllerFactory {
    /**
     * Initializes and returns the Controller of homePage's section.
     * @param dataSource    the{@link DataSource} from which to retrieve data
     * @param financeManager    the finance manager
     * @return  Home Page's Controller
     */
    Controller getHomePageController(DataSource dataSource, FinanceManager financeManager);

    /**
     * Initializes and returns the Controller of finance's section.
     * @param financeManager    the finance manager
     * @return  Finance's Controller
     */
    Controller getFinanceController(FinanceManager financeManager);

    /**
     * Initializes and returns the Controller of calendar's section.
     * @param dataSource    the{@link DataSource} from which to retrieve data
     * @param controller    the finance manager
     * @return  Calendar's Controller
     */
    Controller getCalendarController(DataSource dataSource,  Controller controller);

    /**
     * Initializes and returns the Controller of Diary's section.
     * @param dataSource    the{@link DataSource} from which to retrieve data
     * @return  Diary's Controller
     */
    Controller getDiaryController(DataSource dataSource);
}
