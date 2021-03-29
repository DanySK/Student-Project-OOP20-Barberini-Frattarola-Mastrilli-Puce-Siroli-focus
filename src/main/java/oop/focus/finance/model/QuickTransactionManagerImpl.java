package oop.focus.finance.model;

import javafx.collections.ObservableList;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class QuickTransactionManagerImpl implements QuickTransactionManager {

    private final Dao<QuickTransaction> quickTransactions;

    public QuickTransactionManagerImpl(final DataSource db) {
        this.quickTransactions = db.getQuickTransactions();
    }

    @Override
    public final void add(final QuickTransaction quickTransaction) {
        try {
            this.quickTransactions.save(quickTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void remove(final QuickTransaction quickTransaction) {
        try {
            this.quickTransactions.delete(quickTransaction);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final ObservableList<QuickTransaction> getQuickTransactions() {
        return this.quickTransactions.getAll();
    }
}
