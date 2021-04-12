package oop.focus.finance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.QuickTransactionImpl;
import oop.focus.finance.view.windows.NewQuickTransactionViewImpl;

public class NewQuickTransactionControllerImpl implements NewQuickTransactionController {

    private final NewQuickTransactionViewImpl view;
    private final FinanceManager manager;

    public NewQuickTransactionControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewQuickTransactionViewImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void newQuickTransaction(final String description, final double amount, final Category category,
                                          final Account account) {
        this.manager.getQuickManager().add(new QuickTransactionImpl((int) amount * 100, description,
                category, account));
    }

    @Override
    public final ObservableList<Category> getCategories() {
        final ObservableList<Category> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getCategoryManager().getCategories(), list);
        return list;
    }

    @Override
    public final ObservableList<Account> getAccounts() {
        final ObservableList<Account> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getAccountManager().getAccounts(), list);
        return list;
    }
}
