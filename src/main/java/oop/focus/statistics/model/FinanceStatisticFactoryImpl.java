package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FinanceStatisticFactoryImpl implements FinanceStatisticFactory {

    private final FinanceManager dataSource;

    public FinanceStatisticFactoryImpl(final FinanceManager dataSource) {
        this.dataSource = dataSource;
    }

    private <X, Y> Set<Pair<X, Y>> toPairSet(final Map<X, Y> map) {
        return map.entrySet().stream().map(e -> new Pair<>(e.getKey(), e.getValue())).collect(Collectors.toSet());
    }

    @Override
    public final DataCreator<Account, Pair<String, Integer>> accountBalances() {
        return new DataCreatorImpl<>(this.dataSource.getAccountManager().getAccounts(),
                t -> this.toPairSet(t.collect(Collectors.toMap(Account::getName,
                        this.dataSource::getAmount))));
    }

    @Override
    public final DataCreator<Transaction, Pair<String, Integer>> categoryBalances() {
        return new DataCreatorImpl<>(this.dataSource.getTransactionManager().getTransactions(),
                c -> this.toPairSet(c.collect(Collectors.toMap(t -> t.getCategory().getName(),
                        Transaction::getAmount, Integer::sum))));

    }

    @Override
    public final DataCreator<Transaction, Pair<String, Integer>> accountCategoryBalances(final Account account) {
        return new GeneratedDataCreator<>(() -> this.dataSource.getTransactionManager().getTransactions()
                .stream().filter(t -> t.getAccount().equals(account)).collect(Collectors.toSet()),
                c -> this.toPairSet(c.collect(Collectors.toMap(t -> t.getCategory().getName(),
                        Transaction::getAmount, Integer::sum))));
    }

    @Override
    public final DataCreator<Transaction, Pair<String, Integer>> dailyExpenses() {
        return new DataCreatorImpl<>(this.dataSource.getTransactionManager().getTransactions(),
                t -> this.toPairSet(t.collect(Collectors.toMap(
                        x -> x.getDate().toLocalDate().toString(),
                        Transaction::getAmount, Integer::sum))));
    }

    @Override
    public final DataCreator<Transaction, Pair<String, Integer>> dailyAccountExpenses(final Account account) {
        return new GeneratedDataCreator<>(() -> this.dataSource.getTransactionManager()
                .getTransactions().stream().filter(t -> t.getAccount().equals(account)).collect(Collectors.toSet()),
                t -> this.toPairSet(t.collect(Collectors.toMap(
                        x -> x.getDate().toLocalDate().toString(),
                        Transaction::getAmount, Integer::sum))));
    }
}
