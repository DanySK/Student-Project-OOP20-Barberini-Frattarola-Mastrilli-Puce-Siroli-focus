package oop.focus.finance.controller;

import oop.focus.common.View;

public interface BaseController extends FinanceController {

    /**
     * Change the view to show in BaseView.
     *
     * @param view to show
     */
    void changeView(View view);
}
