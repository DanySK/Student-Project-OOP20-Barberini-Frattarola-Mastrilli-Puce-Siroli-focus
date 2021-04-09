package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.bases.GenericView;

import java.text.DecimalFormat;

public class GroupTransactionViewImpl extends GenericView<GroupTransaction> implements GroupTransactionView {

    @FXML
    private Label personLabel, descriptionLabel, amountLabel;

    public GroupTransactionViewImpl(final GroupTransaction transaction) {
        super(transaction, FXMLPaths.GROUPTILE);
    }

    @Override
    public final void populate() {
        this.personLabel.setText(super.getX().getMadeBy().getName() + " - > " + super.getX().getForList());
        this.descriptionLabel.setText(super.getX().getDescription());
        DecimalFormat df = new DecimalFormat("#0.00");
        this.amountLabel.setText("E " + df.format((double) super.getX().getAmount() / 100));
    }

    @Override
    public final GroupTransaction getTransaction() {
        return super.getX();
    }
}
