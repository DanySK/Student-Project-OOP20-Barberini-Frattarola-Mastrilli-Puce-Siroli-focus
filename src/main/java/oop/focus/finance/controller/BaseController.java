package oop.focus.finance.controller;

import javafx.scene.Parent;
import oop.focus.finance.view.View;

public interface BaseController {

    /**
     * Change the view to show in BaseView.
     *
     * @param view to show
     */
    void changeView(View view);

    /**
     * @return the root of BaseView
     */
    Parent getView();
}
