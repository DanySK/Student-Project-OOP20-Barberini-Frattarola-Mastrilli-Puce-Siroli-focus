package oop.focus.statistics.controller;

import javafx.util.Pair;
import oop.focus.common.UpdatableController;
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
 * Implementation of {@link FinanceChartFactory}.
 */
public class FinanceChartFactoryImpl implements FinanceChartFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final UpdatableController<TimePeriodInput<Account>> balances(final FinanceManager manager) {
        final var factory = new FinanceStatisticFactoryImpl(manager);
        return new AbstractSingleValueChartController<>() {
            private static final String CATEGORY_TITLE = "Saldo categorie in positivo";
            private static final String ACCOUNTS_TITLE = "Saldo conti in positivo";

            @Override
            public void updateInput(final TimePeriodInput<Account> input) {
                if (input.getValues().size() == 0) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final UpdatableController<TimePeriodInput<Account>> periodExpenses(final FinanceManager manager) {
        final var factory = new FinanceStatisticFactoryImpl(manager);
        return new AbstractMultiValueChartController<>() {
            private static final String ALL_ACCOUNT_NAME = "Tutti gli account";
            public static final String ALL_ACCOUNTS_TITLE = "Movimenti giornalieri tutti i conti";
            public static final String ACCOUNTS_TITLE = "Movimenti giornalieri conti selezionati";

            @Override
            public void updateInput(final TimePeriodInput<Account> input) {
                if (input.getValues().size() == 0) {
                    this.updateWithoutAccount(input);
                    this.getChart().setTitle(ALL_ACCOUNTS_TITLE);
                } else {
                    this.updateWithAccount(input);
                    this.getChart().setTitle(ACCOUNTS_TITLE);
                }
            }

            /**
             * Updates the chart data with the given input ignoring the selected accounts.
             * The chart shows the daily expenses of all accounts in the given period.
             *
             * @param input the input.
             */
            private void updateWithoutAccount(final TimePeriodInput<Account> input) {
                final var data = factory.periodExpenses(input.getStartDate(),
                        input.getEndDate());
                this.getChart().updateData(List.of(collectData(data.get().stream()
                        .sorted(Comparator.comparing(Pair::getKey)), a -> (double) a / 100)));
                this.getChart().setNames(List.of(ALL_ACCOUNT_NAME));
            }

            /**
             * Updates the chart data with the given input using the selected accounts.
             * The chart shows, for each account, the daily expenses in the given period.
             * Each series is colored using the account getColor().
             *
             * @param input the input..
             */
            private void updateWithAccount(final TimePeriodInput<Account> input) {
                final var account = input.getValues();
                final var colors = new ArrayList<String>(account.size());
                final var accountsData = new ArrayList<List<Pair<String, Double>>>(account.size());
                final var names = new ArrayList<String>(account.size());
                account.forEach(a -> {
                    final var data = factory
                            .accountPeriodExpenses(a, input.getStartDate(), input.getEndDate());
                    accountsData.add(collectData(data.get().stream()
                            .sorted(Comparator.comparing(Pair::getKey)), x -> (double) x / 100));
                    colors.add(a.getColor());
                    names.add(a.getName());
                });
                this.getChart().updateData(accountsData);
                this.getChart().setColors(colors);
                this.getChart().setNames(names);

            }
        };
    }
}
