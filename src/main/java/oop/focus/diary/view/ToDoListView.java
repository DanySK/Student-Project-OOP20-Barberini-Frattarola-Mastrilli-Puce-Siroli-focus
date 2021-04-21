package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.common.View;
import oop.focus.diary.controller.CreateNewAnnotationController;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.RemoveTDLController;
import oop.focus.diary.controller.ToDoListController;
import oop.focus.diary.model.ToDoAction;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static oop.focus.diary.view.OpenWindow.openWindow;

/**
 * To Do List View represents toDoList's section, a container of ToDoActions.
 */
public class ToDoListView implements View, Initializable {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final double SINGLE_NOTE_HEIGHT = 0.1;
    private static final double TDL_LABEL_HEIGHT = 0.2;
    private static final double V_BOX_WIDTH = 0.5;
    private static final double BUTTON_WIDTH = 0.3;
    private static final double BUTTON_HEIGHT = 0.15;
    private static final double CONTAINER_TDL_HEIGHT = 0.6;
    private static final double V_BOX_HEIGHT = 0.5;
    private static final double H_BOX_SPACING = 0.04;
    @FXML
    private VBox vBox;

    @FXML
    private Label toDoListLabel;

    @FXML
    private ScrollPane containerTDL;

    @FXML
    private HBox hBox;

    @FXML
    private Button removeAnnotation;

    @FXML
    private Button addAnnotation;
    private final ToDoListController controller;
    private  ListView<CheckBox> listView;
    private ObservableList<CheckBox> checkBoxes;
    /**
     * Instantiates a new to do list view and opens the relative FXML file.
     * @param controller    to do list controller
     */
    public ToDoListView(final ToDoListController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.TDL_SCHEME.getPath()));
        loader.setController(this);
        try {
            loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * The method can be used to insert all {@link ToDoAction} in the appropriate {@link ListView}.
     */
    private void updateTDLView() {
        this.controller.allAnnotations().stream().map(this::createSingleToDoAction).forEach(this.checkBoxes::add);
    }

    /**
     * Represents single To Do Action of the section of To Do List, using {@link CheckBox}.
     * @param action    the to do action of which it's set the View.
     * @return  a {@link CheckBox} representing the toDoAction in input
     */
    private CheckBox createSingleToDoAction(final ToDoAction action) {
        final CheckBox box;
        box = new CheckBox(action.getAnnotation());
        box.setSelected(action.isDone());
        return box;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.vBox = (VBox) new ContainerFactoryImpl().mergeVertically(List.of(this.toDoListLabel, this.containerTDL,
                this.hBox)).getRoot();
        this.toDoListLabel.setText("To Do List");
        this.addAnnotation.setText("Aggiungi");
        this.addAnnotation.setOnMouseClicked(event -> openWindow((Parent) new CreateNewAnnotationController(
                this.controller).getView().getRoot()));
        this.removeAnnotation.setText("Rimuovi");
        this.removeAnnotation.setOnMouseClicked(event -> openWindow((Parent) new RemoveTDLController(this.controller)
                .getView().getRoot()));
        this.checkBoxes =  FXCollections.observableArrayList();
        this.listView = new ListView<>();
        this.updateTDLView();
        this.controller.allAnnotations().addListener((SetChangeListener<ToDoAction>) c -> {
            if (c.wasAdded()) {
                final ToDoAction change = c.getElementAdded();
                this.checkBoxes.add(this.createSingleToDoAction(change));
            } else if (c.wasRemoved()) {
                this.listView.getItems().clear();
                this.updateTDLView();
            }
        });
        this.checkBoxes.forEach(a -> a.setOnAction(event -> this.controller.changeCheck(a.getText())));
        this.listView.setItems(this.checkBoxes);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRoot() {
        this.vBox.prefWidthProperty().set(SCREEN_BOUNDS.getWidth() * V_BOX_WIDTH);
        this.vBox.prefHeightProperty().set(SCREEN_BOUNDS.getHeight() * V_BOX_HEIGHT);
        this.hBox.spacingProperty().bind(this.vBox.widthProperty().multiply(H_BOX_SPACING));
        List.of(this.addAnnotation, this.removeAnnotation).forEach(s -> s.prefHeightProperty().
                bind(this.vBox.heightProperty().multiply(BUTTON_HEIGHT)));
        List.of(this.addAnnotation, this.removeAnnotation).forEach(s -> s.prefWidthProperty().
                bind(this.vBox.widthProperty().multiply(BUTTON_WIDTH)));
        this.hBox.setAlignment(Pos.CENTER);
        this.toDoListLabel.prefWidthProperty().bind(this.vBox.widthProperty());
        this.toDoListLabel.prefHeightProperty().bind(this.vBox.heightProperty().multiply(TDL_LABEL_HEIGHT));
        this.containerTDL.prefHeightProperty().bind(this.vBox.heightProperty().multiply(CONTAINER_TDL_HEIGHT));
        this.toDoListLabel.setAlignment(Pos.CENTER);
        this.containerTDL.setContent(this.listView);
        this.listView.prefWidthProperty().bind(this.containerTDL.widthProperty());
        this.listView.prefHeightProperty().bind(this.containerTDL.heightProperty());
        this.listView.getItems().forEach(s -> s.prefHeightProperty().bind(this.containerTDL.heightProperty()
                .multiply(SINGLE_NOTE_HEIGHT)));
        return this.vBox;
    }
}
