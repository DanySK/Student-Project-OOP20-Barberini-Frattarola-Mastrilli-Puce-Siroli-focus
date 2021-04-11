package oop.focus.week.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.week.controller.FXMLPaths;
import oop.focus.week.controller.PersonsController;
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
    private PersonsController controller;
    private Node root;

    public AddNewPersonView(final PersonsController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWPERSON.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

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
        if (!this.nameTextField.getText().isEmpty() && !this.degreeComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            this.controller.addPerson(new PersonImpl(this.nameTextField.getText(), this.degreeComboBox.getSelectionModel().getSelectedItem()));
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
}
