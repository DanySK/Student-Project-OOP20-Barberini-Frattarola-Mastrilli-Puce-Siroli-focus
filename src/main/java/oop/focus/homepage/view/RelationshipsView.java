package oop.focus.homepage.view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import oop.focus.homepage.controller.PersonsController;
import oop.focus.homepage.model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RelationshipsView implements Initializable, View {
    @FXML
    private AnchorPane relationshipsPane;

    @FXML
    private TableView<String> relationshipsTable;

    @FXML
    private TableColumn<String, String> relationshipsColumn;

    @FXML
    private Button addRelationships;

    @FXML
    private Button deleteRelationship;
    private PersonsController controller;
    private Parent root;

    public RelationshipsView(PersonsController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/relationships.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        relationshipsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("string"));
        relationshipsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public final void addRelationships(ActionEvent event) {

    }

    @FXML
    public final void deleteRelationships(ActionEvent event) {

    }

    @Override
    public Parent getRoot() {
        return this.root;
    }
}
