package oop.focus.statistics.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Events statistics controller manages the chart visualization, populating and updating
 * the views when the {@link EventsInput} changes.
 */
public class EventsStatisticsController implements StatisticController<EventsInput> {

    private final View view;
    private final List<ChartController<EventsInput>> charts;

    /**
     * Instantiates a new Events statistics controller and creates the associated view.
     *
     * @param dataSource the {@link DataSource} from which to retrieve data
     */
    public EventsStatisticsController(final DataSource dataSource) {
        this.charts = new ArrayList<>();
        var pieFactory = new EventsSingleValueChartFactoryImpl();
        var lineFactory = new EventsMultiValueChartFactoryImpl();
        this.charts.add(lineFactory.eventsTimePerDays(dataSource));
        this.charts.add(pieFactory.eventsOccurrences(dataSource));
        this.view = new ViewFactoryImpl()
                .createVerticalAutoResizing(this.charts.stream()
                        .map(Controller::getView).collect(Collectors.toList()));
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
        this.charts.forEach(a -> a.updateInput(input));
    }
}
