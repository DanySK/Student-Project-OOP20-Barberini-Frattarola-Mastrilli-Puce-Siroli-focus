package oop.focus.diary.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.controller.*;
import oop.focus.homepage.model.EventManagerImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertNewCounterNameImpl implements Initializable {
    @FXML
    private Label newEventNameLabel;

    @FXML
    private TextField textField;

    @FXML
    private Button insertEvent;

    public InsertNewCounterNameImpl() {
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.newEventNameLabel.setText("Inserisci nome evento: ");
        this.insertEvent.setDisable(true);
        this.insertEvent.setText("Crea");
        this.textField.setOnMouseClicked(event -> this.insertEvent.setDisable(false));
        this.insertEvent.setOnMouseClicked(event -> this.getText());
    }
    public String getText() {
        UseTotalTimeController.getTotalTimeController().addValue(this.textField.getText());
        return this.textField.getText();
    }
}
