package oop.focus.finance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.Linker;
import oop.focus.common.Repetition;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.TransactionImpl;
import oop.focus.finance.view.windows.NewTransactionViewImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Immutable implementation of a ew transaction controller.
 */
public class NewTransactionControllerImpl implements NewTransactionController {

    private final NewTransactionViewImpl view;
    private final FinanceManager manager;

    private final ObservableSet<Category> categories;
    private final ObservableSet<Account> accounts;

    public NewTransactionControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewTransactionViewImpl(this);
        this.categories = manager.getCategoryManager().getCategories();
        this.accounts = manager.getAccountManager().getAccounts();
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
    public final void newTransaction(final String description, final double amount, final Category category, final Account account,
                                     final java.time.LocalDate date, final int hours, final int minutes, final Repetition repetition) {
        this.manager.addTransaction(new TransactionImpl(description, category, new LocalDateTime(date.getYear(), date.getMonthValue(),
                        date.getDayOfMonth(), hours, minutes, 0), account, (int) (amount * 100), repetition));
        if (!repetition.equals(Repetition.ONCE)) {
            this.manager.generateRepeatedTransactions(LocalDate.now());
        }
    }

    @Override
    public final ObservableList<Category> getCategories() {
        final ObservableList<Category> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getCategoryManager().getCategories(), list);
        return list;
    }

    @Override
    public final ObservableList<Repetition> getRepetitions() {
        final ObservableList<Repetition> list = FXCollections.observableArrayList();
        Linker.listToList((Arrays.stream(Repetition.values())
                .collect(Collectors.toCollection(FXCollections::observableArrayList))), list);
        return list;
    }

    @Override
    public final ObservableList<Account> getAccounts() {
        final ObservableList<Account> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getAccountManager().getAccounts(), list);
        return list;
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }
}
