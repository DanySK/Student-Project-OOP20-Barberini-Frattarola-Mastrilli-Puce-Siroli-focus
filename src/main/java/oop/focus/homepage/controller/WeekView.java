package oop.focus.homepage.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.calendar.view.CalendarDaysViewImpl;

public class WeekView  implements Initializable {

    @FXML
    private Pane paneWeek;

    @FXML
    private Button previousWeek, nextWeek;

    @FXML
    private Label thisWeek;

    @FXML
    private HBox hbox;

    private LocalDate startOfWeek;
    private LocalDate endOfWeek;

    private final int weekDays = 7;
    private final int distance = 6;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final LocalDate today = LocalDate.now();
        this.startOfWeek = today.minusDays(today.getDayOfWeek() - 1);
        this.endOfWeek = startOfWeek.plusDays(this.distance);
        this.setWeek();
    }

    @FXML
    public final void lastWeek(final ActionEvent event) {
        /*this.startOfWeek = this.startOfWeek.minusDays(this.weekDays);
        this.endOfWeek = this.startOfWeek.plusDays(this.distance);
        this.paneWeek.getChildren().remove(hbox);
        this.hbox = new HBox();
        this.setWeek();*/
    }

    @FXML
    public final void nextWeek(final ActionEvent event) {
        this.startOfWeek = this.startOfWeek.plusDays(this.weekDays);
        this.endOfWeek = this.startOfWeek.plusDays(this.distance);
        this.paneWeek.getChildren().remove(hbox);
        this.hbox = new HBox();
        this.setWeek();
    }

    public final void setWeek() {
        hbox = new HBox();
        for (final LocalDate date = this.startOfWeek; !date.isAfter(endOfWeek); date.plusDays(1)) {
            final DayImpl day = new DayImpl(date);
            final CalendarDaysView dayView = new CalendarDaysViewImpl(day, 200, 450, 50);
            dayView.buildDay();
            dayView.getScroller().prefWidthProperty().bind(hbox.widthProperty().divide(this.weekDays));
            hbox.getChildren().add(dayView.getScroller());
        }
        this.paneWeek.getChildren().add(hbox);
    }
}
