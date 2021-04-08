package oop.focus.diary.view;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import oop.focus.diary.controller.FXMLPaths;


public class BaseDiary implements Initializable {
    private static final String WINDOW_NEW_ANNOTATION_PATH = "/layouts/diary/windowAddAnnotation.fxml";
    private static final double LEFT_VBOX_WIDTH = 0.55;
    private static final double RIGHT_VBOX_WIDTH = 0.45;
    private static final double LABEL_HEIGHT = 0.1;
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
    private Dimension2D dim;
    public BaseDiary(final Dimension2D dimension2D) {
        dim = dimension2D;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.BASE_DIARY.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setProperties(dimension2D);
    }
    private void setProperties(final Dimension2D dimension2D) {
        HBox principalBox = new HBox();
        VBox vboxLeft = new VBox();
        vboxLeft.getChildren().addAll(this.diaryLabel, this.containerDiaryLayout);
        vboxLeft.prefWidthProperty().bind(principalBox.widthProperty().multiply(LEFT_VBOX_WIDTH));
        vboxLeft.prefHeightProperty().bind(this.pane.heightProperty());
        vboxLeft.setPadding(new Insets(INSETS));
        HBox diaryButtons = new HBox(this.removePage, this.addPage);
        diaryButtons.prefWidthProperty().bind(vboxLeft.widthProperty());
        diaryButtons.setPadding(new Insets(INSETS));
        diaryButtons.setAlignment(Pos.CENTER);
        diaryButtons.setSpacing(INSETS);
        vboxLeft.getChildren().add(diaryButtons);
        principalBox.getChildren().add(vboxLeft);
        VBox vBoxRight = new VBox();
        vBoxRight.prefWidthProperty().bind(principalBox.widthProperty().multiply(RIGHT_VBOX_WIDTH));
        vBoxRight.getChildren().addAll(this.toDoListLabel, this.containerToDoList);
        vBoxRight.setPadding(new Insets(INSETS));
        HBox tdlButton = new HBox(this.removeAnnotation, this.addAnnotation);
        HBox.setMargin(this.removeAnnotation, new Insets(INSETS));
        tdlButton.setAlignment(Pos.CENTER);
        vBoxRight.getChildren().add(tdlButton);
        vBoxRight.getChildren().addAll(this.dailyMoodLabel, this.containerIcons);
        VBox.setMargin(this.dailyMoodLabel, new Insets(INSETS));
        principalBox.getChildren().add(vBoxRight);
        this.pane.getChildren().add(principalBox);
        principalBox.prefWidthProperty().bind(this.pane.widthProperty());
        principalBox.prefHeightProperty().bind(this.pane.heightProperty());
        this.diaryLabel.prefWidthProperty().bind(this.containerDiaryLayout.widthProperty());
        this.diaryLabel.prefHeightProperty().bind(this.containerDiaryLayout.heightProperty().multiply(LABEL_HEIGHT));
        this.containerDiaryLayout.prefHeightProperty().bind(this.pane.heightProperty().multiply(CONTAINER_DIARY_HEIGHT));
        List.of(this.addPage, this.removePage).forEach(s -> s.prefHeightProperty().bind(this.containerDiaryLayout.heightProperty().multiply(LABEL_HEIGHT)));
        List.of(this.addPage, this.removePage).forEach(s -> s.prefWidthProperty().bind(this.containerDiaryLayout.widthProperty().multiply(BUTTON_WIDTH)));
        this.toDoListLabel.prefWidthProperty().bind(this.containerToDoList.widthProperty());
        this.containerToDoList.prefHeightProperty().bind(this.pane.heightProperty().multiply(CONTAINER_TDL_HEIGHT));
        this.dailyMoodLabel.prefWidthProperty().bind(this.toDoListLabel.widthProperty());
        this.dailyMoodLabel.prefHeightProperty().bind(this.toDoListLabel.heightProperty());
        this.containerIcons.prefHeightProperty().bind(this.pane.heightProperty().multiply(BUTTON_WIDTH));
        this.containerIcons.prefWidthProperty().bind(this.dailyMoodLabel.widthProperty());
        List.of(this.addAnnotation, this.removeAnnotation).forEach(s -> s.prefHeightProperty().bind(this.containerToDoList.heightProperty().multiply(LABEL_HEIGHT)));
        List.of(this.addAnnotation, this.removeAnnotation).forEach(s -> s.prefWidthProperty().bind(this.containerToDoList.widthProperty().multiply(BUTTON_WIDTH)));
    }

    private static void openWindow(final Parent root) {
        final Scene scene = new Scene(root);
        final Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
    public final Parent getRoot() {
        return this.root;
    }
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.dailyMoodLabel.setText("Come ti senti oggi?");
        this.toDoListLabel.setText("To Do List");
        this.diaryLabel.setText("Diario");
        this.addPage.setText("Aggiungi");
        this.addPage.setOnMouseClicked(event -> openWindow(new WindowCreateNewPage().getRoot()));
        this.removePage.setText("Rimuovi");
        this.removePage.setDisable(true);
        this.addAnnotation.setText("Aggiungi");
        this.addAnnotation.setOnMouseClicked(event -> openWindow(new WindowCreateNewAnnotation().getRoot()));
        this.removeAnnotation.setText("Rimuovi");
        this.containerDiaryLayout.setContent(new PagesViewImpl(this.removePage, this.pane.widthProperty(), this.pane.heightProperty()).getAccordion());
        final AnnotationViewImpl annotationView = new AnnotationViewImpl(this.removeAnnotation, this.containerDiaryLayout.heightProperty());
        annotationView.getListView().prefHeightProperty().bind(this.containerDiaryLayout.heightProperty());
        annotationView.getListView().prefWidthProperty().bind(this.containerToDoList.widthProperty());
        this.containerToDoList.setContent(annotationView.getListView());
        try {
            final DailyMoodViewImpl iconView = new DailyMoodViewImpl();
            iconView.getGrid().setMaxWidth(this.containerIcons.getMaxWidth());
            iconView.getGrid().setMaxHeight(this.containerIcons.getMaxHeight());
            this.containerIcons.setTop(iconView.getGrid());
            this.containerIcons.setBottom(iconView.getButton());
            BorderPane.setAlignment(iconView.getButton(), Pos.TOP_CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
