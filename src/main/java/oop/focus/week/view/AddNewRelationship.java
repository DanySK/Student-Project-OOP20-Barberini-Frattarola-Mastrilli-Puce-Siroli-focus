package oop.focus.week.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.week.controller.FXMLPaths;
import oop.focus.week.controller.PersonsController;
import javafx.scene.Node;
import oop.focus.homepage.view.AllertGenerator;
import oop.focus.homepage.view.GenericAddView;
import oop.focus.week.controller.RelationshipsControllerImpl;

public class AddNewRelationship implements GenericAddView {

    @FXML
    private AnchorPane newRelatioshipPane;

    @FXML
    private Label name;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private Button back;

    private final RelationshipsControllerImpl controller;
    RelationshipsViewImpl relationshipsView;
    private Node root;
 
    public AddNewRelationship(final RelationshipsControllerImpl controller, final RelationshipsViewImpl relationshipsView) {
        this.controller = controller;
        this.relationshipsView = relationshipsView;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWRELATIONSHIP.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.back.setOnAction(event -> {
			try {
				this.goBack(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

        this.delete.setOnAction(event -> this.delete(event));

        this.save.setOnAction(event -> {
			try {
				this.save(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
    }

    public final void save(final ActionEvent event) throws IOException {
        if (!this.nameTextField.getText().isEmpty()) {
            this.controller.addRelationship(this.nameTextField.getText());
            this.relationshipsView.populateTableView();
            this.goBack(event);
        } else {
            final AllertGenerator allert = new AllertGenerator();
            allert.checkFieldsFilled();
            allert.showAllert();
        }
    }

    public final void delete(final ActionEvent event) {
        this.nameTextField.setText(" ");
    }

    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.newRelatioshipPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}
