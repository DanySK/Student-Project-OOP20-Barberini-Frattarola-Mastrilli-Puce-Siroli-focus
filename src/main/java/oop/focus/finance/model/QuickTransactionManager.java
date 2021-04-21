package oop.focus.finance.model;

/**
 * Interface that models a quick transaction manager,
 * working on all quick transactions and managing database operations.
 */
public interface QuickTransactionManager extends Manager<QuickTransaction> {

    /**
     * Removes all quick transactions.
     */
    void reset();
}
