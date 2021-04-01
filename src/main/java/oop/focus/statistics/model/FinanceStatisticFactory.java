package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

/**
 * The interface Statistic factory provides methods to create different DataCreators elements for the finances context.
 */
public interface FinanceStatisticFactory {
    /**
     * Creates a DataCreator that maps each Account to a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific Account name.
     *
     * @return the data creator
     */
    DataCreator<Account, Pair<String, Integer>> accountBalances();

    /**
     * Creates a DataCreator that maps each Transaction to a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific Category name.
     *
     * @return the data creator
     */
    DataCreator<Transaction, Pair<String, Integer>> categoryBalances();

    /**
     * Creates a DataCreator that maps each Transaction of a specific Account to
     * a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific Category name.
     *
     * @param account the account
     * @return the data creator
     */
    DataCreator<Transaction, Pair<String, Integer>> accountCategoryBalances(Account account);
    /**
     * Creates a DataCreator that maps each Transaction to
     * a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific day.
     *
     * @return the data creator
     */
    DataCreator<Transaction, Pair<String, Integer>> dailyExpenses();
    /**
     * Creates a DataCreator that maps each Transaction of a specific account to
     * a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific day.
     *
     * @param account the account
     * @return the data creator
     */
    DataCreator<Transaction, Pair<String, Integer>> dailyAccountExpenses(Account account);
}
