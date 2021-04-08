package oop.focus.diary.view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.UseTDLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WindowCreateNewAnnotation implements Initializable {
    @FXML
    private Button createButton;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField annotationTitle;
    private Parent root;
    public WindowCreateNewAnnotation() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.INSERT_TDL_ANNOTATION.getPath()));
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
    private void createNewAnnotation() {
        if (!this.annotationTitle.getText().isEmpty()) {
            UseTDLController.getCF().addNote(this.annotationTitle.getText());
            Stage stage = (Stage) this.annotationTitle.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("La nota non deve essere vuota");
            alert.showAndWait();
        }
    }
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.createButton.setText("Crea");
        this.titleLabel.setText("Aggiungi nota");
        this.createButton.setOnMouseClicked(event -> this.createNewAnnotation());
    }
}
