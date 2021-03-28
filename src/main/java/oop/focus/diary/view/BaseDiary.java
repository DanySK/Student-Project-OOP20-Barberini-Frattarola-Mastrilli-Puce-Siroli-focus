package oop.focus.diary.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BaseDiary implements Initializable {
    private static final String WINDOW_NEW_PAGE_PATH = "/layouts/diary/windowAddPage.fxml";

    @FXML
    private ScrollPane containerDiaryLayout;

    @FXML
    private Button addPage;

    @FXML
    private Button toDoListButton;

    @FXML
    private GridPane grid;
    @FXML
    private Button removePage;


    @FXML
    public final void modifyDiary(final ActionEvent event) {
        try {
        final Parent root = FXMLLoader.load(this.getClass().getResource(WINDOW_NEW_PAGE_PATH));
        final Scene scene = new Scene(root);
            final Stage window = new Stage();
            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modifyList(final ActionEvent event) {

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.addPage.setText("Aggiungi");
        this.removePage.setText("Rimuovi");
        this.removePage.setDisable(true);
        this.containerDiaryLayout.setContent(new PagesViewImpl(this.removePage).getAccordion());
    }
}
