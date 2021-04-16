package oop.focus.statistics.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.List;

/**
 * The Finance statistics represents a controller that manages a chart section of
 * finance data. It also manage the user input and updates the shown charts.
 */
public class FinanceStatistics implements Controller {

    private final View statisticsView;

    /**
     * Instantiates a new Finance statistics and creates the associated view.
     *
     * @param manager the finance manager
     */
    public FinanceStatistics(final FinanceManager manager) {
        var statisticController = new FinanceStatisticsController(manager);
        var inputController = new InputControllerFactoryImpl()
                .financeInputController(statisticController, manager);
        this.statisticsView = new ViewFactoryImpl()
                .createHorizontal(List.of(statisticController.getView(), inputController.getView()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.statisticsView;
    }
}
