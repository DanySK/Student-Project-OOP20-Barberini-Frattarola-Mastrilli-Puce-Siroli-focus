package oop.focus.statistics.view;

import java.util.List;

/**
 * The interface Colorable chart represents a chart that can be colored.
 */
public interface ColorableChart extends Chart {
    /**
     * Assigns colors to chart data in an orderly manner.
     * If the colors list bigger then the data size, the exceeding elements will be ignored.
     * If the colors list is smaller then the data size, the remaining elements will be assigned a random color.
     * If this method is not called, the chart will be random colored.
     *
     * @param colors the colors
     */
    void setColors(List<String> colors);
}
