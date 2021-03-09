package oop.focus.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FinanceManagerImpl implements FinanceManager {

    private final List<Account> accounts = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public final void addAccount(final Account account) {
        this.accounts.add(account);
    }

    @Override
    public final void removeAccount(final Account account) {
        this.transactions.removeAll(this.transactions.stream().filter(t -> t.getAccount().equals(account)).collect(Collectors.toList()));
        this.accounts.remove(account);
    }

    @Override
    public final void addCategory(final Category category) {
        this.categories.add(category);
    }

    @Override
    public final void removeCategory(final Category category) {
        if (this.transactions.stream().map(Transaction::getCat).anyMatch(c -> c.equals(category))) {
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
        return this.accounts;
    }

    @Override
    public final List<Category> getCategories() {
        return this.categories;
    }

    @Override
    public final List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public final List<Transaction> getIncomes() {
        return this.filteredTransactions(t -> t.getAmount() > 0);
    }

    @Override
    public final List<Transaction> getOutings() {
        return this.filteredTransactions(t -> t.getAmount() < 0);
    }

    @Override
    public final List<Transaction> getSubscriptions() {
        return this.filteredTransactions(t -> !t.isLast());
    }

    @Override
    public final int monthlyExpense() {
        return this.transactions.stream()
                                .filter(t -> !t.isLast())
                                .map(t -> (int) (t.getAmount() / t.getRep().getPerMonth()))
                                .reduce(0, Integer::sum);
    }

    @Override
    public final int yearlyExpense() {
        return this.transactions.stream()
                                .filter(t -> !t.isLast())
                                .map(t -> (int) (t.getAmount() * t.getRep().getPerYear()))
                                .reduce(0, Integer::sum);
    }

    private List<Transaction> filteredTransactions(final Predicate<Transaction> predicate) {
        return this.transactions.stream().filter(predicate).collect(Collectors.toList());
    }

}
