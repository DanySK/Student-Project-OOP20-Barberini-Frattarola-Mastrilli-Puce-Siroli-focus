package oop.focus.finance.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;

/**
 * Implementation of a controller interface that takes care of changing the screen to be displayed.
 */
public interface ChangeViewController extends Controller {

    /**
     * Change the view to show in BaseView.
     *
     * @param view to show
     */
    void changeView(View view);

    /**
     * @return manager of finance
     */
    FinanceManager getManager();
}
