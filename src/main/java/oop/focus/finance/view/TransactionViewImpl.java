package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class TransactionViewImpl extends VBox implements Initializable, TransactionView {

    @FXML
    private Label descriptionLabel, categoryLabel, dateLabel, amountLabel;

    private final Transaction transaction;
    private Parent root;

    public TransactionViewImpl(final Transaction transaction) {
        this.transaction = transaction;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.MOVTILE.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.descriptionLabel.setText(this.transaction.getDescription());
        this.categoryLabel.setText(this.transaction.getCategory().getName());
        this.dateLabel.setText(this.transaction.getDateToString());
        DecimalFormat df = new DecimalFormat("#.00");
        this.amountLabel.setText("E " + df.format((double) this.transaction.getAmount() / 100));
    }

    @Override
    public final Transaction getTransaction() {
        return this.transaction;
    }
}
