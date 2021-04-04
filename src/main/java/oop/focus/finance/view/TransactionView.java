package oop.focus.finance.view;
import oop.focus.finance.model.Transaction;

public interface TransactionView extends View {

    /**
     * @return the transaction referenced by the view
     */
    Transaction getTransaction();
}
