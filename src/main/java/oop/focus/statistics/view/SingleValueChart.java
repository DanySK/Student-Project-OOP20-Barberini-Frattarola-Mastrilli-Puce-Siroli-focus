package oop.focus.statistics.view;

import javafx.util.Pair;

import java.util.List;

/**
 * The interface Single value chart defines a chart that displays a single series of data.
 * The series can contain multiple pair of values.
 * Each pair of values is represented by a string and an associated double value.
 */
public interface SingleValueChart extends ColorableChart {
    /**
     * Update the data in an orderly manner and change the displayed chart.
     *
     * @param items the list containing the series.
     *              The series is a list of {@link Pair}. For each pair the pair key represents the string
     *              and the pair value represents the double value associated with the string.
     */
    void updateData(List<Pair<String, Double>> items);
}
