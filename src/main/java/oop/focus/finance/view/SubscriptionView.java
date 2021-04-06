package oop.focus.finance.view;

import oop.focus.common.View;
import oop.focus.finance.model.Transaction;

public interface SubscriptionView extends View {

    /**
     * @return the subscription referenced by the view
     */
    Transaction getSubscription();
}
