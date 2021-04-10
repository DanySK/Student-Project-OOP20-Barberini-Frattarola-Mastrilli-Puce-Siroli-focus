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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.*;
import oop.focus.diary.model.DailyMoodManager;


public class BaseDiary implements Initializable, View {
    private static final double LEFT_VBOX_WIDTH = 0.55;
    private static final double DIARY_LABEL_HEIGHT = 0.1;
    private static final double TDL_LABEL_HEIGHT = 0.25;
    private static final double CONTAINER_DIARY_HEIGHT = 0.7;
    private static final double CONTAINER_TDL_HEIGHT = 0.4;
    private static final double BUTTON_WIDTH = 0.3;
    private static final int INSETS = 20;
    @FXML
    private Pane pane;

    @FXML
    private Label toDoListLabel;

    @FXML
    private Label diaryLabel;

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
    private final ToDoListControllerImpl toDoListController;
    private final DiaryPagesImpl diaryController;
    private final DailyMoodControllerImpl manager;
    public BaseDiary(final ToDoListControllerImpl toDoListController, final DiaryPagesImpl diaryController, final DailyMoodControllerImpl manager) {
        this.toDoListController = toDoListController;
        this.diaryController = diaryController;
        this.manager = manager;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.BASE_DIARY.getPath()));
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
        HBox principalBox = new HBox();
        principalBox.getChildren().add(this.setLeftVBox(principalBox));
        principalBox.getChildren().add(this.setRightVBox());
        this.pane.getChildren().add(principalBox);
        principalBox.prefWidthProperty().bind(this.pane.widthProperty());
        principalBox.prefHeightProperty().bind(this.pane.heightProperty());
    }

    /**
     * The method creates a new VBox, filled by the components that manage the diary(two buttons, a label and
     * the container of pages' diary). The method manages the dimension of each single component too.
     * @param principalBox  the box in which insert the VBox created
     * @return  the new VBox created
     */
    private VBox setLeftVBox(final HBox principalBox) {
        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(this.diaryLabel, this.containerDiaryLayout);
        leftVBox.prefWidthProperty().bind(principalBox.widthProperty().multiply(LEFT_VBOX_WIDTH));
        leftVBox.prefHeightProperty().bind(this.pane.heightProperty());
        leftVBox.setPadding(new Insets(INSETS));
        HBox diaryButtons = new HBox(this.removePage, this.addPage);
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
    private VBox setRightVBox() {
        VBox vBoxRight = new VBox();
        vBoxRight.getChildren().addAll(this.toDoListLabel, this.containerToDoList);
        vBoxRight.setPadding(new Insets(INSETS));
        HBox tdlButton = new HBox(this.removeAnnotation, this.addAnnotation);
        tdlButton.setPadding(new Insets(INSETS));
        tdlButton.setSpacing(INSETS);
        tdlButton.setAlignment(Pos.CENTER);
        vBoxRight.getChildren().add(tdlButton);
        vBoxRight.getChildren().addAll(this.dailyMoodLabel, this.containerIcons);
        VBox.setMargin(this.dailyMoodLabel, new Insets(INSETS));
        this.toDoListLabel.prefHeightProperty().bind(this.containerToDoList.heightProperty().multiply(TDL_LABEL_HEIGHT));
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
        this.addPage.setOnMouseClicked(event -> openWindow((Parent) new WindowCreateNewPage(diaryController).getRoot()));
        this.removePage.setText("Rimuovi");
        this.removePage.setDisable(true);
        this.addAnnotation.setText("Aggiungi");
        this.addAnnotation.setOnMouseClicked(event -> openWindow((Parent) new WindowCreateNewAnnotation(this.toDoListController).getRoot()));
        this.removeAnnotation.setText("Rimuovi");
        this.containerDiaryLayout.setContent(new PagesViewImpl(this.removePage, this.pane.widthProperty(), this.pane.heightProperty(), this.diaryController).getAccordion());
        final AnnotationViewImpl annotationView = new AnnotationViewImpl(this.removeAnnotation, this.containerDiaryLayout.heightProperty(), toDoListController);
        annotationView.getListView().prefHeightProperty().bind(this.containerDiaryLayout.heightProperty());
        annotationView.getListView().prefWidthProperty().bind(this.containerToDoList.widthProperty());
        this.containerToDoList.setContent(annotationView.getListView());
        final DailyMoodViewImpl iconView = new DailyMoodViewImpl(this.manager);
        this.containerIcons.setTop(iconView.getRoot());
        this.containerIcons.setBottom(iconView.getButton());
        iconView.getButton().prefWidthProperty().bind(this.dailyMoodLabel.widthProperty().multiply(BUTTON_WIDTH));
        iconView.getButton().prefHeightProperty().bind(this.dailyMoodLabel.heightProperty());
        BorderPane.setAlignment(iconView.getButton(), Pos.TOP_CENTER);
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}
