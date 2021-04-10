package oop.focus.diary.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.TotalTimeControllerImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InsertNewCounterNameImpl implements Initializable, View {
    private static final double LABEL_SIZE = 0.3;
    private static final double BUTTON_HEIGHT = 0.2;
    private static final double TEXT_FIELD_WIDTH = 0.4;
    @FXML
    private Label newEventNameLabel;

    @FXML
    private TextField textField;

    @FXML
    private Button insertEvent;

    @FXML
    private BorderPane pane;
    private Parent root;
    private final TotalTimeControllerImpl totalTimeController;
    public InsertNewCounterNameImpl(final TotalTimeControllerImpl totalTimeController) {
        this.totalTimeController = totalTimeController;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADD_EVENT_NAME_COUNTER.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setProperties();
    }
    private void setProperties() {
        this.newEventNameLabel.prefHeightProperty().bind(this.pane.heightProperty().multiply(LABEL_SIZE));
        this.newEventNameLabel.prefWidthProperty().bind(this.pane.widthProperty().multiply(LABEL_SIZE));
        this.textField.prefWidthProperty().bind(this.pane.widthProperty().multiply(TEXT_FIELD_WIDTH));
        this.textField.prefHeightProperty().bind(this.pane.heightProperty().multiply(LABEL_SIZE));
        this.insertEvent.prefWidthProperty().bind(this.pane.widthProperty().multiply(LABEL_SIZE));
        this.insertEvent.prefHeightProperty().bind(this.pane.heightProperty().multiply(BUTTON_HEIGHT));
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.newEventNameLabel.setText("Inserisci nome evento: ");
        this.insertEvent.setDisable(true);
        this.insertEvent.setText("Crea");
        this.textField.setOnKeyPressed(event -> this.insertEvent.setDisable(false));
        this.insertEvent.setOnMouseClicked(event -> this.getText());
    }
    public String getText() {
        if (this.textField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Inserire nome evento");
            alert.showAndWait();
        }
        totalTimeController.addValue(this.textField.getText());
        Stage stage = (Stage) this.insertEvent.getScene().getWindow();
        stage.close();
        return this.textField.getText();
    }

    @Override
    public Node getRoot() {
        return this.root;
    }
}
