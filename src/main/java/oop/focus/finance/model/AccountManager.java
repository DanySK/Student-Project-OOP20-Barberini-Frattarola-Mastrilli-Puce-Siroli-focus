package oop.focus.finance.model;

import javafx.collections.ObservableSet;

/**
 * Interface that models an account manager,
 * working on all accounts and managing database operations.
 */
public interface AccountManager {

    /**
     * Saves an account in the database.
     * If the color of account doesn't exist, saves it in the database too.
     * 
     * @param account that is saved
     */
    void add(Account account);

    /**
     * Removes an account from the database.
     * 
     * @param account being deleted
     */
    void remove(Account account);

    /**
     * @return the list of all accounts.
     */
    ObservableSet<Account> getAccounts();
}
