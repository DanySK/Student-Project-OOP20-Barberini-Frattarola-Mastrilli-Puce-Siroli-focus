package oop.focus.statistics.controller;

import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.List;

/**
 * The Events statistics represents a controller that manages a chart section of
 * events data. It also manage the user input and updates the shown charts.
 */
public class EventsStatistics implements StatisticController<EventsInput> {

    private EventsInput actualInput;
    private final View view;
    private final StatisticController<EventsInput> statisticController;

    /**
     * Instantiates a new Events statistics and creates the associated view.
     *
     * @param dataSource the data source from which to retrieve data.
     */
    public EventsStatistics(final DataSource dataSource) {
        this.statisticController = new EventsStatisticsController(dataSource);
        InputController<EventsInput> inputController = new InputControllerFactoryImpl()
                .eventsInputController(this, dataSource);
        this.view = new ViewFactoryImpl()
                .createHorizontal(List.of(this.statisticController.getView(),
                        inputController.getView()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateInput(final EventsInput input) {
        if (!input.equals(this.actualInput)) {
            this.actualInput = input;
            this.statisticController.updateInput(input);
        }
    }
}
