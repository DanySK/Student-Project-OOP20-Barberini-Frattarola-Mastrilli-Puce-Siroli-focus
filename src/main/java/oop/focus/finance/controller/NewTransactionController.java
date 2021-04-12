package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.common.Repetition;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;

import java.time.LocalDate;

public interface NewTransactionController extends Controller {

    /**
     * Creates a new transaction and saves it in the database.
     *
     * @param description of the transaction to add
     * @param amount of the transaction to add
     * @param category of the transaction to add
     * @param account of the transaction to add
     * @param date of the transaction to add (default today)
     * @param hours of the transaction to add (default current hour)
     * @param minutes of the transaction to add (default current minutes)
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
}
