package oop.focus.finance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.QuickTransactionImpl;
import oop.focus.finance.view.windows.NewQuickTransactionViewImpl;

/**
 * Immutable implementation of a new quick transaction controller.
 */
public class NewQuickTransactionControllerImpl implements NewQuickTransactionController {

    private final NewQuickTransactionViewImpl view;
    private final FinanceManager manager;

    private final ObservableSet<Category> categories;
    private final ObservableSet<Account> accounts;

    public NewQuickTransactionControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewQuickTransactionViewImpl(this);
        this.categories = manager.getCategoryManager().getElements();
        this.accounts = manager.getAccountManager().getElements();
        this.addListeners();
    }

    private void addListeners() {
        this.categories.addListener((SetChangeListener<Category>) change -> this.view.populate());
        this.accounts.addListener((SetChangeListener<Account>) change -> this.view.populate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void newQuickTransaction(final String description, final double amount, final Category category,
                                          final Account account) {
        this.manager.getQuickManager().add(new QuickTransactionImpl((int) amount * 100, description,
                category, account));
    }

    @Override
    public final ObservableList<Category> getCategories() {
        final ObservableList<Category> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getCategoryManager().getElements(), list);
        return list;
    }

    @Override
    public final ObservableList<Account> getAccounts() {
        final ObservableList<Account> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getAccountManager().getElements(), list);
        return list;
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }
}
