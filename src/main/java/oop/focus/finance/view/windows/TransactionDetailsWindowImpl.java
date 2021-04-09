package oop.focus.finance.view.windows;

import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Transaction;

public class TransactionDetailsWindowImpl extends GenericDetailsWindow<TransactionsController> {

    public TransactionDetailsWindowImpl(final TransactionsController controller, final Transaction transaction) {
        super(controller, transaction, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void edits() { }

    @Override
    public final void save() {
        super.getController().deleteTransaction(super.getX());
        this.close();
    }
}
