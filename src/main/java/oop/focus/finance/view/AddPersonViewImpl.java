package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.view.AllertGenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPersonViewImpl implements Initializable, FinanceWindow {

    @FXML
    private Pane newPersonPane;
    @FXML
    private Label titleLabel, selectLabel, newPersonLabel;
    @FXML
    private ChoiceBox<Person> personChoice;
    @FXML
    private Button newPersonButton, cancelButton, saveButton;

    private final GroupController controller;
    private Parent root;

    public AddPersonViewImpl(final GroupController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDPERSON.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    private void populate() {
        this.titleLabel.setText("Nuova persona");
        this.selectLabel.setText("Seleziona la persona da aggiungere:");
        this.newPersonLabel.setText("Clicca qui invece per creare una nuova persona:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.newPersonButton.setText("Nuova persona");
        this.personChoice.setItems(this.controller.getPersonsToAdd());
        this.newPersonButton.setOnAction(event -> this.newPerson());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    private void newPerson() {
        //TODO lanciare la finestra di aggiunta nuova persona
    }

    @Override
    public final void close() {
        final Stage stage = (Stage) this.newPersonPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public final void save() {
        if (this.personChoice.getValue() == null) {
            final AllertGenerator allert = new AllertGenerator();
            allert.showAllert();
        } else {
            this.controller.addPerson(this.personChoice.getValue());
            this.close();
        }
    }

}
