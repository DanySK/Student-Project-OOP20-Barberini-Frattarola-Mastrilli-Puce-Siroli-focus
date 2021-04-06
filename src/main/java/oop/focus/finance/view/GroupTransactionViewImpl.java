package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.GroupTransaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupTransactionViewImpl implements Initializable, GroupTransactionView {

    @FXML
    private Label personLabel, descriptionLabel, amountLabel;

    private final GroupTransaction transaction;
    private Parent root;

    public GroupTransactionViewImpl(final GroupTransaction transaction) {
        this.transaction = transaction;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.GROUPTILE.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.personLabel.setText(this.transaction.getMadeBy().getName() + " - > " + this.transaction.getForList());
        this.descriptionLabel.setText(this.transaction.getDescription());
        this.amountLabel.setText("E " + (double) this.transaction.getAmount() / 100);
    }

    @Override
    public final GroupTransaction getTransaction() {
        return this.transaction;
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
