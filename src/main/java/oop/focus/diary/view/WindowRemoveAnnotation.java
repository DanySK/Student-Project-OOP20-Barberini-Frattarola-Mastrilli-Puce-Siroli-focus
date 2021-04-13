package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.diary.controller.RemoveControllers;
import oop.focus.diary.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WindowRemoveAnnotation<X> implements View, Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Label removeLabel;

    @FXML
    private ListView<X> listView;

    @FXML
    private Button deleteButton;
    private final RemoveControllers controller;
    private Parent root;
    private final ObservableMap<X, String> map;
    public WindowRemoveAnnotation(final RemoveControllers controller, final ObservableMap<X, String> map) {
        this.controller = controller;
        this.map = map;
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
        final ObservableList<X> observableList = FXCollections.observableArrayList();
        this.removeLabel.setText("Seleziona annotazioni da rimuovere");
        this.deleteButton.setText("Elimina");
        Linker.setToList(FXCollections.observableSet(this.map.keySet()), observableList);
        this.listView.setItems(observableList);
        this.deleteButton.setOnMouseClicked(event -> {
            this.controller.remove(this.map.get(this.listView.getSelectionModel().getSelectedItem()));
            final Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
        });


    }
}
