package oop.focus.statistics.controller;

import oop.focus.db.DataSource;

/**
 * The interface Events multi value chart factory defines methods to create
 * different {@link ChartController} of a {@link oop.focus.statistics.view.MultiValueChart}.
 */
public interface EventsMultiValueChartFactory {
    /**
     * Creates a {@link ChartController} that manage a chart
     * that shows for each day , the time spent for each different event name.
     *
     * @param dataSource the {@link DataSource} from which to retrieve data
     * @return the chart controller
     */
    ChartController<EventsInput> eventsTimePerDays(DataSource dataSource);
}
