package oop.focus.finance.view.bases;

import oop.focus.finance.model.Transaction;

import java.util.List;

/**
 * Interface that implements the subscriptions view.
 */
public interface SubscriptionsView extends FinanceView {

    /**
     * Show subscriptions in the subscriptionsScroll.
     *
     * @param subscriptions to be showed
     */
    void showSubscriptions(List<Transaction> subscriptions);
}
