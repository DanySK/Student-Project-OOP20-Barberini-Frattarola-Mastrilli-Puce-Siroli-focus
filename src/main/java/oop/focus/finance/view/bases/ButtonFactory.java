package oop.focus.finance.view.bases;

import oop.focus.finance.controller.ChangeViewController;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

public interface ButtonFactory {

    /**
     * @param controller of finance
     * @param name of the button
     * @param predicate filter the transactions we want to view
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of filtered transactions
     */
    FinanceMenuButton<ChangeViewController> getTransactions(ChangeViewController controller, String name,
                                                            Predicate<Transaction> predicate, FinanceManager manager);

    /**
     * @param controller of finance
     * @param name of the button
     * @param manager of finance
     * @return a FinanceMenuButton that has as its action the visualization of finance statistic
     */
    FinanceMenuButton<ChangeViewController> getStatistics(ChangeViewController controller, String name, FinanceManager manager);

    /**
     * @param controller of finance
     * @param name of the button
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of SubscriptionsView
     */
    FinanceMenuButton<ChangeViewController> getSubscriptions(ChangeViewController controller, String name, FinanceManager manager);

    /**
     * @param controller of group
     * @param name of the button
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of GroupView
     */
    FinanceMenuButton<ChangeViewController> getGroupTransactions(ChangeViewController controller, String name, FinanceManager manager);

    /**
     * @param controller of transactions
     * @param account whose transactions we want to see
     * @return a FinanceMenuButton that has as its action the visualization of account's transactions
     */
    FinanceMenuButton<TransactionsController> getAccountTransactions(TransactionsController controller, Account account);

    /**
     * @param controller of transactions
     * @return a FinanceMenuButton that has as its action the visualization of every transactions
     */
    FinanceMenuButton<TransactionsController> getAccountTransactions(TransactionsController controller);

}
