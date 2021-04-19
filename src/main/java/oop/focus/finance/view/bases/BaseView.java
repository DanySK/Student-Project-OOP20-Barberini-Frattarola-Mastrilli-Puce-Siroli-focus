package oop.focus.finance.view.bases;

import oop.focus.common.View;

/**
 * Interface that implements the finance section skeleton view.
 */
public interface BaseView extends View {

    /**
     * Change the contents of the main pane to be displayed.
     *
     * @param view to add to main pane
     */
    void changeView(View view);
}
