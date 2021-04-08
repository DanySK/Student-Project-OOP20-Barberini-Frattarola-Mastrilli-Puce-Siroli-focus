package oop.focus.finance.view.tiles;

import oop.focus.common.View;
import oop.focus.finance.model.GroupTransaction;

public interface GroupTransactionView extends View {

    /**
     * @return the group transaction referenced by the view
     */
    GroupTransaction getTransaction();
}
