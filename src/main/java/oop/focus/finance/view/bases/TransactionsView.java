package oop.focus.finance.view.bases;

import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

import java.util.Set;
import java.util.function.Predicate;

public interface TransactionsView extends View {

    /**
     * Shows transactions filtered by predicate.
     *
     * @param transactions to show
     * @param predicate to use for filtering transactions
     */
    void updateTransactions(Set<Transaction> transactions, Predicate<Account> predicate);
}
