package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.homepage.model.Person;

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
        this.titleLabel.setText("Nuova persona");
        this.selectLabel.setText("Seleziona la persona da aggiungere:");
        this.newPersonLabel.setText("Clicca qui invece per creare una nuova persona:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.newPersonButton.setText("Nuova persona");
        this.personChoice.setItems(super.getX().getPersonsToAdd());
        this.newPersonButton.setOnAction(event -> this.newPerson());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    private void newPerson() {
        //TODO lanciare la finestra di aggiunta nuova persona
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
