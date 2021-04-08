package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FinanceStatisticFactoryImpl implements FinanceStatisticFactory {

    private final FinanceManager dataSource;

    public FinanceStatisticFactoryImpl(final FinanceManager dataSource) {
        this.dataSource = dataSource;
    }

    private <X, Y> Set<Pair<X, Y>> toPairSet(final Map<X, Y> map) {
        return map.entrySet().stream().map(e -> new Pair<>(e.getKey(), e.getValue())).collect(Collectors.toSet());
    }

    @Override
    public final DataCreator<Account, Pair<Account, Integer>> accountBalances() {
        return new DataCreatorImpl<>(this.dataSource.getAccountManager().getAccounts(),
                t -> this.toPairSet(t.collect(Collectors.toMap(Function.identity(),
                        this.dataSource::getAmount))));
    }

    @Override
    public final DataCreator<Transaction, Pair<Category, Integer>> categoryBalances() {
        return new DataCreatorImpl<>(this.dataSource.getTransactionManager().getTransactions(),
                c -> this.toPairSet(c.collect(Collectors.toMap(Transaction::getCategory,
                        Transaction::getAmount, Integer::sum))));

    }

    @Override
    public final DataCreator<Transaction, Pair<Category, Integer>> accountCategoryBalances(final Account account) {
        return new GeneratedDataCreator<>(() -> this.dataSource.getTransactionManager().getTransactions()
                .stream().filter(t -> t.getAccount().equals(account)).collect(Collectors.toSet()),
                c -> this.toPairSet(c.collect(Collectors.toMap(Transaction::getCategory,
                        Transaction::getAmount, Integer::sum))));
    }

    @Override
    public final DataCreator<Transaction, Pair<LocalDate, Integer>> dailyExpenses() {
        return new DataCreatorImpl<>(this.dataSource.getTransactionManager().getTransactions(),
                t -> this.toPairSet(t.collect(Collectors.toMap(
                        x -> x.getDate().toLocalDate(),
                        Transaction::getAmount, Integer::sum))));
    }

    @Override
    public final DataCreator<Transaction, Pair<LocalDate, Integer>> dailyAccountExpenses(final Account account) {
        return new GeneratedDataCreator<>(() -> this.dataSource.getTransactionManager()
                .getTransactions().stream().filter(t -> t.getAccount().equals(account)).collect(Collectors.toSet()),
                t -> this.toPairSet(t.collect(Collectors.toMap(
                        x -> x.getDate().toLocalDate(),
                        Transaction::getAmount, Integer::sum))));
    }

    @Override
    public final DataCreator<LocalDate, Pair<LocalDate, Integer>> periodExpenses(final LocalDate start, final LocalDate end) {
        return new GeneratedDataCreator<>(
                () -> Stream.iterate(start, d -> d.plusDays(1))
                        .limit(1 + Math.abs(Days.daysBetween(start, end).getDays())).collect(Collectors.toSet()),
                s -> this.toPairSet(s.collect(Collectors.toMap(Function.identity(),
                        d -> this.dataSource.getTransactionManager().getTransactions().stream()
                                .filter(t -> t.getDate().toLocalDate().equals(d))
                                .mapToInt(Transaction::getAmount).sum()))));
    }

    @Override
    public final DataCreator<LocalDate, Pair<LocalDate, Integer>> accountPeriodExpenses(final Account account, final LocalDate start, final LocalDate end) {
        return new GeneratedDataCreator<>(
                () -> Stream.iterate(start, d -> d.plusDays(1))
                        .limit(1 + Math.abs(Days.daysBetween(start, end).getDays())).collect(Collectors.toSet()),
                s -> this.toPairSet(s.collect(Collectors.toMap(Function.identity(),
                        d -> this.dataSource.getTransactionManager().getTransactions().stream()
                                .filter(t -> t.getDate().toLocalDate().equals(d) && t.getAccount().equals(account))
                                .mapToInt(Transaction::getAmount).sum()))));
    }

    public static <X> List<Pair<String, Double>> collectData(final Stream<Pair<X, Integer>> data) {
        return data.map(p -> new Pair<>(p.getKey().toString(), (double) p.getValue() / 100))
                .collect(Collectors.toList());
    }

}
