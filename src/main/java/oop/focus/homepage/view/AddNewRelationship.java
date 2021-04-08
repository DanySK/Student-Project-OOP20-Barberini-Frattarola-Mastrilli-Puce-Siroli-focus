package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.homepage.controller.PersonsController;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class AddNewRelationship implements Initializable, View {

    @FXML
    private AnchorPane newRelatioshipPane;

    @FXML
    private Label name;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private Button back;

    private final PersonsController controller;
    private Parent root;
 
    public AddNewRelationship(final PersonsController controller) {
        this.controller = controller;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/addNewRelationship.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.back.setOnAction(event -> this.goBack());
        this.delete.setOnAction(event -> this.delete());
        this.save.setOnAction(event -> this.save());
    }

    private void save() {
        if (!this.nameTextField.getText().isEmpty()) {
            this.controller.addRelationship(this.nameTextField.getText());
            this.goBack();
        } else {
            final AllertGenerator allert = new AllertGenerator();
            allert.showAllert();
        }
    }

    private void delete() {
        this.nameTextField.setText(" ");
    }

    private void goBack() {
        final Stage stage = (Stage) this.newRelatioshipPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
