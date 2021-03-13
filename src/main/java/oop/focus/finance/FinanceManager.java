package oop.focus.finance;

/**
 * Interface that models a finance manager,
 * coordinating the three managers of transactions, accounts and categories.
 */
public interface FinanceManager {

    /**
     * Create an account and add it to accounts.
     * 
     * @param account that is added
     */
    void addAccount(Account account);

    /**
     * Delete the account and all its transactions.
     * 
     * @param account that is removed
     */
    void removeAccount(Account account);

    /**
     * Create a category and add it to categories.
     * 
     * @param category that is added
     */
    void addCategory(Category category);

    /**
     * Only deletes the category if there are no transactions in that category.
     * 
     * @param category that is removed
     */
    void removeCategory(Category category);

    /**
     * Create a transaction, add it to transactions and edit account's amount.
     * 
     * @param transaction that is added
     */
    void addTransaction(Transaction transaction);

    /**
     * Delete transaction from transactions and edit account's amount.
     * 
     * @param transaction that is removed
     */
    void removeTransaction(Transaction transaction);

    /**
     * Check if repeat transactions are to be generated, and if so generate them.
     */
    void generateRepeatedTransactions();

    /**
     * @return account manager
     */
    AccountManager getAccountManager();

    /**
     * @return category manager
     */
    CategoryManager getCategoryManager();

    /**
     * @return transaction manager
     */
    TransactionManager getTransactionManager();
}
