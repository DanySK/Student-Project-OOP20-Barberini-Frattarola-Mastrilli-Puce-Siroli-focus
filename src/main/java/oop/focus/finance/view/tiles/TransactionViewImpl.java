package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.bases.GenericView;

/**
 * Class that implements the view of a transaction, showing the main details.
 */
public class TransactionViewImpl extends GenericView<Transaction> implements Initializable, TransactionView {

    @FXML
    private Label descriptionLabel, categoryLabel, colorLabel, dateLabel, amountLabel, minusLabel;

    public TransactionViewImpl(final Transaction transaction) {
        super(transaction, FXMLPaths.MOVTILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.descriptionLabel.setText(super.getX().getDescription());
        this.categoryLabel.setText(super.getX().getCategory().getName());
        this.colorLabel.setTextFill(Color.valueOf(super.getX().getCategory().getColor()));
        this.dateLabel.setText(super.getX().getDateToString());
        this.amountLabel.setText(this.format((double) Math.abs(super.getX().getAmount()) / 100));
        this.amountLabel.setTextFill(Color.valueOf(super.getX().getAmount() > 0 ? "008f39" : "cc0605"));
        this.minusLabel.setVisible(super.getX().getAmount() < 0);
    }

    @Override
    public final Transaction getTransaction() {
        return super.getX();
    }
}
