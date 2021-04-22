package oop.focus.finance.controller;

import oop.focus.common.Controller;
import oop.focus.finance.model.Transaction;

/**
 * Implementation of a controller interface that deals with suscriptions.
 */
public interface SubscriptionsController extends Controller {

    /**
     * @return the average monthly expense on subscriptions
     */
    double getYearlyExpense();

    /**
     * @return the average yearly expense on subscriptions
     */
    double getMonthlyExpense();

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
    double getTransactionAmount(Transaction transaction);
}
