package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.Transaction;
import org.joda.time.LocalDate;

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
    DataCreator<Account, Pair<Account, Integer>> accountBalances();

    /**
     * Creates a DataCreator that maps each Transaction to a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific Category.
     *
     * @return the data creator
     */
    DataCreator<Transaction, Pair<Category, Integer>> categoryBalances();

    /**
     * Creates a DataCreator that maps each Transaction of a specific Account to
     * a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific Category.
     *
     * @param account the account
     * @return the data creator
     */
    DataCreator<Transaction, Pair<Category, Integer>> accountCategoryBalances(Account account);

    /**
     * Creates a DataCreator that maps each Transaction to
     * a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific day.
     *
     * @return the data creator
     */
    DataCreator<Transaction, Pair<LocalDate, Integer>> dailyExpenses();

    /**
     * Creates a DataCreator that maps each Transaction of a specific account to
     * a {@link Pair} of String and Integer.
     * Each pair represents the balance for a specific day.
     *
     * @param account the account
     * @return the data creator
     */
    DataCreator<Transaction, Pair<LocalDate, Integer>> dailyAccountExpenses(Account account);

    /**
     * Creates a DataCreator that maps each day between
     * a start date (inclusive) and and an end date (inclusive),
     * to a {@link Pair} of {@link LocalDate} and Integer.
     * Each pair represents the sum of the transactions made in a specific day.
     *
     * @param start the start date (inclusive)
     * @param end   the end date (inclusive)
     * @return the data creator
     */
    DataCreator<LocalDate, Pair<LocalDate, Integer>> periodExpenses(LocalDate start, LocalDate end);

    /**
     * Creates a DataCreator that maps each day between
     * a start date (inclusive) and and an end date (inclusive),
     * to a {@link Pair} of {@link LocalDate} and Integer.
     * Each pair represents the sum of the transactions of the specified account, made in a specific day.
     *
     * @param account the account
     * @param start   the start date (inclusive)
     * @param end     the end date (inclusive)
     * @return the data creator
     */
    DataCreator<LocalDate, Pair<LocalDate, Integer>> accountPeriodExpenses(Account account, LocalDate start, LocalDate end);
}
