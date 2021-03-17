package oop.focus.finance;

import org.joda.time.LocalDate;

import java.util.stream.Collectors;

/**
 * Immutable implementation of a finance manager.
 */
public class FinanceManagerImpl implements FinanceManager {

    private final AccountManager accounts;
    private final CategoryManager categories;
    private final TransactionManager transactions;
    private final QuickTransactionManager quickTransactions;

    public FinanceManagerImpl() {
        this.accounts = new AccountManagerImpl();
        this.categories = new CategoryManagerImpl();
        this.transactions = new TransactionManagerImpl();
        this.quickTransactions = new QuickTransactionManagerImpl();
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
        transaction.getAccount().execute(transaction.getAmount());
    }

    @Override
    public final void removeTransaction(final Transaction transaction) {
        if (this.transactions.getTransactions().contains(transaction)) {
            this.transactions.remove(transaction);
            transaction.getAccount().execute(-transaction.getAmount());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final void doQuickTransaction(final QuickTransaction quickTransaction) {
        this.addTransaction(new TransactionImpl(quickTransaction.getDescription(), quickTransaction.getCategory(),
                LocalDate.now(), quickTransaction.getAccount(), quickTransaction.getAmount(), Repetition.ONCE, false));
    }

    @Override
    public final void generateRepeatedTransactions(final LocalDate date) {
        this.transactions.getGeneratedTransactions(date).forEach(this::addTransaction);
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
}
