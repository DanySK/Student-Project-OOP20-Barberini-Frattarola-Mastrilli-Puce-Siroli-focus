package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Interface that models a transaction manager,
 * working on all transactions and managing database operations.
 */
public interface TransactionManager {

    /**
     * Saves a transaction in the database.
     * It is not possible to add a transaction at a future date.
     *
     * @param transaction that is saved
     */
    void add(Transaction transaction);

    /**
     * Removes a transaction from the database.
     * 
     * @param transaction being deleted
     */
    void remove(Transaction transaction);

    /**
     * Updates the data of a transaction in the database.
     *
     * @param transaction geing updated
     */
    void update(Transaction transaction);

    /**
     * Stop repetition of a subscription and update it in the database.
     *
     * @param subscription being stopped
     */
    void stopRepeat(Transaction subscription);

    /**
     * @return the list of all transactions
     */
    ObservableSet<Transaction> getTransactions();

    /**
     * @return positive transactions' list
     */
    List<Transaction> getIncomes();

    /**
     * @return negative transactions' list
     */
    List<Transaction> getOutings();

    /**
     * @return subscriptions' list
     */
    List<Transaction> getSubscriptions();

    /**
     * @param date until it is time to calculate
     * @return a list of transactions generated by their repetition
     */
    List<Transaction> getGeneratedTransactions(LocalDate date);

    /**
     * @return the total monthly expenditure due to subscriptions
     */
    int monthlyExpense();

    /**
     * @return the total yearly expenditure due to subscriptions
     */
    int yearlyExpense();
}
