package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

public interface TransactionsController extends Controller {

    /**
     * Notify view to show account's transactions, sorted by time.
     * @param predicate whose transactions we want to see
     */
    void showTransactions(Predicate<Account> predicate);

    /**
     * Save the account in the database.
     *
     * @param name of the account to save
     * @param amount of the account to save
     * @param color of the account to save
     */
    void newAccount(String name, String color, double amount);

    /**
     * Delete the account or accounts displayed.
     * Show all accounts view.
     */
    void deleteAccounts();

    /**
     * Delete a transaction.
     * @param transaction to e deleted
     */
    void deleteTransaction(Transaction transaction);

    /**
     * @return the account or accounts displayed name.
     */
    String getAccountName();

    /**
     * @param predicate filter the account whose amount we want to see
     * @return returns the account amount indicated in the predicate
     */
    String getAmount(Predicate<Account> predicate);

    /**
     * @param predicate whose color we want to return
     * @return the color of the account passing the predicate, returns white if the satisfied accounts are more than one
     */
    String getColor(Predicate<Account> predicate);

    /**
     * @return a ObservableSet of all accounts
     */
    ObservableSet<Account> getAccounts();
}
