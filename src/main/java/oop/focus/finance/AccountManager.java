package oop.focus.finance;

import java.util.List;

/**
 * Interface that models an account manager,
 * working on an account list and managing database operations.
 */
public interface AccountManager {

    /**
     * Adds an account and saves it in the database.
     * 
     * @param account
     */
    void add(Account account);

    /**
     * Removes an account and deletes it from the database.
     * 
     * @param account
     */
    void remove(Account account);

    /**
     * @return the list of all accounts.
     */
    List<Account> getAccounts();
}
