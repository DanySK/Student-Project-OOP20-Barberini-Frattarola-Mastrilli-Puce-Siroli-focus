package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.common.Repetition;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface FinanceHomePageController extends Controller {

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
     * Creates a new quick transaction and saves it in the database.
     *
     * @param description of the quick transaction to add
     * @param amount of the quick transaction to add
     * @param category of the quick transaction to add
     * @param account of the quick transaction to add
     */
    void newQuickTransaction(String description, double amount, Category category, Account account);

    /**
     * Do a quick transaction, then a new transaction is created with the current date as its date.
     *
     * @param quickTransaction - that is executed
     */
    void doQuickTransaction(QuickTransaction quickTransaction);

    /**
     * @param account whose amount we want to know
     * @return account's current amount in euro
     */
    double getAmount(Account account);

    /**
     * @return the total amount of all accounts in euro
     */
    double getTotalAmount();

    /**
     * @return a list of transactions performed today saved in the database sorted by time
     */
    List<Transaction> getSortedTodayTransactions();

    /**
     * @return a list of all quick transactions saved in the database sorted by description
     */
    List<QuickTransaction> getSortedQuickTransactions();

    /**
     * @return a list of all accounts saved in the database sorted by name.
     */
    List<Account> getSortedAccounts();

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
