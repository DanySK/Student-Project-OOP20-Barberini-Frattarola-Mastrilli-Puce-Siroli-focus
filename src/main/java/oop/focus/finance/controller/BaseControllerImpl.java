package oop.focus.finance.controller;

import javafx.scene.Parent;
import oop.focus.db.DataSource;
import oop.focus.finance.view.BaseView;
import oop.focus.finance.view.View;

public class BaseControllerImpl implements BaseController {

    private final BaseView view;

    public BaseControllerImpl(final DataSource db) {
        this.view = new BaseView(this, db);
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
