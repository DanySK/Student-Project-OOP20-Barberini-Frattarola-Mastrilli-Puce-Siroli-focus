package oop.focus.homepage.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.finance.model.Repetition;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;

public class NewEventView implements Initializable {

    @FXML
    private Pane paneNewEvent;

    @FXML
    private Label newEvent;

    @FXML
    private Label nameNewEvent;

    @FXML
    private Label startHour;

    @FXML
    private Label endHour;

    @FXML
    private Label repetition;

    @FXML
    private ComboBox<String> startHourChoice;

    @FXML
    private ComboBox<String> repetitionChoice;

    @FXML
    private ComboBox<String> endHourChoice;

    @FXML
    private Button back;

    @FXML
    private Button saveSelection;

    @FXML
    private Button deleteSelection;

    @FXML
    private ComboBox<String> startMinuteChoice;

    @FXML
    private ComboBox<String> endMinuteChoice;

    private Event event;

    private Repetition lastRep;

    private HomePageController controller;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        //this.controller = new HomePageController();
        for (final Repetition repetition : Repetition.values()) {
            this.repetitionChoice.getItems().add(repetition.getName());
        }
        for (int i = 0; i < 10; i++) {
            this.startMinuteChoice.getItems().add(String.valueOf("0" + i));
            this.endMinuteChoice.getItems().add(String.valueOf("0" + i));
            this.startHourChoice.getItems().add(String.valueOf("0" + i));
            this.endHourChoice.getItems().add(String.valueOf("0" + i));
        }
        for (int i = 10; i < 60; i++) {
            this.startMinuteChoice.getItems().add(String.valueOf(i));
            this.endMinuteChoice.getItems().add(String.valueOf(i));
        }
        for (int i = 10; i < 24; i++) {
            this.startHourChoice.getItems().add(String.valueOf(i));
            this.endHourChoice.getItems().add(String.valueOf(i));
        }

        this.lastRep = null;
    }

    @FXML
    public final void backButtonPress(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("/layouts/homepage/calendarHomePage.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    public final void deleteSelection(final ActionEvent event) {
        this.repetitionChoice.getSelectionModel().clearSelection();
        this.startHourChoice.getSelectionModel().clearSelection();
        this.endHourChoice.getSelectionModel().clearSelection();
        this.startMinuteChoice.getSelectionModel().clearSelection();
        this.endMinuteChoice.getSelectionModel().clearSelection();
    }

    @FXML
    public final void saveSelection(final ActionEvent event) throws IOException {
        if (!this.startHourChoice.getSelectionModel().isEmpty() && !this.endHourChoice.getSelectionModel().isEmpty()
                && !this.startMinuteChoice.getSelectionModel().isEmpty() && !this.endMinuteChoice.getSelectionModel().isEmpty()
                && !this.repetitionChoice.getSelectionModel().isEmpty()) {
            final LocalDate date = LocalDate.now();
            final LocalTime start = new LocalTime(Integer.valueOf(this.startHourChoice.getSelectionModel().getSelectedItem()), Integer.valueOf(this.startMinuteChoice.getSelectionModel().getSelectedItem()));
            final LocalTime end = new LocalTime(Integer.valueOf(this.endHourChoice.getSelectionModel().getSelectedItem()), Integer.valueOf(this.endMinuteChoice.getSelectionModel().getSelectedItem()));
            final String repetition = this.repetitionChoice.getSelectionModel().getSelectedItem();
            for (final Repetition rep : Repetition.values()) {
                if (rep.getName().equals(repetition)) {
                    this.lastRep = rep;
                }
            }
            this.event = new EventImpl(this.nameNewEvent.getText(), date.toLocalDateTime(start), date.toLocalDateTime(end), this.lastRep);
            System.out.println(this.nameNewEvent.getText());
            this.backButtonPress(event);
        } else {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("I campi non sono stati riempiti correttamente!");
            alert.setContentText("Riempire i campi o tornare indietro");
 
            final Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
                alert.close();
            }
        }
    }

    public final void setText(final String eventName) {
        nameNewEvent.setText(eventName);
    }

}

