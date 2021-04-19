package oop.focus.statistics.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.List;

/**
 * The Events statistics represents a controller that manages a chart section of
 * events data. It also manage the user input and updates the shown charts.
 */
public class EventsStatistics implements Controller {

    private final View view;

    /**
     * Instantiates a new Events statistics and creates the associated view.
     *
     * @param dataSource the data source from which to retrieve data.
     */
    public EventsStatistics(final DataSource dataSource) {
        final var statisticController = new EventsStatisticsController(dataSource);
        final var inputController = new InputControllerFactoryImpl()
                .eventsInputController(statisticController, dataSource);
        this.view = new ViewFactoryImpl().createHorizontal(List.of(
                statisticController.getView(),
                inputController.getView()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }
}
