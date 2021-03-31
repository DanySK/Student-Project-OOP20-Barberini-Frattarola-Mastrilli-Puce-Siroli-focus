package oop.focus.diary.view;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.focus.diary.controller.UseTDLController;
import java.net.URL;
import java.util.ResourceBundle;

public class WindowCreateNewAnnotation implements Initializable {
    @FXML
    private Button createButton;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField annotationTitle;

    private void createNewAnnotation() {
        if (!this.annotationTitle.getText().isEmpty()) {
            UseTDLController.getCF().addNote(this.annotationTitle.getText());
        }
    }
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.createButton.setText("Crea");
        this.titleLabel.setText("Aggiungi nota");
        this.createButton.setOnMouseClicked(event -> createNewAnnotation());
    }
}
