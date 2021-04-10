package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.stream.Collectors;

/**
 * Immutable implementation of a finance manager.
 */
public class FinanceManagerImpl implements FinanceManager {

    private final DataSource db;
    private final AccountManager accounts;
    private final CategoryManager categories;
    private final TransactionManager transactions;
    private final QuickTransactionManager quickTransactions;
    private final GroupManager group;
    private final ObservableSet<String> colors;

    public FinanceManagerImpl(final DataSource db) {
        this.db = db;
        this.accounts = new AccountManagerImpl(this.db);
        this.categories = new CategoryManagerImpl(this.db);
        this.transactions = new TransactionManagerImpl(this.db);
        this.quickTransactions = new QuickTransactionManagerImpl(this.db);
        this.group = new GroupManagerImpl(this.db);
        this.colors = this.db.getColors().getAll();
    }

    @Override
    public final void addAccount(final Account account) {
        this.accounts.add(account);
    }

    @Override
    public final void removeAccount(final Account account) {
        this.transactions.getTransactions().stream()
                        .filter(t -> t.getAccount()
                        .equals(account))
                        .collect(Collectors.toList()).forEach(this.transactions::remove);
        this.accounts.remove(account);
    }

    @Override
    public final void addCategory(final Category category) {
        this.categories.add(category);
    }

    @Override
    public final void removeCategory(final Category category) {
        if (this.transactions.getTransactions().stream()
                .map(Transaction::getCategory)
                .noneMatch(c -> c.equals(category))) {
            this.categories.remove(category);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public final int getAmount(final Account account) {
        final var acc = this.accounts.getAccounts().stream()
                .filter(a -> a.equals(account))
                .collect(Collectors.toList())
                .get(0);
        return acc.getInitialAmount() + this.transactions.getTransactions().stream()
                .filter(t -> t.getAccount().equals(acc))
                .mapToInt(Transaction::getAmount)
                .sum();
    }

    @Override
    public final void removeTransaction(final Transaction transaction) {
        if (this.transactions.getTransactions().contains(transaction)) {
            this.transactions.remove(transaction);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final void doQuickTransaction(final QuickTransaction quickTransaction) {
        this.addTransaction(new TransactionImpl(quickTransaction.getDescription(), quickTransaction.getCategory(),
                LocalDateTime.now(), quickTransaction.getAccount(), quickTransaction.getAmount(), Repetition.ONCE, false));
    }

    @Override
    public final void generateRepeatedTransactions(final LocalDate date) {
        final var sub = this.transactions.getSubscriptions();
        this.transactions.getGeneratedTransactions(date).forEach(this::addTransaction);
        sub.forEach(this.transactions::update);
    }

    @Override
    public final DataSource getDb() {
        return this.db;
    }

    @Override
    public final AccountManager getAccountManager() {
        return this.accounts;
    }

    @Override
    public final CategoryManager getCategoryManager() {
        return this.categories;
    }

    @Override
    public final TransactionManager getTransactionManager() {
        return this.transactions;
    }

    @Override
    public final QuickTransactionManager getQuickManager() {
        return this.quickTransactions;
    }

    @Override
    public final GroupManager getGroupManager() {
        return this.group;
    }

    @Override
    public final ObservableSet<String> getColors() {
        return this.colors;
    }
}
