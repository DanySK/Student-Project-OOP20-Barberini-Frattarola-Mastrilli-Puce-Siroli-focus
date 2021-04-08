package oop.focus.homepage.view;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.homepage.controller.PersonsController;
import oop.focus.homepage.model.PersonImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewPersonView implements Initializable, View {
    @FXML
    private Button save;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private AnchorPane newPersonPane;

    @FXML
    private Label name;

    @FXML
    private Label degree;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<String> degreeComboBox;

    @FXML
    private Label newPerson;
    private PersonsController controller;
    private Parent root;

    public AddNewPersonView(final PersonsController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/addNewPerson.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.degreeComboBox.setItems(this.controller.getDegree());

        save.setOnAction(event -> this.save());
        delete.setOnAction(event -> this.delete());
        back.setOnAction(event -> this.goBack());
    }

    private void goBack() {
        final Stage stage = (Stage) this.newPersonPane.getScene().getWindow();
        stage.close();
    }

    private void save() {
        if (!this.nameTextField.getText().isEmpty() && !this.degreeComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            this.controller.addPerson(new PersonImpl(this.nameTextField.getText(), this.degreeComboBox.getSelectionModel().getSelectedItem()));
            this.goBack();
        } else {
        final AllertGenerator allert = new AllertGenerator();
        allert.showAllert();
        }
    }

    private void delete() {
        this.degreeComboBox.getSelectionModel().clearSelection();
        this.nameTextField.setText(" ");
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
