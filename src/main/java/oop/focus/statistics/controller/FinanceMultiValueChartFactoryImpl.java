package oop.focus.statistics.controller;

import javafx.util.Pair;
import oop.focus.finance.model.FinanceManager;
import oop.focus.statistics.model.FinanceStatisticFactoryImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static oop.focus.statistics.model.FinanceStatisticFactoryImpl.collectData;

/**
 * Implementation of {@link FinanceMultiValueChartFactory}.
 */
public class FinanceMultiValueChartFactoryImpl implements FinanceMultiValueChartFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final ChartController<FinanceInput> periodExpenses(final FinanceManager manager) {
        var factory = new FinanceStatisticFactoryImpl(manager);
        return new AbstractLineChartController<>() {
            private static final String ALL_ACCOUNT_NAME = "Tutti gli account";
            public static final String ALL_ACCOUNTS_TITLE = "Saldo giornaliero di tutti i conti";
            public static final String ACCOUNTS_TITLE = "Saldo giornaliero conti selezionati";

            @Override
            public void updateInput(final FinanceInput input) {
                if (input.getAccount().size() == 0) {
                    this.updateLineWithoutAccount(input);
                    this.getChart().setTitle(ALL_ACCOUNTS_TITLE);
                } else {
                    this.updateLineWithAccount(input);
                    this.getChart().setTitle(ACCOUNTS_TITLE);
                }
            }
            private void updateLineWithoutAccount(final FinanceInput input) {
                var data = factory.periodExpenses(input.getStartDate(), input.getEndDate());
                this.getChart().updateData(List.of(collectData(data.get().stream(),
                        a -> (double) a / 100)));
                this.getChart().setNames(List.of(ALL_ACCOUNT_NAME));
            }
            private void updateLineWithAccount(final FinanceInput input) {
                final var account = input.getAccount();
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
                this.getChart().setNames(names);
                this.getChart().setColors(colors);
            }
        };
    }
}
