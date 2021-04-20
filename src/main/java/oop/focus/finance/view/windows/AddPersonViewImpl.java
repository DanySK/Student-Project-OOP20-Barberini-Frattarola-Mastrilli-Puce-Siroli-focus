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
import oop.focus.homepage.model.Person;

/**
 * Class that implements the view of adding a person to the group of group transactions.
 */
public class AddPersonViewImpl extends GenericWindow<AddPersonController> {

    @FXML
    private Pane newPersonPane;
    @FXML
    private Label titleLabel, selectLabel, newPersonLabel;
    @FXML
    private ComboBox<Person> personChoice;
    @FXML
    private Button newPersonButton, cancelButton, saveButton;

    public AddPersonViewImpl(final AddPersonController controller) {
        super(controller, FXMLPaths.ADDPERSON);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.newPersonButton.setText("Nuova persona");
        this.personChoice.setItems(super.getX().getPersonsToAdd());
        this.personChoice.setConverter(super.createStringConverter(Person::getName));
        this.newPersonButton.setOnAction(event -> this.showNewPerson());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    /**
     * Method that shows on the screen the window for creating a new person to add to the database.
     */
    private void showNewPerson() {
        final PersonsController controller = new PersonsControllerImpl(super.getX().getDb());
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
            super.allert("Non hai selezionato nessuna persona.");
        } else {
            super.getX().addPerson(this.personChoice.getValue());
            this.close();
        }
    }

}
