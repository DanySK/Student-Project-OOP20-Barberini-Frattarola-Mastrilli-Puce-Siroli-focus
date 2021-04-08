package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;

public interface BaseController extends FinanceController {

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
