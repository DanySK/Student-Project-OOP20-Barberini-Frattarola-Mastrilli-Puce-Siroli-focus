package oop.focus.week.view;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.week.controller.FXMLPaths;
import oop.focus.week.controller.PersonsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import oop.focus.common.View;
import oop.focus.week.controller.PersonsControllerImpl;
import oop.focus.week.controller.RelationshipsControllerImpl;


public class RelationshipsViewImpl implements RelationshipsView {
    @FXML
    private AnchorPane relationshipsPane;

    @FXML
    private TableView<String> relationshipsTable;

    @FXML
    private TableColumn<String, String> relationshipsColumn;

    @FXML
    private Button addRelationships, goBack, deleteRelationship;

    private final RelationshipsControllerImpl controller;
    private Node root;

    public RelationshipsViewImpl(final RelationshipsControllerImpl controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.RELATIONSHIPS.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populateTableView();

        goBack.setOnAction(event -> this.goBack());
        addRelationships.setOnAction(event -> this.addRelationships());
        deleteRelationship.setOnAction(event -> this.deleteRelationships());
    }

    public final void populateTableView() {
        relationshipsColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        this.relationshipsTable.setItems(this.controller.getDegree());
    }

    public final void goBack() {
        final PersonsView persons = new PersonsViewImpl(new PersonsControllerImpl(this.controller.getDsi()));
        this.relationshipsPane.getChildren().clear();
        this.relationshipsPane.getChildren().add(persons.getRoot());
    }

    @FXML
    public final void addRelationships() {
        final View newDegree = new AddNewRelationship(this.controller, this);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newDegree.getRoot()));
        stage.show();
    }

    @FXML
    public final void deleteRelationships() {
        this.controller.deleteRelationship(relationshipsTable.getSelectionModel().getSelectedItem());
        this.refreshTableView();
    }

    public final void refreshTableView() {
        this.relationshipsTable.getItems().removeAll(relationshipsTable.getSelectionModel().getSelectedItems());
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}
