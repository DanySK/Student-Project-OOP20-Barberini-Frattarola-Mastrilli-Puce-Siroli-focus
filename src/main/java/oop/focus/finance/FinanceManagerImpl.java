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
        accounts.add(account);
    }

    @Override
    public final void removeAccount(final Account account) {
        transactions.removeAll(transactions.stream().filter(t -> t.getAccount().equals(account)).collect(Collectors.toList()));
        accounts.remove(account);
    }

    @Override
    public final void addCategory(final Category category) {
        categories.add(category);
    }

    @Override
    public final void removeCategory(final Category category) {
        if (transactions.stream().map(t -> t.getCat()).anyMatch(c -> c.equals(category))) {
            impossible();
        } else {
            categories.remove(category);
        }
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

    @Override
    public final List<Transaction> getIncomes() {
        return filteredTransactions(t -> t.getAmount() > 0);
    }

    @Override
    public final List<Transaction> getOutings() {
        return filteredTransactions(t -> t.getAmount() < 0);
    }


    @Override
    public final List<Transaction> getSubscriptions() {
        return filteredTransactions(t -> !t.isLast());
    }

    private List<Transaction> filteredTransactions(final Predicate<Transaction> predicate) {
        return transactions.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public final int monthlyExpense() {
        return transactions.stream()
                           .filter(t -> !t.isLast())
                           .map(t -> (int) (t.getAmount() / t.getRep().getPerMonth()))
                           .reduce(0, (a, b) -> a + b);
    }

    @Override
    public final int yearlyExpense() {
        return transactions.stream()
                           .filter(t -> !t.isLast())
                           .map(t -> (int) (t.getAmount() * t.getRep().getPerYear()))
                           .reduce(0, (a, b) -> a + b);
    }

    private void impossible() {
        System.out.println("Impossibile eseguire l'operazione");
    }

}
