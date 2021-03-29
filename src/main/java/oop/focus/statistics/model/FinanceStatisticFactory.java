package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.finance.model.Account;

public interface FinanceStatisticFactory {
    DataCreator<Account, Pair<String, Integer>> accountBalances();
}
