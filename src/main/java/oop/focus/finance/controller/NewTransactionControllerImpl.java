package oop.focus.finance.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class NewTransactionControllerImpl implements NewTransactionController {

    private final NewTransactionViewImpl view;
    private final FinanceManager manager;

    public NewTransactionControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewTransactionViewImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void newTransaction(final String description, final double amount, final Category category, final Account account,
                                     final java.time.LocalDate date, final int hours, final int minutes, final Repetition repetition) {
        final LocalDateTime formattedDate = new LocalDateTime(date == null ? LocalDate.now().getYear() : date.getYear(),
                date == null ? LocalDate.now().getMonthOfYear() : date.getMonthValue(),
                date == null ? LocalDate.now().getDayOfMonth() : date.getDayOfMonth(), hours, minutes, 0);
        this.manager.addTransaction(new TransactionImpl(description, category, formattedDate, account,
                (int) (amount * 100), repetition));
        this.manager.generateRepeatedTransactions(LocalDate.now());
    }

    @Override
    public final ObservableList<Category> getCategories() {
        final ObservableList<Category> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getCategoryManager().getCategories(), list);
        return list;
    }

    @Override
    public final ObservableList<Repetition> getRepetitions() {
        return new ObservableListWrapper<>(Arrays.asList(Repetition.values()));
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
