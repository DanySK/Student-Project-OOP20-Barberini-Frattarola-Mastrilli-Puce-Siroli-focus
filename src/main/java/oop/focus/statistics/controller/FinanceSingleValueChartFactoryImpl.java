package oop.focus.statistics.controller;

import javafx.util.Pair;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.model.FinanceStatisticFactoryImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static oop.focus.statistics.model.FinanceStatisticFactoryImpl.collectData;

/**
 * Implementation of {@link FinanceSingleValueChartFactory}.
 */
public class FinanceSingleValueChartFactoryImpl implements FinanceSingleValueChartFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final ChartController<FinanceInput> balances(final FinanceManager manager) {
        final var factory = new FinanceStatisticFactoryImpl(manager);
        return new AbstractPieChartController<>() {
            private static final String CATEGORY_TITLE = "Saldo categorie in positivo";
            private static final String ACCOUNTS_TITLE = "Saldo conti in positivo";

            @Override
            public void updateInput(final FinanceInput input) {
                if (input.getAccount().size() == 0) {
                    this.getChart().setTitle(CATEGORY_TITLE);
                    final var data = factory.categoryBalances().get();
                    final Comparator<Pair<Category, Integer>> sort = Comparator.comparing(a -> a.getKey().getName());
                    this.updatePie(data, sort, Category::getName, Category::getColor);
                } else {
                    this.getChart().setTitle(ACCOUNTS_TITLE);
                    final var data = factory.accountBalances().get();
                    final Comparator<Pair<Account, Integer>> sort = Comparator.comparing(a -> a.getKey().getName());
                    this.updatePie(data, sort, Account::getName, Account::getColor);
                }
            }
            private <X> void updatePie(final Set<Pair<X, Integer>> data, final Comparator<Pair<X, Integer>> sort,
                                       final Function<X, String> stringMapper, final Function<X, String> colorMapper) {
                final List<String> categoryColors = new ArrayList<>();
                this.getChart().updateData(collectData(data.stream().sorted(sort)
                                .filter(a -> a.getValue() >= 0)
                                .peek(a -> categoryColors.add(colorMapper.apply(a.getKey())))
                                .map(p -> new Pair<>(stringMapper.apply(p.getKey()), p.getValue())),
                        x -> (double) x / 100));
                this.getChart().setColors(categoryColors);
            }
        };
    }
}
