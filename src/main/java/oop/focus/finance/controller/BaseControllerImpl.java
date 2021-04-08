package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.bases.BaseView;
import oop.focus.finance.view.bases.BaseViewImpl;

public class BaseControllerImpl implements BaseController {

    private final BaseView view;

    public BaseControllerImpl(final FinanceManager manager) {
        this.view = new BaseViewImpl(this, manager);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void changeView(final View view) {
        this.view.changeView(view);
    }
}
