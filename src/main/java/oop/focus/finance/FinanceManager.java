package oop.focus.finance;

import java.util.List;

public interface FinanceManager {

    /**
     * Create an account and add it to accounts.
     * 
     * @param account
     */
    void addAccount(Account account);

    /**
     * Create a category and add it to categories.
     * 
     * @param category
     */
    void addCategory(Category category);

    /**
     * Create a transaction, add it to transactions and edit account's amount.
     * 
     * @param transaction
     */
    void addTransaction(Transaction transaction);

    /**
     * Remove transaction from transactions and edit account's amount.
     * 
     * @param transaction
     */
    void removeTransaction(Transaction transaction);

    /**
     * @return accounts' list
     */
    List<Account> getAccounts();

    /**
     * @return categories' list
     */
    List<Category> getCategories();

    /**
     * @return transactions' list
     */
    List<Transaction> getTransactions();

}
