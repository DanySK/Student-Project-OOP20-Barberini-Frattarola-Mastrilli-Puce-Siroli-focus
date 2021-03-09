package oop.focus.finance;

import java.util.List;
import java.util.stream.Collectors;

public class FinanceManagerImpl implements FinanceManager {

    private final AccountManager accounts = new AccountManagerImpl();
    private final CategoryManager categories = new CategoryManagerImpl();
    private final TransactionManager transactions = new TransactionManagerImpl();

    @Override
    public final void addAccount(final Account account) {
        this.accounts.add(account);
    }

    @Override
    public final void removeAccount(final Account account) {
        this.transactions.removeAll(this.transactions.getTransactions().stream()
                                                                       .filter(t -> t.getAccount()
                                                                       .equals(account))
                                                                       .collect(Collectors.toList()));
        this.accounts.remove(account);
    }

    @Override
    public final void addCategory(final Category category) {
        this.categories.add(category);
    }

    @Override
    public final void removeCategory(final Category category) {
        if (this.transactions.getTransactions().stream().map(Transaction::getCat).anyMatch(c -> c.equals(category))) {
            throw new IllegalStateException();
        } else {
            this.categories.remove(category);
        }
    }

    @Override
    public final void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
        transaction.getAccount().execute(transaction.getAmount());
    }

    @Override
    public final void removeTransaction(final Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.getAccount().execute(-transaction.getAmount());
    }

    @Override
    public final List<Account> getAccounts() {
        return this.accounts.getAccounts();
    }

    @Override
    public final List<Category> getCategories() {
        return this.categories.getCategories();
    }

    @Override
    public final List<Transaction> getTransactions() {
        return this.transactions.getTransactions();
    }

    @Override
    public final TransactionManager getTransactionManager() {
        return this.transactions;
    }
}
