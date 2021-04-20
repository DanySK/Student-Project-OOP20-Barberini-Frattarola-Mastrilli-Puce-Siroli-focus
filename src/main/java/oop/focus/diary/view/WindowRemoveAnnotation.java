package oop.focus.diary.view;

import javafx.collections.ObservableList;
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
import oop.focus.common.View;
import oop.focus.diary.controller.RemoveControllers;
import oop.focus.diary.controller.FXMLPaths;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 *  This class represents a new window, opened to remove annotations. Annotations could represent
 *  titles of pages of diary or toDoActions' annotations, depending on the classes in which it is called.
 * @param <X> the type parameter
 */
public class WindowRemoveAnnotation<X> implements View, Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Label removeLabel;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button deleteButton;
    private final RemoveControllers<String> controller;
    private Parent root;
    private final ObservableList<String> list;

    /**
     * Instantiates a new window remove annotation and open the relative FXML file.
     * @param controller    the remove controller
     * @param list  an {@link ObservableList} with annotations that could be removed
     */
    public WindowRemoveAnnotation(final RemoveControllers<String> controller, final ObservableList<String> list) {
        this.controller = controller;
        this.list = list;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.REMOVE_TDL_ANNOTATION.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.root;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.removeLabel.setText("Seleziona annotazioni da rimuovere");
        this.deleteButton.setText("Elimina");
        this.listView.setItems(this.list);
        this.deleteButton.setOnMouseClicked(event -> {
            this.controller.remove(this.listView.getSelectionModel().getSelectedItem());
            final Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
        });


    }
}
