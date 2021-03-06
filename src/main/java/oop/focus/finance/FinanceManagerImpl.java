package oop.focus.finance;

import java.util.ArrayList;
import java.util.List;

public class FinanceManagerImpl implements FinanceManager {

    private final List<Account> accounts = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public final void addAccount(final Account account) {
        accounts.add(account);
    }

    @Override
    public final void addCategory(final Category category) {
        categories.add(category);
    }

    @Override
    public final void addTransaction(final Transaction transaction) {
        transactions.add(transaction);
        transaction.getAccount().execute(transaction.getAmount());
    }

    @Override
    public final void removeTransaction(final Transaction transaction) {
        transactions.remove(transaction);
        transaction.getAccount().execute(-transaction.getAmount());
    }

    @Override
    public final List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public final List<Category> getCategories() {
        return categories;
    }

    @Override
    public final List<Transaction> getTransactions() {
        return transactions;
    }

}
