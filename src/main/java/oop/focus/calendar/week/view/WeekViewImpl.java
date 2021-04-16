package oop.focus.calendar.week.view;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import oop.focus.statistics.view.ViewFactoryImpl;
import org.joda.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oop.focus.calendar.day.controller.CalendarDayController;
import oop.focus.calendar.day.controller.CalendarDayControllerImpl;
import oop.focus.calendar.day.view.CalendarDaysView;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.week.controller.FXMLPaths;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.db.DataSourceImpl;

public class WeekViewImpl implements WeekView {

    @FXML
    private VBox weekDaysPane;

    @FXML
    private Button lastWeek;

    @FXML
    private Label thisWeek;

    @FXML
    private Button nextWeek;

    @FXML
    private ScrollPane weekDaysScroller;

    private Node root;
    private LocalDate startWeek;
    private final WeekController controller;

    public WeekViewImpl(final WeekController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.WEEK.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.root = new ViewFactoryImpl().createVerticalAutoResizingWithNodes(List.copyOf(this.weekDaysPane.getChildren())).getRoot();
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {

        final LocalDate today = LocalDate.now();
        this.startWeek = today.minusDays(today.getDayOfWeek() - 1);
        this.setWeekDays();
        this.setButtonAction();

    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void lastWeek(final ActionEvent event) {
        this.startWeek = this.startWeek.minusDays(Constants.DAYS_PER_WEEK);
        this.setWeekDays();
    }

    @FXML
    public final void nextWeek(final ActionEvent event) {
        this.startWeek = this.startWeek.plusDays(Constants.DAYS_PER_WEEK);
        this.setWeekDays();
    }

    public final void setButtonAction() {
        this.nextWeek.setOnAction(event -> this.nextWeek(event));
        this.lastWeek.setOnAction(event -> this.lastWeek(event));
    }

    private void setLabelText() {
        if (this.startWeek.isEqual(LocalDate.now().minusDays(LocalDate.now().getDayOfWeek() - 1))) {
            this.thisWeek.setText("SETTIMANA CORRENTE");
        } else {
            this.thisWeek.setText(startWeek.toString() + " - " + startWeek.plusDays(Constants.FIND_FINAL));
        }
    }

    public final void setWeekDays() {
        this.setLabelText();

        LocalDate date = this.startWeek;
        final HBox hbox = new HBox();

        for (int i = 0; i < Constants.DAYS_PER_WEEK; i++) {
            final CalendarDayController day = new CalendarDayControllerImpl(new DayImpl(date, (DataSourceImpl) this.controller.getDsi()), 200, 500);

            day.setFormat(Format.NORMAL);
            day.setSpacing(Constants.SPACING);
            day.buildDay();
            final CalendarDaysView daysView = (CalendarDaysView) day.getView();
            daysView.getContainer().prefWidthProperty().bind(hbox.widthProperty().divide(Constants.DAYS_PER_WEEK));
            hbox.getChildren().add(daysView.getContainer());
            date = date.plusDays(1);
        }
        hbox.prefWidthProperty().bind(weekDaysScroller.widthProperty());

        this.weekDaysScroller.setContent(hbox);
    }

    private class Constants {
        private static final int FIND_FINAL = 6;
        private static final int DAYS_PER_WEEK = 7;
        private static final int SPACING = 100;
    }
}
