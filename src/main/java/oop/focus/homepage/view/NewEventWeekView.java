package oop.focus.homepage.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.common.Repetition;
import oop.focus.homepage.controller.WeekController;
import oop.focus.homepage.model.EventImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class NewEventWeekView implements Initializable, View {
    @FXML
    private AnchorPane paneNewEvent;

    @FXML
    private Label newEvent;

    @FXML
    private Label name, startDay, endDay, startHour, endHour, repetition;

    @FXML
    private DatePicker datePickerStart, datePickerEnd;

    @FXML
    private TextField textFieldName;

    @FXML
    private ComboBox<String> startHourComboBox, endHourComboBox, startMinuteComboBox, endMinuteComboBox, repetitionComboBox;

    @FXML
    private Button save, goBack, delete;

    private final WeekController controller;
    private Parent root;

    public NewEventWeekView(final WeekController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/newEvent.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final ComboBoxFiller filler = new ComboBoxFiller();
        this.fillComboBox(filler);
    }

    private void fillComboBox(final ComboBoxFiller filler) {
        this.endHourComboBox.setItems(filler.getHourAndMinute(Constants.HOUR_PER_DAY).getItems());
        this.startHourComboBox.setItems(filler.getHourAndMinute(Constants.HOUR_PER_DAY).getItems());

        this.endMinuteComboBox.setItems(filler.getHourAndMinute(Constants.MINUTE_PER_HOUR).getItems());
        this.startMinuteComboBox.setItems(filler.getHourAndMinute(Constants.MINUTE_PER_HOUR).getItems());

        this.repetitionComboBox.setItems(filler.getRepetition().getItems());
    }

    @FXML
    public final void delete(final ActionEvent event) {

        this.textFieldName.setText(" ");
        this.repetitionComboBox.getSelectionModel().clearSelection();
        this.startHourComboBox.getSelectionModel().clearSelection();
        this.endHourComboBox.getSelectionModel().clearSelection();
        this.startMinuteComboBox.getSelectionModel().clearSelection();
        this.endMinuteComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public final void goBack(final ActionEvent event) {
        final Stage stage = (Stage) this.paneNewEvent.getScene().getWindow();
        stage.close();
    }

    @FXML
    public final void save(final ActionEvent event) {
        final java.time.LocalDate start = this.datePickerStart.getValue();
        final LocalDate startDate = new LocalDate(start.getYear(), start.getMonthValue(), start.getDayOfMonth());

        final java.time.LocalDate end = this.datePickerEnd.getValue();
        final LocalDate endDate = new LocalDate(end.getYear(), end.getMonthValue(), end.getDayOfMonth());

        final LocalTime startTime = new LocalTime(Integer.valueOf(this.startHourComboBox.getSelectionModel().getSelectedItem()), Integer.valueOf(this.startMinuteComboBox.getSelectionModel().getSelectedItem()));
        final LocalTime endTime = new LocalTime(Integer.valueOf(this.endHourComboBox.getSelectionModel().getSelectedItem()), Integer.valueOf(this.endMinuteComboBox.getSelectionModel().getSelectedItem()));
        this.controller.addNewEvent(new EventImpl(this.textFieldName.getText(), startDate.toLocalDateTime(startTime), endDate.toLocalDateTime(endTime), Repetition.valueOf(this.repetitionComboBox.getSelectionModel().getSelectedItem())));
        this.goBack(event);
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    private class Constants {
        public static final int HOUR_PER_DAY = 24;
        public static final int MINUTE_PER_HOUR = 60;
    }
}
