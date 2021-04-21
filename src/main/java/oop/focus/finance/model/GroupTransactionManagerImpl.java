package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

/**
 * Class that implements group transaction management.
 */
public class GroupTransactionManagerImpl implements Manager<GroupTransaction> {

    private final Dao<GroupTransaction> transactions;

    public GroupTransactionManagerImpl(final DataSource db) {
        this.transactions = db.getGroupTransactions();
    }

    /**
     * Saves a group transaction in the database.
     *
     * @param groupTransaction added to group transactions
     */
    @Override
    public final void add(final GroupTransaction groupTransaction) {
        try {
            this.transactions.save(groupTransaction);
        } catch (final DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a group transaction from the database.
     *
     * @param groupTransaction removed from group transactions
     */
    @Override
    public final void remove(final GroupTransaction groupTransaction) {
        try {
            this.transactions.delete(groupTransaction);
        } catch (final DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the list of group transactions
     */
    @Override
    public final ObservableSet<GroupTransaction> getElements() {
        return this.transactions.getAll();
    }

}
