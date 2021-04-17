package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

import java.util.List;

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
    public final void reset() {
        List.copyOf(this.quickTransactions.getAll()).forEach(this::remove);
    }

    @Override
    public final ObservableSet<QuickTransaction> getQuickTransactions() {
        return this.quickTransactions.getAll();
    }
}
