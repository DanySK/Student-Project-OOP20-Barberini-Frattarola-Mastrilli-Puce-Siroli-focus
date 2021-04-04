package oop.focus.finance.controller;

import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.GroupViewImpl;
import oop.focus.finance.view.View;

public class GroupControllerImpl implements GroupController {

    private final GroupViewImpl view;
    private final FinanceManager manager;

    public GroupControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new GroupViewImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }
}
