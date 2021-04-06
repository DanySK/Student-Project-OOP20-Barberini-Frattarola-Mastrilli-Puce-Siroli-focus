package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import oop.focus.homepage.controller.PersonsController;
import oop.focus.homepage.model.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

public class PersonsView implements Initializable, View {

   @FXML
    private AnchorPane panePersons;

    @FXML
    private TableView<Person> personsColumn;

    @FXML
    private TableColumn<Person, String> nome;

    @FXML
    private TableColumn<Person, String> parentela;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button Relationships;


    private final PersonsController controller;
    private Parent root;

    public PersonsView(final PersonsController controller) {
        this.controller = controller;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/persons.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        nome.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        nome.setCellFactory(TextFieldTableCell.forTableColumn());
        nome.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(final CellEditEvent<Person, String> event) {
                final Person person = event.getRowValue();
                person.setName(event.getNewValue());
            }
        });

        parentela.setCellValueFactory(new PropertyValueFactory<Person, String>("relationships"));
        parentela.setCellFactory(TextFieldTableCell.forTableColumn());
        parentela.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(final CellEditEvent<Person, String> event) {
                final Person person = event.getRowValue();
                person.setRelationships(event.getNewValue());
            }
        });
    }

    @FXML
    public final void addNewPerson(final ActionEvent event) {

    }

    @FXML
    public final void deleteSelected(final ActionEvent event) {

    }

    @FXML
    public final void seeRelationships(final ActionEvent event) {
        RelationshipsView relationship = new RelationshipsView(this.controller);
        this.panePersons.getChildren().clear();
        this.panePersons.getChildren().add(relationship.getRoot());
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

}
