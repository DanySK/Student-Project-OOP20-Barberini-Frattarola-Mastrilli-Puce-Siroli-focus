package oop.focus.finance.model;

import oop.focus.db.DataSource;
import org.joda.time.LocalDate;

/**
 * Interface that models a finance manager,
 * coordinating the five managers of transactions, quick transaction, accounts, categories and group.
 */
public interface FinanceManager {

    /**
     * Create an account and add it to database.
     *
     * @param account that is added
     */
    void addAccount(Account account);

    /**
     * Delete the account and all its transactions from database.
     *
     * @param account that is removed
     */
    void removeAccount(Account account);

    /**
     * Create a category and add it to database.
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
     * Create a transaction add it to database.
     *
     * @param transaction that is added
     */
    void addTransaction(Transaction transaction);

    /**
     * Delete transaction from database.
     *
     * @param transaction that is removed
     */
    void removeTransaction(Transaction transaction);

    /**
     * Returns the current amount of an account.
     *
     * @param account whose amount we want to know
     * @return account's current amount
     */
    int getAmount(Account account);

    /**
     * Do a quick transaction, then a new transaction is created with the current date as its date.
     *
     * @param quickTransaction that is executed
     */
    void doQuickTransaction(QuickTransaction quickTransaction);

    /**
     * Check if repeat transactions are to be generated, and if so generate them.
     * @param date until it is time to calculate
     */
    void generateRepeatedTransactions(LocalDate date);

    /**
     * @return the DataSource
     */
    DataSource getDb();

    /**
     * @return account manager
     */
    Manager<Account> getAccountManager();

    /**
     * @return category manager
     */
    Manager<Category> getCategoryManager();

    /**
     * @return transaction manager
     */
    TransactionManager getTransactionManager();

    /**
     * @return quick transaction manager
     */
    QuickTransactionManager getQuickManager();

    /**
     * @return group manager
     */
    GroupManager getGroupManager();
}
