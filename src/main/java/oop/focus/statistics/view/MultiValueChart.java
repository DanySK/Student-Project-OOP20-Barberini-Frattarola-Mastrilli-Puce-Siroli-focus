package oop.focus.statistics.view;

import javafx.util.Pair;

import java.util.List;

/**
 * The interface Multi value chart defines a chart that displays multiples series of data.
 * Each series can contain multiple pair of values.
 * Each pair of values is represented by a string and an associated double value.
 */
public interface MultiValueChart extends ColorableChart {
    /**
     * Update the data in an orderly manner and change the displayed chart.
     *
     * @param items the list containing the series.
     *              Each series is a list of {@link Pair}. For each pair the pair key represents the string
     *              and the pair value represents the double value associated with the string.
     */
    void updateData(List<List<Pair<String, Double>>> items);

    /**
     * Assigns names to series in an orderly manner.
     * if the data is updated, the names must also be updated
     * to ensure that they are displayed in the graph properly.
     * If this method is not called, no name will be given to the series
     *
     * @param names the names
     */
    void setNames(List<String> names);
}
