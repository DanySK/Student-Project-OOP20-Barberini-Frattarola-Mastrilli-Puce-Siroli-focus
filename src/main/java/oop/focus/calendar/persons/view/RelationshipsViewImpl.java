package oop.focus.calendar.persons.view;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.db.exceptions.DaoAccessException;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.calendar.persons.controller.RelationshipsController;
import oop.focus.calendar.persons.controller.FXMLPaths;
import oop.focus.common.View;


public class RelationshipsViewImpl implements RelationshipsView {
    @FXML
    private Pane relationshipsPane;

    @FXML
    private TableView<String> relationshipsTable;

    @FXML
    private TableColumn<String, String> relationshipsColumn;

    @FXML
    private Button addRelationship, goBack, deleteRelationship;

    private final RelationshipsController controller;
    private Node root;

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
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populateTableView();
        this.setButtonOnAction();
    }

    private void setProperty() {
        //this.relationshipsPane.setPrefSize(this.controller.getWidth(), this.controller.getHeight());

        this.relationshipsTable.prefWidthProperty().bind(this.relationshipsPane.widthProperty().multiply(0.65));
        this.relationshipsTable.prefHeightProperty().bind(this.relationshipsPane.heightProperty().multiply(0.8));
        this.relationshipsColumn.prefWidthProperty().bind(this.relationshipsTable.widthProperty());
    }

    @FXML
    public final void addRelationships() {
        final View newDegree = new AddNewRelationship(this.controller, this);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newDegree.getRoot()));
        stage.show();
    }

    private void deleteItem() {
        try {
            this.controller.deleteRelationship(relationshipsTable.getSelectionModel().getSelectedItem());
            this.refreshTableView();
        } catch (DaoAccessException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Impossibile eliminare la parentela poichè è utilizzata da una o più persone!");
            final Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
                alert.close();
            }
        }
    }

    @FXML
    public final void deleteRelationships() {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di volere eliminare questa persona?");

        final Optional<ButtonType> result = alert.showAndWait();

        if (!result.isPresent() || result.get() != ButtonType.OK) {
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
        final PersonsController personsController = new PersonsControllerImpl(this.controller.getDsi());
        this.relationshipsPane.getChildren().clear();
        this.relationshipsPane.getChildren().add(personsController.getView().getRoot());
    }

    public final void populateTableView() {
        relationshipsColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        this.relationshipsTable.setItems(this.controller.getDegree());
    }

    private void setButtonOnAction() {
        this.goBack.setOnAction(event -> this.goBack());
        this.addRelationship.setOnAction(event -> this.addRelationships());
        this.deleteRelationship.setOnAction(event -> this.deleteRelationships());
    }

    public final void refreshTableView() {
        this.relationshipsTable.getItems().removeAll(relationshipsTable.getSelectionModel().getSelectedItems());
    }

}
