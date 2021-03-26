package oop.focus.finance.model;

import org.joda.time.LocalDate;

/**
 * Interface that models a finance manager,
 * coordinating the four managers of transactions, quick transaction, accounts and categories.
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
     * Given as a parameter, an account returns its current amount.
     *
     * @param account whose amount we want to know
     * @return account's current amount
     */
    int getAmount(Account account);

    /**
     * Delete transaction from transactions and edit account's amount.
     * 
     * @param transaction that is removed
     */
    void removeTransaction(Transaction transaction);

    /**
     * Do a quick transaction, then a new transaction is created with the current date as its date.
     *
     * @param quickTransaction - that is executed
     */
    void doQuickTransaction(QuickTransaction quickTransaction);

    /**
     * Check if repeat transactions are to be generated, and if so generate them.
     * @param date until it is time to calculate
     */
    void generateRepeatedTransactions(LocalDate date);

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

    /**
     * @return quick transaction manager
     */
    QuickTransactionManager getQuickManager();
}
