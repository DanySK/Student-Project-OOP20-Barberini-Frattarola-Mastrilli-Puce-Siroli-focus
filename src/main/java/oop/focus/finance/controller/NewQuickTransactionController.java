package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.FinanceManager;

/**
 * Implementation of a controller interface that deals with the creation of a new quick transaction.
 */
public interface NewQuickTransactionController extends Controller {

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
     * @return a list of all categories saved in the database
     */
    ObservableList<Category> getCategories();

    /**
     * @return a list of alla accounts saved in the database
     */
    ObservableList<Account> getAccounts();

    /**
     * @return manager of finance
     */
    FinanceManager getManager();
}
