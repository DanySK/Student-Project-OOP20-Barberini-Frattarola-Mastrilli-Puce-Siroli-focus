package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

public interface TransactionsController extends FinanceController {

    /**
     * @param predicate filter the account whose amount we want to see
     * @return returns the account amount indicated in the predicate
     */
    double getAmount(Predicate<Account> predicate);

    /**
     * @return a ObservableSet of all accounts
     */
    ObservableSet<Account> getAccounts();

    /**
     * Notify view to show account's transactions.
     * @param predicate whose transactions we want to see
     */
    void showTransactions(Predicate<Account> predicate);

    /**
     * Delete a transaction.
     * @param transaction to e deleted
     */
    void deleteTransaction(Transaction transaction);

    /**
     * Save the account in the database.
     *
     * @param name of the account to save
     * @param amount of the account to save
     * @param color of the account to save
     */
    void newAccount(String name, String color, int amount);

    /**
     * @return colors saved in the database
     */
    ObservableList<String> getColors();
}
