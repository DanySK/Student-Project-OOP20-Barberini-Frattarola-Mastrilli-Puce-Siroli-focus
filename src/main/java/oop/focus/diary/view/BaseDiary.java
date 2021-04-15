package oop.focus.diary.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.DailyMoodController;
import oop.focus.diary.controller.DiaryPages;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.RemovePageController;
import oop.focus.diary.controller.RemoveTDLController;
import oop.focus.diary.controller.ToDoListController;


public class BaseDiary implements Initializable, View {
    private static final double DIARY_LABEL_HEIGHT = 0.1;
    private static final double TDL_LABEL_HEIGHT = 0.1;
    private static final double CONTAINER_DIARY_HEIGHT = 0.7;
    private static final double CONTAINER_TDL_HEIGHT = 0.4;
    private static final double BUTTON_WIDTH = 0.3;
    private static final int INSETS = 20;
    @FXML
    private BorderPane pane;

    @FXML
    private Label toDoListLabel, diaryLabel;

    @FXML
    private ScrollPane containerDiaryLayout;

    @FXML
    private Button addPage;

    @FXML
    private Button removePage;

    @FXML
    private ScrollPane containerToDoList;

    @FXML
    private Button removeAnnotation;

    @FXML
    private Button addAnnotation;

    @FXML
    private Label dailyMoodLabel;

    @FXML
    private BorderPane containerIcons;
    private Parent root;
    private final ToDoListController toDoListController;
    private final DiaryPages diaryController;
    private final DailyMoodController manager;
    public BaseDiary(final ToDoListController toDoListController, final DiaryPages diaryController, final DailyMoodController manager) {
        this.toDoListController = toDoListController;
        this.diaryController = diaryController;
        this.manager = manager;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.BASE_DIARY.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setProperties();
    }

    /**
     * The method creates a new HBox, composed by two different vBox: one that manages the right's part, and
     * one that manages the left's part.
     */
    private void setProperties() {
        final HBox principalBox = new HBox();
        principalBox.getChildren().add(this.getLeftVBox());
        principalBox.getChildren().add(this.getRightVBox());
        this.pane.setCenter(principalBox);
    }

    /**
     * The method creates a new VBox, filled by the components that manage the diary(two buttons, a label and
     * the container of pages' diary). The method manages the dimension of each single component too.
     * @return  the new VBox created
     */
    private VBox getLeftVBox() {
        final VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(this.diaryLabel, this.containerDiaryLayout);
        leftVBox.prefHeightProperty().bind(this.pane.heightProperty());
        leftVBox.setPadding(new Insets(INSETS));
        final HBox diaryButtons = new HBox(this.removePage, this.addPage);
        diaryButtons.prefWidthProperty().bind(leftVBox.widthProperty());
        diaryButtons.setPadding(new Insets(INSETS));
        diaryButtons.setAlignment(Pos.CENTER);
        diaryButtons.setSpacing(INSETS);
        leftVBox.getChildren().add(diaryButtons);
        this.diaryLabel.prefWidthProperty().bind(this.containerDiaryLayout.widthProperty());
        this.diaryLabel.prefHeightProperty().bind(this.containerDiaryLayout.heightProperty().multiply(DIARY_LABEL_HEIGHT));
        this.containerDiaryLayout.prefHeightProperty().bind(this.pane.heightProperty().multiply(CONTAINER_DIARY_HEIGHT));
        List.of(this.addPage, this.removePage).forEach(s -> s.prefHeightProperty().bind(this.containerDiaryLayout.heightProperty().multiply(DIARY_LABEL_HEIGHT)));
        List.of(this.addPage, this.removePage).forEach(s -> s.prefWidthProperty().bind(this.containerDiaryLayout.widthProperty().multiply(BUTTON_WIDTH)));
        return leftVBox;
    }

    /**
     * The method creates a new VBox, filled by the components that manage the ToDoList(two buttons, a label and
     * the container of ToDoActions), and with dailyMood's section.
     * The method manages the dimension of each single component too.
     * @return  the new VBox created.
     */
    private VBox getRightVBox() {
        final VBox vBoxRight = new VBox();
        vBoxRight.getChildren().addAll(this.toDoListLabel, this.containerToDoList);
        vBoxRight.setPadding(new Insets(INSETS));
        final HBox tdlButton = new HBox(this.removeAnnotation, this.addAnnotation);
        tdlButton.setPadding(new Insets(INSETS));
        tdlButton.setSpacing(INSETS);
        tdlButton.setAlignment(Pos.CENTER);
        vBoxRight.getChildren().add(tdlButton);
        vBoxRight.getChildren().addAll(this.dailyMoodLabel, this.containerIcons);
        VBox.setMargin(this.dailyMoodLabel, new Insets(INSETS));
        this.toDoListLabel.prefHeightProperty().bind(vBoxRight.heightProperty().multiply(TDL_LABEL_HEIGHT));
        this.toDoListLabel.prefWidthProperty().bind(this.containerToDoList.widthProperty());
        this.containerToDoList.prefHeightProperty().bind(this.pane.heightProperty().multiply(CONTAINER_TDL_HEIGHT));
        this.dailyMoodLabel.prefWidthProperty().bind(this.toDoListLabel.widthProperty());
        this.dailyMoodLabel.prefHeightProperty().bind(this.toDoListLabel.heightProperty());
        this.containerIcons.prefHeightProperty().bind(this.pane.heightProperty().multiply(BUTTON_WIDTH));
        this.containerIcons.prefWidthProperty().bind(this.dailyMoodLabel.widthProperty());
        List.of(this.addAnnotation, this.removeAnnotation).forEach(s -> s.prefHeightProperty().bind(this.containerToDoList.heightProperty().multiply(BUTTON_WIDTH)));
        List.of(this.addAnnotation, this.removeAnnotation).forEach(s -> s.prefWidthProperty().bind(this.containerToDoList.widthProperty().multiply(BUTTON_WIDTH)));
        return vBoxRight;
    }

    private static void openWindow(final Parent root) {
        final Scene scene = new Scene(root);
        final Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.dailyMoodLabel.setText("Come ti senti oggi?");
        this.toDoListLabel.setText("To Do List");
        this.diaryLabel.setText("Diario");
        this.addPage.setText("Aggiungi");
        this.addPage.setOnMouseClicked(event -> openWindow((Parent) new WindowCreateNewPage(this.diaryController).getRoot()));
        this.removePage.setText("Rimuovi");
        this.removePage.setOnMouseClicked(event -> openWindow((Parent) new RemovePageController(this.diaryController).getView().getRoot()));
        this.addAnnotation.setText("Aggiungi");
        this.addAnnotation.setOnMouseClicked(event -> openWindow((Parent) new WindowCreateNewAnnotation(this.toDoListController).getRoot()));
        this.removeAnnotation.setText("Rimuovi");
        this.removeAnnotation.setOnMouseClicked(event -> openWindow((Parent) new RemoveTDLController(this.toDoListController).getView().getRoot()));
        this.containerDiaryLayout.setContent(this.diaryController.getView().getRoot());
        this.containerToDoList.setContent(this.toDoListController.getView().getRoot());
        this.containerIcons.setCenter(this.manager.getView().getRoot());
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}
