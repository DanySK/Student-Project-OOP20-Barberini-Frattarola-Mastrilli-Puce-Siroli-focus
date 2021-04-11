package oop.focus.finance.controller;

import com.sun.javafx.collections.ObservableListWrapper;
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
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.QuickTransactionImpl;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.model.TransactionImpl;
import oop.focus.finance.view.bases.FinanceHomePageView;
import oop.focus.finance.view.bases.FinanceHomePageViewImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceHomePageControllerImpl implements FinanceHomePageController {

    private final FinanceHomePageView view;
    private final FinanceManager manager;

    private final ObservableSet<Account> accounts;
    private final ObservableSet<Transaction> transaction;
    private final ObservableSet<QuickTransaction> quickTransactions;

    public FinanceHomePageControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new FinanceHomePageViewImpl(this);
        this.accounts = manager.getAccountManager().getAccounts();
        this.transaction = manager.getTransactionManager().getTransactions();
        this.quickTransactions = manager.getQuickManager().getQuickTransactions();
        this.addListeners();
    }

    private void addListeners() {
        this.accounts.addListener((SetChangeListener<Account>) change -> this.view.populateAccounts());
        this.quickTransactions.addListener((SetChangeListener<QuickTransaction>) change ->
                this.view.populateQuickTransactions());
        this.transaction.addListener((SetChangeListener<Transaction>) change -> {
            this.view.populateRecentTransactions();
            this.view.populateAccounts();
        });
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void newTransaction(final String description, final String amount, final Category category, final Account account,
                               final java.time.LocalDate date, final String hours, final String minutes, final Repetition repetition) {
        LocalDateTime d = new LocalDateTime(date == null ? LocalDate.now().getYear() : date.getYear(),
                date == null ? LocalDate.now().getMonthOfYear() : date.getMonthValue(),
                date == null ? LocalDate.now().getDayOfMonth() : date.getDayOfMonth(),
                hours.isEmpty() ? LocalDateTime.now().getHourOfDay() : Integer.parseInt(hours),
                minutes.isEmpty() ? LocalDateTime.now().getMinuteOfHour() : Integer.parseInt(minutes), 0);
        this.manager.addTransaction(new TransactionImpl(description, category, d, account,
                (int) (Double.parseDouble(amount) * 100), repetition));
    }

    @Override
    public final void newQuickTransaction(final String description, final String amount, final Category category,
                                          final Account account) {
        this.manager.getQuickManager().add(new QuickTransactionImpl((int) (Double.parseDouble(amount) * 100),
                description, category, account));
    }

    @Override
    public final void doQuickTransaction(final QuickTransaction quickTransaction) {
        this.manager.doQuickTransaction(quickTransaction);
    }

    @Override
    public final String getAmount(final Account account) {
        return this.format(this.manager.getAmount(account));
    }

    @Override
    public final String getTotalAmount() {
        return this.format(this.getAccounts().stream()
                .map(this.manager::getAmount)
                .mapToInt(i -> i)
                .sum());
    }

    @Override
    public final String format(final int amount) {
        final DecimalFormat f = new DecimalFormat("#0.00");
        return f.format((double) amount / 100);
    }

    @Override
    public final List<Transaction> getSortedTodayTransactions() {
        return this.manager.getTransactionManager().getTransactions().stream()
                .filter(t -> t.getDate().toLocalDate().equals(LocalDate.now()))
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public final List<QuickTransaction> getSortedQuickTransactions() {
        return this.manager.getQuickManager().getQuickTransactions().stream()
                .sorted(Comparator.comparing(QuickTransaction::getDescription))
                .collect(Collectors.toList());
    }

    @Override
    public final List<Account> getSortedAccounts() {
        return this.getAccounts().stream()
                .sorted(Comparator.comparing(Account::getName))
                .collect(Collectors.toList());
    }

    @Override
    public final ObservableList<Category> getCategories() {
        ObservableList<Category> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getCategoryManager().getCategories(), list);
        return list;
    }

    @Override
    public final ObservableList<Repetition> getRepetitions() {
        return new ObservableListWrapper<>(Arrays.asList(Repetition.values()));
    }

    @Override
    public final ObservableList<Account> getAccounts() {
        ObservableList<Account> list = FXCollections.observableArrayList();
        Linker.setToList(this.manager.getAccountManager().getAccounts(), list);
        return list;
    }
}
