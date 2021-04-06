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
import oop.focus.finance.controller.GroupController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewPersonViewImpl implements Initializable, FinanceWindow {

    @FXML
    private Pane newPersonPane;
    @FXML
    private Label titleLabel, nameLabel, relationshipLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<String> relationshipChoice;
    @FXML
    private Button cancelButton, saveButton;

    private final GroupController controller;
    private Parent root;

    public NewPersonViewImpl(final GroupController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.NEWPERSON.getPath()));
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
        this.titleLabel.setText("Nuova persona");
        this.nameLabel.setText("Nome:");
        this.relationshipLabel.setText("Relazione:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.relationshipChoice.setItems(this.controller.getRelationships());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void close() {
        final Stage stage = (Stage) this.newPersonPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public final void save() {
        this.controller.newPerson(this.nameTextField.getText(), this.relationshipChoice.getValue());
        this.close();
    }

}
