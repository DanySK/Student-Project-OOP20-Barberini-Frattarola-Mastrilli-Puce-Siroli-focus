package oop.focus.calendar.persons.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.calendar.persons.controller.FXMLPaths;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.homepage.view.AllertGenerator;
import oop.focus.homepage.view.GenericAddView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewPersonView implements GenericAddView {

    @FXML
    private Button save;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private AnchorPane newPersonPane;

    @FXML
    private Label name;

    @FXML
    private Label degree;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<String> degreeComboBox;

    @FXML
    private Label newPerson;

    private final PersonsController controller;
    private final PersonsView personsView;
    private Node root;

    public AddNewPersonView(final PersonsController controller, final PersonsView personsView) {
        this.controller = controller;
        this.personsView = personsView;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWPERSON.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperty();
    }

    private void setProperty() {
        this.newPerson.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.LABEL_HEIGHT));
        this.newPerson.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.LABEL_WIDTH));
        this.newPerson.setAlignment(Pos.CENTER);

        this.degree.setAlignment(Pos.CENTER);
        this.name.setAlignment(Pos.CENTER);

        this.degreeComboBox.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.degreeComboBox.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.FIELD_HEIGHT));

        this.nameTextField.prefWidthProperty().bind(this.newPersonPane.widthProperty().multiply(Constants.FIELD_WIDTH));
        this.nameTextField.prefHeightProperty().bind(this.newPersonPane.heightProperty().multiply(Constants.FIELD_HEIGHT));
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.degreeComboBox.setItems(this.controller.getDegree());
        save.setOnAction(event -> {
            try {
                this.save(event);
            } catch (IOException e) {
                 e.printStackTrace();
            }
        });

        delete.setOnAction(event -> this.delete(event));

        back.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.newPersonPane.getScene().getWindow();
        stage.close();
    }

    public final void save(final ActionEvent event) throws IOException {
        if (!this.nameTextField.getText().isEmpty() && !this.degreeComboBox.getSelectionModel().isEmpty()) {
            this.controller.addPerson(new PersonImpl(this.nameTextField.getText(), this.degreeComboBox.getSelectionModel().getSelectedItem()));
            this.personsView.populateTableView();
            this.goBack(event);
        } else {
           final AllertGenerator allert = new AllertGenerator();
           allert.checkFieldsFilled();
           allert.showAllert();
        }
    }

    public final void delete(final ActionEvent event) {
        this.degreeComboBox.getSelectionModel().clearSelection();
        this.nameTextField.setText(" ");
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    private static final class Constants {
        private static final double FIELD_WIDTH = 0.4;
        private static final double FIELD_HEIGHT = 0.05;
        private static final double LABEL_WIDTH = 0.8;
        private static final double LABEL_HEIGHT = 0.1;
    }
}
