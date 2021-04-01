package oop.focus.finance.model;

import javafx.collections.ObservableSet;

/**
 * Interface that models a quick transaction manager,
 * working on a quick transaction list and managing database operations.
 */
public interface QuickTransactionManager {

    /**
     * Adds a quick transaction and saves it in the database.
     * It is not possible to add a transaction at a future date.
     *
     * @param quickTransaction that is saved
     */
    void add(QuickTransaction quickTransaction);

    /**
     * Removes a quick transaction and deletes it from the database.
     *
     * @param quickTransaction being deleted
     */
    void remove(QuickTransaction quickTransaction);

    /**
     * @return the list of all quick transactions
     */
    ObservableSet<QuickTransaction> getQuickTransactions();
}
