package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.calendar.persons.view.PersonsView;
import oop.focus.calendar.persons.view.PersonsViewImpl;
import oop.focus.finance.controller.AddPersonController;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.homepage.model.Person;

public class AddPersonViewImpl extends GenericWindow<AddPersonController> {

    @FXML
    private Pane newPersonPane;
    @FXML
    private Label titleLabel, selectLabel, newPersonLabel;
    @FXML
    private ChoiceBox<Person> personChoice;
    @FXML
    private Button newPersonButton, cancelButton, saveButton;

    public AddPersonViewImpl(final AddPersonController controller) {
        super(controller, FXMLPaths.ADDPERSON);
    }

    @Override
    public final void populate() {
        this.newPersonButton.setText("Nuova persona");
        this.personChoice.setItems(super.getX().getPersonsToAdd());
        this.newPersonButton.setOnAction(event -> this.newPerson());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    private void newPerson() {
        final PersonsController controller = new PersonsControllerImpl(super.getX().getDb());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) controller.getView().getRoot()));
        stage.show();
        this.close();
    }

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
