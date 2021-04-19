package oop.focus.finance.view.bases;

import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;

import java.util.List;

/**
 * Interface that implements the subscriptions view.
 */
public interface SubscriptionsView extends FinanceView<SubscriptionsController> {

    /**
     * Show subscriptions in the subcriptionsScroll.
     *
     * @param subscriptions to be showed
     */
    void showSubscriptions(List<Transaction> subscriptions);
}
