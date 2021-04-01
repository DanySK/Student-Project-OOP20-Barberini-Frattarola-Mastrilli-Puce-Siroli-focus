package oop.focus.homepage.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysViewImpl;

public class WeekView  implements Initializable {

    @FXML
    private Pane paneWeek;

    @FXML
    private Button previousWeek, nextWeek;

    @FXML
    private Label thisWeek, month;

    @FXML
    private ScrollPane scrollPaneEvents;

    @FXML
    private HBox hbox;

    private LocalDate startOfWeek, endOfWeek;

    private List<Node> list;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.list = new ArrayList<>();
        final LocalDate today = LocalDate.now();
        this.startOfWeek = today.minusDays(today.getDayOfWeek() - 1);
        this.endOfWeek = startOfWeek.plusDays(6);
        this.setWeek();
    }

    @FXML
    public final void lastWeek(final ActionEvent event) {
        this.startOfWeek = this.startOfWeek.minusDays(7);
        this.endOfWeek = this.startOfWeek.plusDays(6);
        this.hbox.getChildren().removeAll(list);
        this.list = new ArrayList<>();
        this.setWeek();
    }

    @FXML
    public final void nextWeek(final ActionEvent event) {
        this.startOfWeek = this.startOfWeek.plusDays(7);
        this.endOfWeek = this.startOfWeek.plusDays(6);
        this.hbox.getChildren().removeAll(list);
        this.list = new ArrayList<>();
        this.setWeek();
    }

    public final void setWeek() {

        this.month.setText(startOfWeek.toString() + " " + "-" + " " + endOfWeek.toString());
        LocalDate date = this.startOfWeek;

        for (int i = 0; i < 7; i++) {
            final DayImpl day = new DayImpl(date);
            /*final CalendarDaysViewImpl dayView = new CalendarDaysViewImpl(day, 200, 300, 50);

            dayView.buildDay();

            dayView.getContainer().prefWidthProperty().bind(hbox.widthProperty().divide(7));
            dayView.getContainer().prefHeightProperty().bind(hbox.heightProperty());

            this.list.add(dayView.getContainer());

            hbox.getChildren().add(dayView.getContainer());

            date = date.plusDays(1);*/
        }
        this.scrollPaneEvents.setContent(hbox);
    }
}
