
package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.StaticFormats;
import oop.focus.finance.view.bases.GenericView;

/**
 * Class that implements the view of a transaction, showing the main details.
 */
public class TransactionViewImpl extends GenericView implements Initializable, TransactionView {

    @FXML
    private Label descriptionLabel, categoryLabel, colorLabel, dateLabel, amountLabel, minusLabel;

    private final Transaction transaction;

    public TransactionViewImpl(final Transaction transaction) {
        this.transaction = transaction;
        this.loadFXML(FXMLPaths.MOVTILE);
        this.getRoot().getStyleClass().add("generic_tile");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.descriptionLabel.setText(this.transaction.getDescription());
        this.categoryLabel.setText(this.transaction.getCategory().getName());
        this.colorLabel.setTextFill(Color.valueOf(this.transaction.getCategory().getColor()));
        this.dateLabel.setText(StaticFormats.formatDate(this.transaction.getDate()));
        this.amountLabel.setText(StaticFormats.formatAmount((double) Math.abs(this.transaction.getAmount()) / 100));
        this.amountLabel.setTextFill(Color.valueOf(this.transaction.getAmount() > 0 ? "008f39" : "cc0605"));
        this.minusLabel.setVisible(this.transaction.getAmount() < 0);
    }

    @Override
    public final Transaction getTransaction() {
        return this.transaction;
    }
}
