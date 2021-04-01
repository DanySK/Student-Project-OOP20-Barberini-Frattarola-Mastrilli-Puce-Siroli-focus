package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.scene.Parent;
import oop.focus.finance.model.Account;

public interface TransactionsController {

    /**
     * @return the total amount of all accounts
     */
    double getAmount();

    /**
     * @return a ObservableSet of all accounts
     */
    ObservableSet<Account> getAccounts();

    /**
     * Notify the view to show all transactions.
     */
    void showTransactions();

    /**
     * Notify view to show account's transactions.
     * @param account whose transactions we want to see
     */
    void showTransactions(Account account);

    /**
     * @return the root of TransactionsView
     */
    Parent getView();

}
