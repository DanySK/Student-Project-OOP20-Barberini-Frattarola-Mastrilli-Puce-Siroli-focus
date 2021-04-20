package oop.focus.calendar.persons.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.calendar.persons.controller.FXMLPaths;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.RelationshipsController;
import oop.focus.calendar.persons.controller.RelationshipsControllerImpl;
import oop.focus.common.Linker;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.homepage.view.AlertFactoryImpl;
import oop.focus.homepage.view.GenericAddView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewPersonViewImpl implements GenericAddView {

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


    private final PersonsController controller;
    private Node root;
    private final ObservableList<String> list;
    private final ObservableSet<String> set;

    public AddNewPersonViewImpl(final PersonsController controller) {
        this.controller = controller;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWPERSON.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperty();
        this.set = this.controller.getDegree();
        this.list = FXCollections.observableArrayList();
        Linker.setToList(this.set, this.list);
        this.degreeComboBox.setItems(this.list);
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setButtonOnAction();
    }

    private void setButtonOnAction() {
        this.save.setOnAction(event -> {
            try {
                this.save(event);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        this.delete.setOnAction(event -> this.delete(event));

        this.back.setOnAction(event -> this.goBack(event));
        this.newDegree.setOnAction(event -> this.newDegree());
    }

    private void newDegree() {
        final RelationshipsController relationshipsController = new RelationshipsControllerImpl(this.controller.getDsi());
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

        this.newDegree.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.newDegree.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.FIELD_HEIGHT));
        this.newDegree.setAlignment(Pos.CENTER);

        this.degreeComboBox.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.degreeComboBox.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.FIELD_HEIGHT));

        this.nameTextField.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.nameTextField.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.FIELD_HEIGHT));
    }

    public final void delete(final ActionEvent event) {
        this.degreeComboBox.getSelectionModel().clearSelection();
        this.nameTextField.clear();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    public final void goBack(final ActionEvent event) {
        final Stage stage = (Stage) this.newPersonPane.getScene().getWindow();
        stage.close();
    }

    public final void save(final ActionEvent event) throws IOException {
        if (!this.nameTextField.getText().isEmpty() && !this.degreeComboBox.getSelectionModel().isEmpty()) {
            this.controller.addPerson(new PersonImpl(this.nameTextField.getText(), this.degreeComboBox.getSelectionModel().getSelectedItem()));
            this.goBack(event);
        } else {
            new AlertFactoryImpl().createIncompleteFieldAlert();
        }
    }

    private static final class Constants {
        private static final double FIELD_WIDTH = 0.4;
        private static final double FIELD_HEIGHT = 0.05;
        private static final double LABEL_WIDTH = 0.8;
        private static final double LABEL_HEIGHT = 0.1;
    }

}
