package oop.focus.statistics.controller;

import oop.focus.db.DataSource;

/**
 * The interface Events single value chart factory defines methods to create
 * different {@link ChartController} of a {@link oop.focus.statistics.view.SingleValueChart}.
 */
public interface EventsSingleValueChartFactory {
    /**
     * Creates a {@link ChartController} that manage a chart
     * that shows the occurrences for each different event name.
     *
     * @param dataSource the {@link DataSource} from which to retrieve data
     * @return the chart controller
     */
    ChartController<EventsInput> eventsOccurrences(DataSource dataSource);
}
