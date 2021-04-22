package oop.focus.finance.view.bases;

import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface that implements the transactions view.
 */
public interface TransactionsView extends FinanceView {

    /**
     * Shows transactions filtered by predicate.
     *
     * @param transactions to show
     * @param predicate to use for filtering transactions
     */
    void updateTransactions(List<Transaction> transactions, Predicate<Account> predicate);
}
