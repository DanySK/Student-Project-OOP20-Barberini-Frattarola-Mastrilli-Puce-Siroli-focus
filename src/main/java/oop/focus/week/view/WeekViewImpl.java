package oop.focus.week.view;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import oop.focus.calendar.controller.CalendarDayController;
import oop.focus.calendar.controller.CalendarDayControllerImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.db.DataSourceImpl;
import oop.focus.week.controller.FXMLPaths;
import oop.focus.week.controller.WeekController;

public class WeekViewImpl implements WeekView {

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
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final LocalDate today = LocalDate.now();
        this.startWeek = today.minusDays(today.getDayOfWeek() - 1);
        this.setWeekDays();
        this.setButtonAction();
    }

    public final void setButtonAction() {
        this.newEvent.setOnAction(event -> this.addNewEvent(event));
        this.nextWeek.setOnAction(event -> this.nextWeek(event));
        this.lastWeek.setOnAction(event -> this.lastWeek(event));
    }

    @FXML
    public final void addNewEvent(final ActionEvent event) {
        final AddNewEventWeekView newEvent = new AddNewEventWeekView(this.controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newEvent.getRoot()));
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

    @FXML
    public final void nextWeek(final ActionEvent event) {
        this.startWeek = this.startWeek.plusDays(Constants.DAYS_PER_WEEK);
        this.setWeekDays();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    private class Constants {
        private static final int DAYS_PER_WEEK = 7;
        private static final int SPACING = 50;
    }
}
