package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

/**
 * Implementation of a controller interface that deals with transactions.
 */
public interface TransactionsController extends Controller {

    /**
     * Notify view to show account's transactions, sorted by time.
     * @param predicate whose transactions we want to see
     */
    void showTransactions(Predicate<Account> predicate);

    /**
     * Delete a transaction.
     * @param transaction to e deleted
     */
    void deleteTransaction(Transaction transaction);

    /**
     * Delete the account or accounts displayed.
     * Show all accounts view.
     */
    void deleteAccounts();

    /**
     * @param predicate filter the account whose amount we want to see
     * @return returns the account amount indicated in the predicate
     */
    double getAmount(Predicate<Account> predicate);

    /**
     * @return the account or accounts displayed name.
     */
    String getAccountName();

    /**
     * @param predicate whose color we want to return
     * @return the color of the account passing the predicate, returns white if the satisfied accounts are more than one
     */
    String getColor(Predicate<Account> predicate);

    /**
     * @return a ObservableSet of all accounts
     */
    ObservableSet<Account> getAccounts();

    /**
     * @return manager of finance
     */
    FinanceManager getManager();
}
