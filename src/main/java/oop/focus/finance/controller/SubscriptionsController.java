package oop.focus.finance.controller;

public interface SubscriptionsController extends FinanceController {

    /**
     * @return the average monthly expense on subscriptions
     */
    double getYearlyExpense();

    /**
     * @return the average yearly expense on subscriptions
     */
    double getMonthlyExpense();
}
