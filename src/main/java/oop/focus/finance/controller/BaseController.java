package oop.focus.finance.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;

public interface BaseController extends Controller {

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
