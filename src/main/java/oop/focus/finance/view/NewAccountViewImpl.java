package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.finance.controller.FXMLPaths;

import oop.focus.finance.controller.TransactionsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountViewImpl implements Initializable, NewAccountView {

    @FXML
    private Pane newAccountPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField amountTextfield;
    @FXML
    private ChoiceBox<String> colorChoiche;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    private final TransactionsController controller;
    private Parent root;

    public NewAccountViewImpl(final TransactionsController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.NEWACCOUNT.getPath()));
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
        this.populate();
    }

    private void populate() {
        this.titleLabel.setText("Nuovo conto");
        this.nameLabel.setText("Nome:");
        this.amountLabel.setText("Importo:                E");
        this.colorLabel.setText("Colore:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.colorChoiche.setItems(this.controller.getColors());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void close() {
        final Stage stage = (Stage) this.newAccountPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public final void save() {
        this.controller.newAccount(this.nameTextfield.getText(), this.colorChoiche.getValue(),
                Integer.parseInt(this.amountTextfield.getText()));
        this.close();
    }

}
