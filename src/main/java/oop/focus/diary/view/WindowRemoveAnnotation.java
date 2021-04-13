package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.SingleCheckBoxController;
import oop.focus.diary.controller.ToDoListController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WindowRemoveAnnotation implements View, Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Label removeLabel;

    @FXML
    private ListView<CheckBox> listView;

    @FXML
    private Button deleteButton;
    private final ToDoListController controller;
    private SingleCheckBoxController checkBoxController;
    private ObservableList<CheckBox> checkBoxes;
    private Parent root;
    public WindowRemoveAnnotation(final ToDoListController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.REMOVE_TDL_ANNOTATION.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.checkBoxController = new SingleCheckBoxController();
        this.checkBoxes = FXCollections.observableArrayList();
        this.removeLabel.setText("Seleziona annotazioni da rimuovere");
        this.deleteButton.setText("Elimina");
        controller.allAnnotations().stream().map(s -> this.checkBoxController.createCheckBox(s)).forEach(this.checkBoxes::add);
        this.listView.setItems(this.checkBoxes);
        this.deleteButton.setOnMouseClicked(event -> {
            System.out.println(listView.getSelectionModel().getSelectedItem().getText() + "Vi");
            controller.remove(listView.getSelectionModel().getSelectedItem().getText());
            final Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        });
    }
}
