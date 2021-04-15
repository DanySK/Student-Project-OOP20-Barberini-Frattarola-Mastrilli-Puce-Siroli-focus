package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.CategoryImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.finance.view.windows.NewCategoryViewImpl;

public class NewCategoryControllerImpl implements NewCategoryController {

    private final GenericWindow<NewCategoryController> view;
    private final FinanceManager manager;

    public NewCategoryControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewCategoryViewImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void newCategory(final String name, final String color) {
        this.manager.addCategory(new CategoryImpl(name, color));
    }
}
