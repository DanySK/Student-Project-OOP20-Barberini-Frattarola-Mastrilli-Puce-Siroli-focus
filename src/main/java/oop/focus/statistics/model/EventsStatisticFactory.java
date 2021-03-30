package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.homepage.model.Event;
import org.joda.time.LocalDate;

/**
 * The interface Statistic factory provides methods to create different events DataCreators elements.
 */
public interface EventsStatisticFactory {
    /**
     * Creates a DataCreator that maps each event to a {@link Pair} of String and Integer.
     * Each pair represents the number of occurrences for a specific event name.
     *
     * @return the DataCreator
     */
    DataCreator<Event, Pair<String, Integer>> eventsOccurrences();
    /**
     * Creates a DataCreator that maps each event,with a given name, to a {@link Pair} of {@link LocalDate} and Integer.
     * Each pair represents the time, in minutes, spent in a specific day for a specific event name.
     *
     * @param eventName the event name
     * @return the DataCreator
     */
    DataCreator<Event, Pair<LocalDate, Integer>> eventTimePerDay(String eventName);

    /**
     * Creates a DataCreator that maps each event with a given name that occurred between
     * a start date (inclusive) and and an end date (inclusive),
     * to a {@link Pair} of {@link LocalDate} and Integer.
     * Each pair represents the time, in minutes, spent in a specific day for a specific event name.
     * Note that the event must start and finish in the specified period to be considered.
     *
     * @param eventName the event name
     * @param start     the start date
     * @param end       the end date
     * @return the DataCreator
     */
    DataCreator<Event, Pair<LocalDate, Integer>> boundedEventTimePerDay(String eventName, LocalDate start, LocalDate end);
}
