package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.week.controller.PersonsController;
import oop.focus.homepage.model.Person;
import oop.focus.week.controller.PersonsControllerImpl;
import oop.focus.week.view.PersonsView;
import oop.focus.week.view.PersonsViewImpl;

public class AddPersonViewImpl extends GenericWindow<GroupController> {

    @FXML
    private Pane newPersonPane;
    @FXML
    private Label titleLabel, selectLabel, newPersonLabel;
    @FXML
    private ChoiceBox<Person> personChoice;
    @FXML
    private Button newPersonButton, cancelButton, saveButton;

    public AddPersonViewImpl(final GroupController groupController) {
        super(groupController, FXMLPaths.ADDPERSON);
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
        final PersonsController controller = new PersonsControllerImpl((DataSourceImpl) super.getX().getManager().getDb());
        final PersonsView persons = new PersonsViewImpl(controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) persons.getRoot()));
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
