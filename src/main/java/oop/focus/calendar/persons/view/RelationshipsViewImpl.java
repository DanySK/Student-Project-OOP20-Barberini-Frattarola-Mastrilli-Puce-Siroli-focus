package oop.focus.calendar.persons.view;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import oop.focus.calendar.persons.controller.RelationshipsController;
import oop.focus.calendar.persons.controller.FXMLPaths;
import oop.focus.common.Linker;
import oop.focus.common.View;


public class RelationshipsViewImpl implements RelationshipsView {

    @FXML
    private AnchorPane relationshipsPane;

    @FXML
    private TableView<String> relationshipsTable;

    @FXML
    private TableColumn<String, String> relationshipsColumn;

    @FXML
    private Button addRelationship, goBack, deleteRelationship;

    private final RelationshipsController controller;
    private Node root;
    private final ObservableList<String> list;
    private final ObservableSet<String> set;

    public RelationshipsViewImpl(final RelationshipsController controller) {
        this.controller = controller;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.RELATIONSHIPS.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperty();
        this.set = this.controller.getDegree();
        this.list = FXCollections.observableArrayList();
        this.addListener();
        this.populateTableView();
        this.relationshipsTable.setItems(this.list);
    }

    private void addListener() {
        Linker.setToList(this.set, this.list);
    }
 
    private void setProperty() {
        this.relationshipsTable.prefWidthProperty().bind(this.relationshipsPane.widthProperty().multiply(Constants.TABLE_WIDTH));
        this.relationshipsTable.prefHeightProperty().bind(this.relationshipsPane.heightProperty().multiply(Constants.TABLE_HEIGHT));
        this.relationshipsColumn.prefWidthProperty().bind(this.relationshipsTable.widthProperty());
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populateTableView();
        this.setButtonOnAction();
    }

    @FXML
    public final void addRelationships() {
        final View newDegree = new AddNewRelationship(this.controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newDegree.getRoot()));
        stage.show();
    }

    private void deleteItem() {
        if (this.controller.getPersons().contains(this.relationshipsTable.getSelectionModel().getSelectedItem())) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("La parentela che si vuole eliminare è utilizzata!");
            final Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
                alert.close();
            }
        } else {
            this.controller.deleteRelationship(this.relationshipsTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public final void deleteRelationships() {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di volere eliminare questa parentela?");

        final Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) {
            alert.close();
        } else {
            this.deleteItem();
        }
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    public final void goBack() {
        final Stage stage = (Stage) this.root.getScene().getWindow();
        stage.close();
    }

    private void populateTableView() {
        this.relationshipsColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
    }

    private void setButtonOnAction() {
        this.goBack.setOnAction(event -> this.goBack());
        this.addRelationship.setOnAction(event -> this.addRelationships());
        this.deleteRelationship.setOnAction(event -> this.deleteRelationships());
    }

    private static class Constants {
        private static final double TABLE_WIDTH = 0.65;
        private static final double TABLE_HEIGHT = 0.8;
    }
}
