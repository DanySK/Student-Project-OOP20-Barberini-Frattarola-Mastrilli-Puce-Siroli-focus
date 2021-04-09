package oop.focus.finance.view.windows;

import javafx.scene.control.ButtonType;
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
        var result = super.confirm("Sicuro di voler elminare la transazione?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getController().deleteTransaction(super.getX());
        }
        this.close();
    }
}
