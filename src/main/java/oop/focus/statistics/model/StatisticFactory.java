package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.homepage.model.Event;
import org.joda.time.LocalDate;

/**
 * The interface Statistic factory provides methods to create different DataCreators elements.
 */
public interface StatisticFactory {
    /**
     * Creates a DataCreator that maps an event to a {@link Pair} of String and Integer.
     * Each pair represents the number of occurrences for a specific event name.
     *
     * @return the DataCreator
     */
    DataCreator<Event, Pair<String, Integer>> getEventsOccurrences();
    /**
     * Creates a DataCreator that maps an event to a {@link Pair} of {@link LocalDate} and Integer.
     * Each pair represents the time, in minutes, spent in a specific day for a specific event name.
     *
     * @param eventName the event name whose occurrences are to be obtained
     * @return the DataCreator
     */
    DataCreator<Event, Pair<LocalDate, Integer>> getEventTimePerDay(String eventName);
}
