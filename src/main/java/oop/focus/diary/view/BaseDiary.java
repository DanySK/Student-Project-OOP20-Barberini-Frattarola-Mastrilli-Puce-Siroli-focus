package oop.focus.diary.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BaseDiary implements Initializable {
    private static final String WINDOW_NEW_PAGE_PATH = "/layouts/diary/windowAddPage.fxml";
    private static final String WINDOW_NEW_ANNOTATION_PATH = "/layouts/diary/windowAddAnnotation.fxml";

    @FXML
    private ScrollPane containerDiaryLayout;

    @FXML
    private Button addPage;
    @FXML
    private ScrollPane containerToDoList;
    @FXML
    private Button addAnnotation;
    @FXML
    private Button removeAnnotation;

    @FXML
    private BorderPane containerIcons;
    @FXML
    private Button removePage;


    public final void modifyDiary(final String path) {
        try {
        final Parent root = FXMLLoader.load(this.getClass().getResource(path));
        final Scene scene = new Scene(root);
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.addPage.setText("Aggiungi");
        this.addPage.setOnMouseClicked(event -> modifyDiary(WINDOW_NEW_PAGE_PATH));
        this.removePage.setText("Rimuovi");
        this.removePage.setDisable(true);
        this.addAnnotation.setText("Aggiungi");
        this.addAnnotation.setOnMouseClicked(event -> modifyDiary(WINDOW_NEW_ANNOTATION_PATH));
        this.removeAnnotation.setText("Rimuovi");
        this.containerDiaryLayout.setContent(new PagesViewImpl(this.removePage).getAccordion());
        final AnnotationViewImpl annotationView = new AnnotationViewImpl(this.removeAnnotation);
        annotationView.getListView().setPrefHeight(containerDiaryLayout.getPrefHeight());
        annotationView.getListView().setPrefWidth(containerDiaryLayout.getPrefWidth());
        this.containerToDoList.setContent(annotationView.getListView());
        try {
            final DailyMoodViewImpl iconView = new DailyMoodViewImpl();
            iconView.getGrid().setMaxWidth(containerIcons.getMaxWidth());
            iconView.getGrid().setMaxHeight(containerIcons.getMaxHeight());
            this.containerIcons.setTop(iconView.getGrid());
            this.containerIcons.setBottom(iconView.getButton());
            BorderPane.setAlignment(iconView.getButton(), Pos.TOP_CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
