package oop.focus.statistics.controller;

import oop.focus.common.UpdatableController;
import oop.focus.db.DataSource;

/**
 * The interface Events single value chart factory defines methods to create
 * different {@link UpdatableController} about the events.
 */
public interface EventsChartFactory {
    /**
     * Creates a {@link UpdatableController} that manage a chart
     * that shows the occurrences for each different event name.
     *
     * @param dataSource the {@link DataSource} from which to retrieve data
     * @return the chart controller
     */
    UpdatableController<TimePeriodInput<String>> eventsOccurrences(DataSource dataSource);

    /**
     * Creates a {@link UpdatableController} that manage a chart
     * that shows for each day , the time spent for each different event name.
     *
     * @param dataSource the {@link DataSource} from which to retrieve data
     * @return the chart controller
     */
    UpdatableController<TimePeriodInput<String>> eventsTimePerDays(DataSource dataSource);
}
