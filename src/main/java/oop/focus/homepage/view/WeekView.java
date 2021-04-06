package oop.focus.homepage.view;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.joda.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import oop.focus.calendar.controller.CalendarDayController;
import oop.focus.calendar.controller.CalendarDayControllerImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;
import oop.focus.homepage.controller.WeekController;

public class WeekView implements View, Initializable {

    @FXML
    private AnchorPane weekDaysPane;

    @FXML
    private ScrollPane weekDaysScroller;

    @FXML
    private Label thisWeek;

    @FXML
    private Button nextWeek;

    @FXML
    private Button lastWeek;

    @FXML
    private Button newEvent;

    private Parent root;
    private LocalDate startWeek;
    private final WeekController controller;

    public WeekView(final WeekController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/pane.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final LocalDate today = LocalDate.now();
        this.startWeek = today.minusDays(today.getDayOfWeek() - 1);
        this.setWeekDays();
    }

    @FXML
    public final void addNewEvent(final ActionEvent event) {
        final NewEventWeekView newEvent = new NewEventWeekView(this.controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene(newEvent.getRoot()));
        stage.show();
    }

    @FXML
    public final void lastWeek(final ActionEvent event) {
        this.startWeek = this.startWeek.minusDays(Constants.DAYS_PER_WEEK);
        this.setWeekDays();
    }

    private void setWeekDays() {
        LocalDate date = this.startWeek;
        final HBox hbox = new HBox();

        for (int i = 0; i < Constants.DAYS_PER_WEEK; i++) {
            final CalendarDayController day = new CalendarDayControllerImpl(new DayImpl(date), 200, 500);
            day.setFormat(Format.NORMAL);
            day.setSpacing(Constants.SPACING);
            day.buildDay();
            day.getContainer().prefWidthProperty().bind(hbox.widthProperty().divide(Constants.DAYS_PER_WEEK));
            hbox.getChildren().add(day.getContainer());
            date = date.plusDays(1);
        }
        hbox.prefWidthProperty().bind(weekDaysScroller.widthProperty());

        this.weekDaysScroller.setContent(hbox);
    }

    @FXML
    public final void nextWeek(final ActionEvent event) {
        this.startWeek = this.startWeek.plusDays(Constants.DAYS_PER_WEEK);
        this.setWeekDays();
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    private class Constants {
        private static final int DAYS_PER_WEEK = 7;
        private static final int SPACING = 50;
    }
}
