package oop.focus.calendar.persons.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.FXMLPaths;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class PersonsViewImpl implements PersonsView {

    @FXML
    private VBox panePersons;

    @FXML
    private TableView<Person> tableViewPersons;

    @FXML
    private TableColumn<Person, String> name, relationships;

    @FXML
    private Button delete, add;

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
        this.root = new ViewFactoryImpl().createVerticalAutoResizingWithNodes(List.copyOf(this.panePersons.getChildren())).getRoot();

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populateTableView();
        this.setButtonOnAction();
    }

    public final void add(final ActionEvent event) {
        final AddNewPersonView newPerson = new AddNewPersonViewImpl(this.controller, this);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newPerson.getRoot()));
        stage.show();
    }

    public final void delete(final ActionEvent event) {
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

    private void deleteItem() {
        final String name = this.tableViewPersons.getSelectionModel().getSelectedItem().getName();
        final String degree = tableViewPersons.getSelectionModel().getSelectedItem().getRelationships();
        this.controller.deletePerson(new PersonImpl(name, degree));
        this.refreshTableView();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    public final void populateTableView() {
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(final CellEditEvent<Person, String> event) {
                final Person person = event.getRowValue();
                person.setName(event.getNewValue());
            }
        });

        relationships.setCellValueFactory(new PropertyValueFactory<Person, String>("relationships"));
        relationships.setCellFactory(TextFieldTableCell.forTableColumn());
        relationships.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(final CellEditEvent<Person, String> event) {
                final Person person = event.getRowValue();
                person.setRelationships(event.getNewValue());
            }
        });

        this.tableViewPersons.setEditable(false);
        this.tableViewPersons.getItems().clear();
        this.tableViewPersons.setItems(this.controller.getPersons());
    }

    private void setButtonOnAction() {
        this.add.setOnAction(event -> this.add(event));
        this.delete.setOnAction(event -> this.delete(event));
    }

    public final void refreshTableView() {
        this.tableViewPersons.getItems().removeAll(tableViewPersons.getSelectionModel().getSelectedItems());
    }

}
