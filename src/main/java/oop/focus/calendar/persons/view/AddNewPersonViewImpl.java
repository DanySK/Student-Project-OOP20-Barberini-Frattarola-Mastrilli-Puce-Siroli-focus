package oop.focus.calendar.persons.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import oop.focus.calendar.persons.controller.FXMLPaths;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.RelationshipsController;
import oop.focus.calendar.persons.controller.RelationshipsControllerImpl;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.homepage.view.AlertFactory;
import oop.focus.homepage.view.AlertFactoryImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewPersonViewImpl implements AddNewPersonView {

    @FXML
    private Button save, back, delete, newDegree;

    @FXML
    private AnchorPane newPersonPane;

    @FXML
    private Label name, degree, newPerson, newDegreeLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<String> degreeComboBox;

    @FXML
    private Line line;

    private final PersonsController controller;
    private final PersonsView personsView;
    private Node root;

    public AddNewPersonViewImpl(final PersonsController controller, final PersonsView personsView) {
        this.controller = controller;
        this.personsView = personsView;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWPERSON.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperty();
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.fillComboBoxDegree();
        this.setButtonOnAction();
    }

    private void setButtonOnAction() {
        this.degreeComboBox.setItems(this.controller.getDegree());
        save.setOnAction(event -> {
            try {
                this.save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        delete.setOnAction(event -> this.delete(event));

        back.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.newDegree.setOnAction(event -> this.newDegree(event));
    }

    private void newDegree(final ActionEvent event) {
        final RelationshipsController relationshipsController = new RelationshipsControllerImpl(this.controller.getDsi(), this);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) relationshipsController.getView().getRoot()));
        stage.show();
    }

    private void setProperty() {
        this.newPerson.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.LABEL_HEIGHT));
        this.newPerson.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.LABEL_WIDTH));
        this.newPerson.setAlignment(Pos.CENTER);

        this.newDegreeLabel.setAlignment(Pos.CENTER);
        this.degree.setAlignment(Pos.CENTER);
        this.name.setAlignment(Pos.CENTER);

        this.degreeComboBox.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.degreeComboBox.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.FIELD_HEIGHT));

        this.nameTextField.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.nameTextField.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.FIELD_HEIGHT));

        //this.newDegree.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(C));
    }

    public final void delete(final ActionEvent event) {
        this.degreeComboBox.getSelectionModel().clearSelection();
        this.nameTextField.clear();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.newPersonPane.getScene().getWindow();
        stage.close();
    }

    public final void save(final ActionEvent event) throws IOException {
        if (!this.nameTextField.getText().isEmpty() && !this.degreeComboBox.getSelectionModel().isEmpty()) {
            this.controller.addPerson(new PersonImpl(this.nameTextField.getText(), this.degreeComboBox.getSelectionModel().getSelectedItem()));
            this.personsView.populateTableView();
            this.goBack(event);
        } else {
            final AlertFactory alertCreator = new AlertFactoryImpl();
            final Alert alert = alertCreator.createWarningAlert();
            alert.setHeaderText("I campi non sono stati riempiti correttamente!");
            alert.show();
        }
    }

    private static final class Constants {
        private static final double FIELD_WIDTH = 0.4;
        private static final double FIELD_HEIGHT = 0.05;
        private static final double LABEL_WIDTH = 0.8;
        private static final double LABEL_HEIGHT = 0.1;
    }

    @Override
    public final void fillComboBoxDegree() {
        this.degreeComboBox.setItems(this.controller.getDegree());
    }
}
