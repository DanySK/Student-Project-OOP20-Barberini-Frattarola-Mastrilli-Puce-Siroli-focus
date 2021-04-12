package oop.focus.finance.controller;

import oop.focus.common.Controller;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.Transaction;

import java.util.List;

public interface FinanceHomePageController extends Controller {

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
     * @return finance manager
     */
    FinanceManager getManager();
}
