package oop.focus.finance.view;

import oop.focus.finance.model.GroupTransaction;

public interface GroupTransactionView extends View {

    /**
     * @return the group transaction referenced by the view
     */
    GroupTransaction getTransaction();
}
