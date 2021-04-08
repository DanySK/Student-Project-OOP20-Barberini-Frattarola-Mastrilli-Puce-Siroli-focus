package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.Repetition;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.Transaction;
import org.joda.time.LocalDateTime;

import java.util.List;

public interface FinanceHomePageController extends FinanceController {

    /**
     * @return a list of all categories saved in the database
     */
    ObservableList<Category> getCategories();

    /**
     * @return a list of all accounts saved in the database
     */
    ObservableList<Account> getAccounts();

    /**
     * @return a list of all repetitions saved in the database
     */
    ObservableList<Repetition> getRepetitions();

    /**
     * @return a list of all quick transactions saved in the database
     */
    ObservableSet<QuickTransaction> getQuickTransactions();

    /**
     * @return a list of transactions performed today saved in the database
     */
    List<Transaction> getTodayTransactions();

    /**
     * Creates a new transaction and saves it in the database.
     *
     * @param description of the transaction to add
     * @param amount of the transaction to add
     * @param category of the transaction to add
     * @param account of the transaction to add
     * @param date of the transaction to add
     * @param repetition of the transaction to add
     */
    void newTransaction(String description, double amount, Category category, Account account, LocalDateTime date,
                        Repetition repetition);

    /**
     * Given as a parameter, an account returns its current amount.
     *
     * @param account whose amount we want to know
     * @return account's current amount
     */
    int getAmount(Account account);

    /**
     * @return the total amount of all accounts
     */
    double getTotalAmount();

    /**
     * Do a quick transaction, then a new transaction is created with the current date as its date.
     *
     * @param quickTransaction - that is executed
     */
    void doQuickTransaction(QuickTransaction quickTransaction);

}
