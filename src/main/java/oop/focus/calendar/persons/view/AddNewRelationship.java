package oop.focus.calendar.persons.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import oop.focus.calendar.persons.controller.RelationshipsController;
import oop.focus.calendar.persons.controller.FXMLPaths;
import oop.focus.homepage.view.AlertFactoryImpl;
import oop.focus.homepage.view.GenericAddView;

public class AddNewRelationship implements GenericAddView {

    @FXML
    private AnchorPane newRelatioshipPane;

    @FXML
    private Label name, newDegree;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private Button back;

    private final RelationshipsController controller;
    private final RelationshipsView relationshipsView;
    private final AddNewPersonView personView;
    private Node root;
 
    public AddNewRelationship(final RelationshipsController controller, final AddNewPersonView personView) {
        this.controller = controller;
        this.relationshipsView = (RelationshipsView) this.controller.getView();
        this.personView = personView;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWRELATIONSHIP.getPath()));
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
        this.back.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.delete.setOnAction(event -> this.delete(event));

        this.save.setOnAction(event -> {
            try {
                this.save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setProperty() {
        this.newDegree.prefHeightProperty().bind(this.newRelatioshipPane.heightProperty().multiply(Constants.LABEL_HEIGHT));
        this.newDegree.prefWidthProperty().bind(this.newRelatioshipPane.widthProperty().multiply(Constants.LABEL_WIDTH));

        this.newDegree.setAlignment(Pos.CENTER);

        this.nameTextField.prefWidthProperty().bind(this.newRelatioshipPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.nameTextField.prefHeightProperty().bind(this.newRelatioshipPane.heightProperty().multiply(Constants.FIELD_HEIGHT));
    }


    public final void delete(final ActionEvent event) {
        this.nameTextField.clear();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.newRelatioshipPane.getScene().getWindow();
        stage.close();
    }

    public final void save(final ActionEvent event) throws IOException {
        if (!this.nameTextField.getText().isEmpty()) {
            this.controller.addRelationship(this.nameTextField.getText());
            this.relationshipsView.populateTableView();
            this.personView.fillComboBoxDegree();
            this.goBack(event);
        } else {
            new AlertFactoryImpl().createIncompleteFieldAlert();
        }
    }
    private static final class Constants {
        private static final double FIELD_WIDTH = 0.4;
        private static final double FIELD_HEIGHT = 0.05;
        private static final double LABEL_WIDTH = 0.6;
        private static final double LABEL_HEIGHT = 0.1;
    }

}
