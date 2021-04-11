package oop.focus.statistics.view;

import oop.focus.common.View;

/**
 * The interface Chart represents a base chart type of which a title can be defined.
 */
public interface Chart extends View {
    /**
     * Sets the chart title.
     *
     * @param title the title
     */
    void setTitle(String title);
}
