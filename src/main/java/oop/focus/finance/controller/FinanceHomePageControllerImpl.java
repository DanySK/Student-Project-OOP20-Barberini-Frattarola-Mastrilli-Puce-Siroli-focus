package oop.focus.finance.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.Repetition;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.model.TransactionImpl;
import oop.focus.finance.view.FinanceHomePageViewImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceHomePageControllerImpl implements FinanceHomePageController {

    private final View view;
    private final FinanceManager manager;

    public FinanceHomePageControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new FinanceHomePageViewImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final double getTotalAmount() {
        return (double) this.getAccounts().stream()
                .map(this.manager::getAmount)
                .mapToInt(i -> i)
                .sum() / 100;
    }

    @Override
    public final ObservableList<Category> getCategories() {
        return new ObservableListWrapper<>(new ArrayList<>(this.manager.getCategoryManager().getCategories()));
    }

    @Override
    public final ObservableList<Account> getAccounts() {
        return new ObservableListWrapper<>(new ArrayList<>(this.manager.getAccountManager().getAccounts()));
    }

    @Override
    public final ObservableList<Repetition> getRepetitions() {
        return new ObservableListWrapper<>(Arrays.asList(Repetition.values()));
    }

    @Override
    public final void newTransaction(final String description, final double amount, final Category category,
                               final Account account, final LocalDateTime date, final Repetition repetition) {
        this.manager.addTransaction(new TransactionImpl(description, category, date, account,
                (int) (amount * 100), repetition));
    }

    @Override
    public final List<Transaction> getTodayTransactions() {
        return this.manager.getTransactionManager().getTransactions().stream()
                .filter(t -> !t.getDate().isBefore(new LocalDateTime(LocalDate.now().getYear(),
                        LocalDate.now().getMonthOfYear(), LocalDate.now().getDayOfMonth(), 0, 0, 0)))
                .collect(Collectors.toList());
    }

    @Override
    public final int getAmount(final Account account) {
        return this.manager.getAmount(account);
    }

    @Override
    public final void doQuickTransaction(final QuickTransaction quickTransaction) {
        this.manager.doQuickTransaction(quickTransaction);
    }

    @Override
    public final ObservableSet<QuickTransaction> getQuickTransactions() {
        return this.manager.getQuickManager().getQuickTransactions();
    }
}
