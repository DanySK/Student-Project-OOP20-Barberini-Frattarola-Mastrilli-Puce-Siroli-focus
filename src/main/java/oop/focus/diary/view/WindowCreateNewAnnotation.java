package oop.focus.diary.view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.ToDoListController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class represents a new window, opened to add new ToDoAction. When new ToDoAction is created,
 * his annotation is chosen by the user and his status is automatically set as "not done".
 */
public class WindowCreateNewAnnotation implements Initializable, View {
    @FXML
    private Button createButton;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField annotationTitle;
    private Parent root;
    private final  ToDoListController toDoListController;

    /**
     * Instantiates a new window create new annotation and opens the relative FXML file.
     * @param toDoListController    to do list Controller
     */
    public WindowCreateNewAnnotation(final ToDoListController toDoListController) {
        this.toDoListController = toDoListController;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.INSERT_TDL_ANNOTATION.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method (if the note is not saved yet), saves the annotation of ToDoList written by the user.
     */
    private void createNewAnnotation() {
        if (!this.annotationTitle.getText().isEmpty()) {
            this.toDoListController.addNote(this.annotationTitle.getText());
            final Stage stage = (Stage) this.annotationTitle.getScene().getWindow();
            stage.close();
        } else {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("La nota non deve essere vuota");
            alert.showAndWait();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.createButton.setText("Crea");
        this.titleLabel.setText("Aggiungi nota");
        this.createButton.setOnMouseClicked(event -> this.createNewAnnotation());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRoot() {
        return this.root;
    }
}
