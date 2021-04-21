package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

import java.util.List;

/**
 * Immutable implementation of a quick transaction manager.
 */
public class QuickTransactionManagerImpl implements QuickTransactionManager {

    private final Dao<QuickTransaction> quickTransactions;

    public QuickTransactionManagerImpl(final DataSource db) {
        this.quickTransactions = db.getQuickTransactions();
    }

    /**
     * Saves a quick transaction in the database.
     * It is not possible to add a transaction at a future date.
     *
     * @param quickTransaction that is saved
     */
    @Override
    public final void add(final QuickTransaction quickTransaction) {
        try {
            this.quickTransactions.save(quickTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a quick transaction from the database.
     *
     * @param quickTransaction being deleted
     */
    @Override
    public final void remove(final QuickTransaction quickTransaction) {
        try {
            this.quickTransactions.delete(quickTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void reset() {
        List.copyOf(this.quickTransactions.getAll()).forEach(this::remove);
    }

    /**
     * @return the list of all quick transactions
     */
    @Override
    public final ObservableSet<QuickTransaction> getElements() {
        return this.quickTransactions.getAll();
    }
}
