package oop.focus.finance.view;

import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

public interface ButtonFactory {

    /**
     *
     * @param controller of transactions
     * @param name of the button
     * @param predicate filter the transactions we want to view
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of filtered transactions
     */
    FinanceMenuButton<BaseController> getTransactions(BaseController controller, String name,
                                      Predicate<Transaction> predicate, FinanceManager manager);

    /**
     * @param controller of subscriptions
     * @param name of the button
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of SubscriptionsView
     */
    FinanceMenuButton<BaseController> getSubscriptions(BaseController controller, String name, FinanceManager manager);

    /**
     * @param controller of group
     * @param name of the button
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of GroupView
     */
    FinanceMenuButton<BaseController> getGroupTransactions(BaseController controller, String name, FinanceManager manager);

    /**
     * @param controller of finance
     * @param account whose transactions we want to see
     * @return a FinanceMenuButton that has as its action the visualization of account's transactions
     */
    FinanceMenuButton<TransactionsController> getAccountTransactions(TransactionsController controller, Account account);

    /**
     * @param controller of finance
     * @return a FinanceMenuButton that has as its action the visualization of every transactions
     */
    FinanceMenuButton<TransactionsController> getAccountTransactions(TransactionsController controller);
}
