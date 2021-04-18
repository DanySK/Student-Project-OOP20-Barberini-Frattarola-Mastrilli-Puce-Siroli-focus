package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.common.Repetition;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;

import java.time.LocalDate;

/**
 * Implementation of a controller interface that deals with the creation of a new transaction.
 */
public interface NewTransactionController extends Controller {

    /**
     * Creates a new transaction and saves it in the database.
     * If possible, create the transactions to be generated automatically.
     *
     * @param description of the transaction to add
     * @param amount of the transaction to add
     * @param category of the transaction to add
     * @param account of the transaction to add
     * @param date of the transaction to add
     * @param hours of the transaction to add
     * @param minutes of the transaction to add
     * @param repetition of the transaction to add
     */
    void newTransaction(String description, double amount, Category category, Account account,
                        LocalDate date, int hours, int minutes, Repetition repetition);

    /**
     * @return a list of all categories saved in the database
     */
    ObservableList<Category> getCategories();

    /**
     * @return a list of all repetitions saved in the database
     */
    ObservableList<Repetition> getRepetitions();

    /**
     * @return a list of alla accounts saved in the database
     */
    ObservableList<Account> getAccounts();

    /**
     * @return manager of finance
     */
    FinanceManager getManager();
}
