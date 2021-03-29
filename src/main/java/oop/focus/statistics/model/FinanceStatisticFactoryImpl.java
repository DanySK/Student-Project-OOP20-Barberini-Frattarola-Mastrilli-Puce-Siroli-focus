package oop.focus.statistics.model;

import javafx.collections.FXCollections;
import javafx.util.Pair;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;

import java.util.stream.Collectors;

public class FinanceStatisticFactoryImpl implements FinanceStatisticFactory {

    private final FinanceManager dataSource;

    public FinanceStatisticFactoryImpl(final FinanceManager dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public final DataCreator<Account, Pair<String, Integer>> accountBalances() {
        return new DataCreatorImpl<>(
                FXCollections.observableList(this.dataSource.getAccountManager().getAccounts()),
                t -> t.distinct().collect(Collectors.toMap(Account::getName, this.dataSource::getAmount))
                        .entrySet().stream().map(e -> new Pair<>(e.getKey(), e.getValue())).collect(Collectors.toSet()));
    }

}
