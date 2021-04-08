package oop.focus.diary.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.UseControllerDiary;

public class WindowCreateNewPage implements Initializable {

    @FXML
    private Label insertTitleLabel;

    @FXML
    private TextField titleDiaryPage;

    @FXML
    private TextArea content;

    @FXML
    private Button create;
    private Parent root;
    public WindowCreateNewPage() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.INSERT_DIARY_PAGE.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Parent getRoot() {
        return this.root;
    }
    @FXML
    public final void createNewPage(final ActionEvent event) {
        if (!this.titleDiaryPage.getText().isEmpty() && !this.content.getText().isEmpty()) {
           UseControllerDiary.getCF().createPage(this.titleDiaryPage.getText(), this.content.getText());
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.insertTitleLabel.setText("Titolo");
        this.create.setText("Crea");
    }

}

