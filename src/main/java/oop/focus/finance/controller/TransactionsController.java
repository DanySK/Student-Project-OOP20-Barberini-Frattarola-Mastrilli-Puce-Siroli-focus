package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import oop.focus.finance.model.Account;

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
}
