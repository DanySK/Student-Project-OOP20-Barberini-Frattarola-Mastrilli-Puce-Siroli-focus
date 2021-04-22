package oop.focus.calendarhomepage.view;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.calendar.month.view.CalendarMonthView;
import oop.focus.calendar.month.view.CalendarMonthViewImpl;
import oop.focus.calendarhomepage.controller.HotKeyController;
import oop.focus.calendarhomepage.controller.HotKeyControllerImpl;
import oop.focus.statistics.view.ViewFactoryImpl;
import org.joda.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.day.controller.CalendarDayControllerImpl;
import oop.focus.calendar.day.view.CalendarDaysView;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendarhomepage.controller.FXMLPaths;
import oop.focus.calendarhomepage.controller.HomePageController;
import oop.focus.calendarhomepage.model.HotKey;

public class HomePageBaseViewImpl implements HomePageBaseView {

        @FXML
        private VBox paneCalendarHomePage;

        @FXML
        private Button modifyButton;

        @FXML
        private ScrollPane scrollPane;

        @FXML
        private VBox vbox;

        @FXML
        private VBox calendarHBox;

        @FXML
        private ScrollPane scroller;

        private Node root;
        private final HomePageController controller;

        public HomePageBaseViewImpl(final HomePageController controller) {
            this.controller = controller;
            final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.HOMEPAGEBASE.getPath()));
            loader.setController(this);

            try {
                this.root = loader.load();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            this.setRoot();
            this.scroller.setFitToWidth(true);
            this.scrollPane.setFitToWidth(true);
            this.scrollPane.vbarPolicyProperty().set(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        }

        private void setRoot() {
            this.root = new ViewFactoryImpl().createVerticalAutoResizingWithNodes(List.copyOf(this.paneCalendarHomePage.getChildren())).getRoot();
        }

        @Override
        public final void initialize(final URL location, final ResourceBundle resources) {
            this.controller.refreshDailyEvents();

            this.fullVBoxHotKey();

            this.modifyButton.setOnAction(this::modifyClicked);

            this.setCalendar();
            this.setDay();
       }

        public final Node getRoot() {
        return this.root;
    }

        public final void modifyClicked(final ActionEvent event) {
            final HotKeyController menuController = new HotKeyControllerImpl(this.controller);
            final Stage stage = new Stage();
            stage.setScene(new Scene((Parent) menuController.getView().getRoot()));
            stage.show();
        }

        private void setCalendar() {
            final CalendarMonthController monthController = new CalendarMonthControllerImpl(CalendarType.HOMEPAGE, this.controller.getDsi());
            monthController.setFontSize(Constants.FONT_SIZE);
            final CalendarMonthView month = new CalendarMonthViewImpl(CalendarType.HOMEPAGE, monthController);
            month.getMonthView().prefWidthProperty().bind(this.calendarHBox.widthProperty());
            month.getMonthView().prefHeightProperty().bind(this.calendarHBox.heightProperty());
            this.calendarHBox.getChildren().add(month.getMonthView());
        }

        @Override
        public final void setDay() {
            final VBox vBoxCalendar = new VBox();

            final DayImpl day = new DayImpl(LocalDate.now(), this.controller.getDsi());
            final CalendarDayControllerImpl days = new CalendarDayControllerImpl(day, vBoxCalendar.getWidth(), vBoxCalendar.getHeight());

            days.setFormat(Format.NORMAL);
            days.buildDay();
            days.setSpacing(100);

            final CalendarDaysView daysView = (CalendarDaysView) days.getView();
            daysView.getContainer().prefWidthProperty().bind(vBoxCalendar.widthProperty());

            vBoxCalendar.getChildren().add(daysView.getContainer());

            vBoxCalendar.prefWidthProperty().bind(this.scroller.widthProperty());
            this.scroller.setContent(vBoxCalendar);
        }

        @Override
        public final void fullVBoxHotKey() {
            this.vbox.getChildren().clear();
            final HotKeyGenerate generate = new HotKeyGenerate(this.controller);

            this.vbox.setSpacing(Constants.SPACING_HOT_KEY);
            this.vbox.setPadding(new Insets(Constants.SPACING_HOT_KEY));

            final ObservableList<HotKey> hotKeyList = this.controller.getHotKey();
            hotKeyList.forEach(hotkey -> this.vbox.getChildren().add(generate.createButton(hotkey)));

            this.scrollPane.setContent(this.vbox);
        }

        private static class Constants {
            private static final int FONT_SIZE = 12;
            private static final int SPACING_HOT_KEY = 10;
        }

}
