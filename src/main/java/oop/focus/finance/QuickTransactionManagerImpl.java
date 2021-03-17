package oop.focus.finance;

import java.util.ArrayList;
import java.util.List;

public class QuickTransactionManagerImpl implements QuickTransactionManager {

    private final List<QuickTransaction> quickTransactions = new ArrayList<>();

    @Override
    public final void add(final QuickTransaction quickTransaction) {
        this.quickTransactions.add(quickTransaction);
    }

    @Override
    public final void remove(final QuickTransaction quickTransaction) {
        this.quickTransactions.remove(quickTransaction);
    }

    @Override
    public final List<QuickTransaction> getQuickTransactions() {
        return this.quickTransactions;
    }
}
