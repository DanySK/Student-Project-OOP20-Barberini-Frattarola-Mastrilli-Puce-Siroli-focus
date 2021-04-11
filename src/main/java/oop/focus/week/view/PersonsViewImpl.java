package oop.focus.week.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import oop.focus.week.controller.FXMLPaths;
import oop.focus.week.controller.PersonsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.AnchorPane;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PersonsViewImpl implements PersonsView {

    @FXML
    private AnchorPane panePersons;

    @FXML
    private TableView<Person> tableViewPersons;

    @FXML
    private TableColumn<Person, String> name;

    @FXML
    private TableColumn<Person, String> relationship;

    @FXML
    private Button delete;

    @FXML
    private Button add;

    @FXML
    private Button degreeOfKinsip;

    private final PersonsController controller;
    private Node root;

    public PersonsViewImpl(final PersonsController controller) {
        this.controller = controller;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.PERSONS.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {

        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(final CellEditEvent<Person, String> event) {
                final Person person = event.getRowValue();
                person.setName(event.getNewValue());
            }
        });

        relationship.setCellValueFactory(new PropertyValueFactory<Person, String>("relationships"));
        relationship.setCellFactory(TextFieldTableCell.forTableColumn());
        relationship.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(final CellEditEvent<Person, String> event) {
                final Person person = event.getRowValue();
                person.setRelationships(event.getNewValue());
            }
        });
        this.tableViewPersons.setItems(this.controller.getPersons());

        this.add.setOnAction(event -> this.add());
        this.delete.setOnAction(event -> this.delete());
        this.degreeOfKinsip.setOnAction(event -> goToDegree());
    }

    public final void goToDegree() {
        final RelationshipsView relationship = new RelationshipsViewImpl(this.controller);
        this.panePersons.getChildren().clear();
        this.panePersons.getChildren().add(relationship.getRoot());
    }

    public final void delete() {
        final String name = this.tableViewPersons.getSelectionModel().getSelectedItem().getName();
        final String degree = tableViewPersons.getSelectionModel().getSelectedItem().getRelationships();
        this.controller.deletePerson(new PersonImpl(name, degree));
        this.refreshTableView();
    }

    public final void refreshTableView() {
        this.tableViewPersons.getItems().removeAll(tableViewPersons.getSelectionModel().getSelectedItems());
    }

    public final void add() {
        final AddNewPersonView newPerson = new AddNewPersonView(this.controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newPerson.getRoot()));
        stage.show();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}
