package oop.focus.finance.view.windows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import oop.focus.common.Linker;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.homepage.controller.PersonsController;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.view.PersonsView;

public class AddPersonViewImpl extends GenericWindow<GroupController> {

    @FXML
    private Pane newPersonPane;
    @FXML
    private Label titleLabel, selectLabel, newPersonLabel;
    @FXML
    private ComboBox<Person> personChoice;
    @FXML
    private Button newPersonButton, cancelButton, saveButton;

    public AddPersonViewImpl(final GroupController groupController) {
        super(groupController, FXMLPaths.ADDPERSON);
    }

    @Override
    public final void populate() {
        this.titleLabel.setText("Nuova persona");
        this.selectLabel.setText("Seleziona la persona da aggiungere:");
        this.newPersonLabel.setText("Clicca qui invece per creare una nuova persona:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.newPersonButton.setText("Nuova persona");
        this.personChoice.setCellFactory(
                new Callback<>() {
                    @Override
                    public ListCell<Person> call(final ListView<Person> p) {
                        return new ListCell<>() {
                            @Override
                            protected void updateItem(final Person item, final boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    this.setText("");
                                } else {
                                    this.setText(item.getName());
                                }
                            }
                        };
                    }
                });
        ObservableList<Person> list = FXCollections.observableArrayList();
        Linker.setToList(super.getX().getGroup(), list);
        this.personChoice.setItems(list);
        this.newPersonButton.setOnAction(event -> this.newPerson());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    private void newPerson() {
        final PersonsController controller = new PersonsController((DataSourceImpl) super.getX().getManager().getDb());
        final PersonsView persons = new PersonsView(controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene(persons.getRoot()));
        stage.show();
    }

    @Override
    public final void save() {
        if (this.personChoice.getValue() == null) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().addPerson(this.personChoice.getValue());
            this.close();
        }
    }

}
