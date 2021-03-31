package oop.focus.finance.controller;

import javafx.scene.Parent;
import oop.focus.finance.view.BaseView;
import oop.focus.finance.view.View;

public class BaseControllerImpl implements BaseController {

    private final BaseView view;

    public BaseControllerImpl() {
        this.view = new BaseView(this);
    }

    @Override
    public final void changeView(final View view) {
        this.view.changeView(view.getRoot());
    }

    @Override
    public final Parent getView() {
        return this.view.getRoot();
    }
}
