package oop.focus.finance.controller;

import oop.focus.finance.model.Transaction;

public interface SubscriptionsController extends FinanceController {

    /**
     * @return the average monthly expense on subscriptions
     */
    String getYearlyExpense();

    /**
     * @return the average yearly expense on subscriptions
     */
    String getMonthlyExpense();

    /**
     * Show subscriptions in view, sorted by tipe of repetition.
     */
    void showSortedSubscriptions();

    /**
     * Stop repeating a subscription.
     *
     * @param subscription to be stopped
     */
    void stopSubscription(Transaction subscription);

    /**
     * @param transaction of which we want to see the amount
     * @return formatted transaction's amount
     */
    String getTransactionAmount(Transaction transaction);
}
