package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionDetailsWindowImpl implements Initializable, TransactionDetailsWindow {

    @FXML
    private Pane detailsPane;
    @FXML
    private Label titleLabel, descriptionLabel, categoryLabel, dateLabel, accountLabel, amountLabel, subscriptionLabel,
            dataDescriptionLabel, dataCategoryLabel, dataDateLabel, dataAccountLabel, dataAmountLabel, dataSubscriptionLabel;
    @FXML
    private Button closeButton, deleteButton;

    private final TransactionsController controller;
    private final Transaction transaction;
    private Parent root;

    public TransactionDetailsWindowImpl(final TransactionsController controller, final Transaction transaction) {
        this.transaction = transaction;
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.TRANSACTIONDETAILS.getPath()));
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
        this.titleLabel.setText("DETTAGLI TRANSAZIONE");
        this.descriptionLabel.setText("Descrizione:");
        this.categoryLabel.setText("Categoria");
        this.dateLabel.setText("Data e ora:");
        this.accountLabel.setText("Conto");
        this.amountLabel.setText("Importo:");
        this.subscriptionLabel.setText("Abbonamento:");
        this.dataDescriptionLabel.setText(this.transaction.getDescription());
        this.dataCategoryLabel.setText(this.transaction.getCategory().getName());
        this.dataDateLabel.setText(this.transaction.getDateToString());
        this.dataAccountLabel.setText(this.transaction.getAccount().toString());
        this.dataAmountLabel.setText("E " + (double) this.transaction.getAmount() / 100);
        this.dataSubscriptionLabel.setText(this.transaction.getRepetition().equals(Repetition.ONCE) ? "No"
                : this.transaction.getRepetition().toString());
        this.deleteButton.setText("Elimina");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.delete());
        this.closeButton.setOnAction(event -> this.close());
    }

    private void delete() {
        this.controller.deleteTransaction(this.transaction);
        this.close();
    }

    private void close() {
        final Stage stage = (Stage) this.detailsPane.getScene().getWindow();
        stage.close();
    }


}
