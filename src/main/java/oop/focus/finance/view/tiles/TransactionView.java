package oop.focus.finance.view.tiles;

import oop.focus.common.View;
import oop.focus.finance.model.Transaction;

/**
 * Interface that implements the view of a transaction, showing the main details.
 */
public interface TransactionView extends View {

    /**
     * @return the transaction referenced by the view
     */
    Transaction getTransaction();
}
