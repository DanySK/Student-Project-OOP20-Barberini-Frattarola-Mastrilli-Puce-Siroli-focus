package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.finance.controller.AddPersonController;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.calendar.persons.model.Person;
import oop.focus.finance.view.StaticAllerts;

/**
 * Class that implements the view of adding a person to the group of group transactions.
 */
public class AddPersonViewImpl extends GenericWindow {

    @FXML
    private Pane newPersonPane;
    @FXML
    private Label titleLabel, selectLabel, newPersonLabel;
    @FXML
    private ComboBox<Person> personChoice;
    @FXML
    private Button newPersonButton, cancelButton, saveButton;

    private final AddPersonController controller;

    public AddPersonViewImpl(final AddPersonController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.ADDPERSON);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.newPersonButton.setText("Nuova persona");
        this.personChoice.setItems(this.controller.getPersonsToAdd());
        this.personChoice.setConverter(super.createStringConverter(Person::getName));
        this.newPersonButton.setOnAction(event -> this.showNewPerson());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    /**
     * Method that shows on the screen the window for creating a new person to add to the database.
     */
    private void showNewPerson() {
        final PersonsController controller = new PersonsControllerImpl(this.controller.getDb());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) controller.getView().getRoot()));
        stage.show();
        this.close();
    }

    /**
     * {@inheritDoc}
     * If the required fields are filled in, add the person to the group.
     */
    @Override
    public final void save() {
        if (this.personChoice.getValue() == null) {
            StaticAllerts.allert("Non hai selezionato nessuna persona.");
        } else {
            this.controller.addPerson(this.personChoice.getValue());
            this.close();
        }
    }

}
