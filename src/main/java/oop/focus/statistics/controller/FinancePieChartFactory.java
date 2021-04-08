package oop.focus.statistics.controller;

import javafx.util.Pair;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.model.FinanceStatisticFactoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static oop.focus.statistics.model.FinanceStatisticFactoryImpl.collectData;

public class FinancePieChartFactory {

    public final ChartController<FinanceInput> balances(final FinanceManager manager) {
        final var factory = new FinanceStatisticFactoryImpl(manager);
        return new AbstractPieChartController<>() {
            @Override
            public void updateInput(final FinanceInput input) {
                if (input.getAccount().size() == 0) {
                    final var data = factory.categoryBalances().get();
                    final Comparator<Pair<Category, Integer>> sort = Comparator.comparing(a -> a.getKey().getName());
                    this.updatePie(data, sort, Category::getName, Category::getColor);
                } else {
                    final var data = factory.accountBalances().get();
                    final Comparator<Pair<Account, Integer>> sort = Comparator.comparing(a -> a.getKey().getName());
                    this.updatePie(data, sort, Account::getName, Account::getColor);
                }
            }

            private <X> void updatePie(final Set<Pair<X, Integer>> data, final Comparator<Pair<X, Integer>> sort,
                                       final Function<X, String> stringMapper, final Function<X, String> colorMapper) {
                final List<String> categoryColors = data.stream().sorted(sort)
                        .filter(a -> a.getValue() >= 0)
                        .map(Pair::getKey)
                        .map(colorMapper)
                        .collect(Collectors.toList());
                this.getChart().updateData(collectData(data.stream().sorted(sort)
                        .filter(a -> a.getValue() >= 0)
                        .map(p -> new Pair<>(stringMapper.apply(p.getKey()), p.getValue()))));
                this.getChart().setColors(categoryColors);
            }
        };
    }
}
