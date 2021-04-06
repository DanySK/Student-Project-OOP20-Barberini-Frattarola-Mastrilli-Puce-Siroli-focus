package oop.focus.finance.view;

import oop.focus.common.View;
import oop.focus.finance.model.Transaction;

import java.util.List;

public interface SubscriptionsView extends View {

    /**
     * Show subscriptions in the subcriptionsScroll.
     *
     * @param subscriptions to be showed
     */
    void showSubscriptions(List<Transaction> subscriptions);
}
