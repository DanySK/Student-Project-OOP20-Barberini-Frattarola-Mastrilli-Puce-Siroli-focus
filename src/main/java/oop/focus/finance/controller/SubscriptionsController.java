package oop.focus.finance.controller;

import oop.focus.finance.model.Transaction;

public interface SubscriptionsController extends FinanceController {

    /**
     * @return the average monthly expense on subscriptions
     */
    double getYearlyExpense();

    /**
     * @return the average yearly expense on subscriptions
     */
    double getMonthlyExpense();

    /**
     * Show subscriptions in view.
     */
    void showSubscriptions();

    /**
     * Stop repeating a subscription.
     *
     * @param subscription to be stopped
     */
    void stopSubscription(Transaction subscription);
}
