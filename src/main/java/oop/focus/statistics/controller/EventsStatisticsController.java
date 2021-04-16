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
 * the views when the {@link TimePeriodInput} changes.
 */
public class EventsStatisticsController implements UpdatableController<TimePeriodInput<String>> {

    private final View view;
    private final List<UpdatableController<TimePeriodInput<String>>> charts;
    private TimePeriodInput<String> actualInput;

    /**
     * Instantiates a new Events statistics controller and creates the associated view.
     *
     * @param dataSource the {@link DataSource} from which to retrieve data
     */
    public EventsStatisticsController(final DataSource dataSource) {
        this.charts = new ArrayList<>();
        var factory = new EventsChartFactoryImpl();
        this.charts.add(factory.eventsTimePerDays(dataSource));
        this.charts.add(factory.eventsOccurrences(dataSource));
        this.view = new ViewFactoryImpl().createVerticalAutoResizing(this.charts.stream()
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
    public final void updateInput(final TimePeriodInput<String> input) {
        if (!input.equals(this.actualInput)) {
            this.actualInput = input;
            this.charts.forEach(a -> a.updateInput(input));
        }
    }
}
