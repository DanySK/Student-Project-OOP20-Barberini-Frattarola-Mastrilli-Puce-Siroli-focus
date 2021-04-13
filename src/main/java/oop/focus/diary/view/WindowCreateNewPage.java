package oop.focus.diary.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.DiaryPages;
import oop.focus.diary.controller.FXMLPaths;


public class WindowCreateNewPage implements Initializable, View {

    @FXML
    private Label insertTitleLabel;

    @FXML
    private TextField titleDiaryPage;

    @FXML
    private TextArea content;

    @FXML
    private Button create;
    private Parent root;
    private final DiaryPages controller;
    public WindowCreateNewPage(final DiaryPages controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.INSERT_DIARY_PAGE.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public final void createNewPage(final ActionEvent event) {
        if (!this.titleDiaryPage.getText().isEmpty() && !this.content.getText().isEmpty()) {
            final Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
            final Matcher m = p.matcher(this.titleDiaryPage.getText());
            if (m.find()) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Il titolo della pagina di diario non deve avere caratteri speciali");
                alert.showAndWait();
            } else {
               controller.createPage(this.titleDiaryPage.getText(), this.content.getText());
                final Stage stage = (Stage) this.content.getScene().getWindow();
                stage.close();
            }
        } else {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Inserire nome e contenuto della pagina di diario");
            alert.showAndWait();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.insertTitleLabel.setText("Titolo");
        this.create.setText("Crea");
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}

