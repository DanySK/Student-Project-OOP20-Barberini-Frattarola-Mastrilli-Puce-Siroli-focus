package oop.focus.finance;

import java.util.List;

/**
 * Interface that models a transaction manager,
 * working on a transaction list and managing database operations.
 */
public interface TransactionManager {

    /**
     * Adds a transaction and saves it in the database.
     * 
     * @param transaction
     */
    void add(Transaction transaction);

    /**
     * Removes a transaction and deletes it from the database.
     * 
     * @param transaction
     */
    void remove(Transaction transaction);

    /**
     * Removes all transactions from a list and deletes them from the database.
     * 
     * @param transactions
     */
    void removeAll(List<Transaction> transactions);

    /**
     * @return the list of all transactions.
     */
    List<Transaction> getTransactions();

    /**
     * @return the total monthly expenditure due to subscriptions
     */
    int monthlyExpense();

    /**
     * @return the total yearly expenditure due to subscriptions
     */
    int yearlyExpense();

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
}
