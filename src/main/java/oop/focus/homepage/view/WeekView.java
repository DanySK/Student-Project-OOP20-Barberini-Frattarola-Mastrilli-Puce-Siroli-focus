package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;
import oop.focus.homepage.controller.WeekController;

public class WeekView  implements Initializable, View {

    @FXML
    private Pane paneWeek;

    @FXML
    private Label thisWeek, month;

    @FXML
    private Button nextWeek, previousWeek;

    @FXML
    private HBox hbox;

    @FXML
    private ScrollPane scrollPaneEvents;

    private LocalDate startOfWeek, endOfWeek;
    private List<Node> list;
    private Parent root;
    private WeekController controller;

    public WeekView(final WeekController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/week.fxml"));
        loader.setController(this);

        try {
            loader.load();
            this.root = loader.getRoot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.list = new ArrayList<>();
        final LocalDate today = LocalDate.now();
        this.startOfWeek = today.minusDays(today.getDayOfWeek() - 1);
        this.endOfWeek = startOfWeek.plusDays(6);
        this.month.setText(startOfWeek.toString() + " " + "-" + " " + endOfWeek.toString());
        this.setWeek();
    }

    @FXML
    public final void lastWeek(final ActionEvent event) {
        this.startOfWeek = this.startOfWeek.minusDays(7);
        this.endOfWeek = this.startOfWeek.plusDays(6);
        this.hbox.getChildren().removeAll(list);
        this.list.clear();
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
        LocalDate date = this.startOfWeek;

        for (int i = 0; i < 7; i++) {
            final DayImpl day = new DayImpl(date);

            final CalendarDaysViewImpl dayView = new CalendarDaysViewImpl(day, 200, 300);
            dayView.setFormat(Format.NORMAL);
            dayView.setSpacing(100);
            dayView.buildDay();

            dayView.getContainer().prefWidthProperty().bind(hbox.widthProperty().divide(7));
            dayView.getContainer().prefHeightProperty().bind(hbox.heightProperty());

            this.list.add(dayView.getContainer());

            hbox.getChildren().add(dayView.getContainer());

            date = date.plusDays(1);
        }

        this.scrollPaneEvents = new ScrollPane();
        scrollPaneEvents.prefHeightProperty().bind(this.paneWeek.heightProperty());
        scrollPaneEvents.prefHeightProperty().bind(this.paneWeek.widthProperty());
        this.scrollPaneEvents.setContent(hbox);
        hbox.prefWidthProperty().bind(scrollPaneEvents.widthProperty());
        hbox.prefHeightProperty().bind(scrollPaneEvents.heightProperty());
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
