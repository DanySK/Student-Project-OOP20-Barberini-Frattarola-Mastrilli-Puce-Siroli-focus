package oop.focus.statistics.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.List;

/**
 * The Finance statistics represents a controller that manages a chart section of
 * finance data. It also manage the user input and updates the shown charts.
 */
public class FinanceStatistics implements StatisticController<FinanceInput> {

    private FinanceInput actualInput;
    private final View statisticsView;
    private final StatisticController<FinanceInput> statisticController;

    /**
     * Instantiates a new Finance statistics and creates the associated view.
     *
     * @param manager the finance manager
     */
    public FinanceStatistics(final FinanceManager manager) {
        this.statisticController = new FinanceStatisticsController(manager);
        AbstractInputController<FinanceInput> inputController = new InputControllerFactoryImpl()
                .financeInputController(this, manager);
        this.statisticsView = new ViewFactoryImpl()
                .createHorizontal(List.of(this.statisticController.getView(), inputController.getView()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.statisticsView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateInput(final FinanceInput input) {
        if (!input.equals(this.actualInput)) {
            this.actualInput = input;
            this.statisticController.updateInput(input);
        }
    }
}
