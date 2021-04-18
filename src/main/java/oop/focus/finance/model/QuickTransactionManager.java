package oop.focus.finance.model;

import javafx.collections.ObservableSet;

/**
 * Interface that models a quick transaction manager,
 * working on all quick transactions and managing database operations.
 */
public interface QuickTransactionManager {

    /**
     * Saves a quick transaction in the database.
     * It is not possible to add a transaction at a future date.
     *
     * @param quickTransaction that is saved
     */
    void add(QuickTransaction quickTransaction);

    /**
     * Removes a quick transaction from the database.
     *
     * @param quickTransaction being deleted
     */
    void remove(QuickTransaction quickTransaction);

    /**
     * Removes all quick transactions.
     */
    void reset();

    /**
     * @return the list of all quick transactions
     */
    ObservableSet<QuickTransaction> getQuickTransactions();
}
