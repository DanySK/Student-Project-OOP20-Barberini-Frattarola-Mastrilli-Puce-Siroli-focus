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
     * Delete the account and all its transactions.
     * 
     * @param account
     */
    void removeAccount(Account account);

    /**
     * Create a category and add it to categories.
     * 
     * @param category
     */
    void addCategory(Category category);

    /**
     * Only deletes the category if there are no transactions in that category.
     * 
     * @param category
     */
    void removeCategory(Category category);

    /**
     * Create a transaction, add it to transactions and edit account's amount.
     * 
     * @param transaction
     */
    void addTransaction(Transaction transaction);

    /**
     * Delete transaction from transactions and edit account's amount.
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
