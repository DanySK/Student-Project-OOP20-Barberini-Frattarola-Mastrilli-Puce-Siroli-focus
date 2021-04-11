package oop.focus.homepage.view;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.joda.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.controller.CalendarDayControllerImpl;
import oop.focus.calendar.controller.CalendarMonthController;
import oop.focus.calendar.controller.CalendarMonthControllerImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;
import oop.focus.homepage.controller.FXMLPaths;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;

public class HomePageBaseViewImpl implements HomePageBaseView {

        @FXML
        private Pane paneCalendarHomePage;

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

        @FXML
        private VBox vBoxCalendar;

        private Node root;
        private final HomePageController controller;

        public HomePageBaseViewImpl(final HomePageController controller) {
            this.controller = controller;
            final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.HOMEPAGEBASE.getPath()));
            loader.setController(this);

            try {
                this.root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public final void initialize(final URL location, final ResourceBundle resources) {
            this.controller.refreshDailyEvents();

            this.modifyButton.setOnAction(event -> {
                try {
                    this.modifyClicked(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            this.setCalendar();

            final DayImpl day = new DayImpl(LocalDate.now(), this.controller.getDsi());
            final CalendarDayControllerImpl days = new CalendarDayControllerImpl(day, vBoxCalendar.getWidth(), vBoxCalendar.getHeight());

            days.buildDay();

            final CalendarDaysView daysView = new CalendarDaysViewImpl(days);

            daysView.getContainer().prefWidthProperty().bind(vBoxCalendar.widthProperty());
            daysView.getContainer().prefHeightProperty().bind(vBoxCalendar.heightProperty());

            vBoxCalendar.getChildren().add(daysView.getContainer());

            vBoxCalendar.prefHeightProperty().bind(scroller.heightProperty());
            vBoxCalendar.prefWidthProperty().bind(scroller.widthProperty());

            scroller.setContent(vBoxCalendar);

            final HotKeyGenerate generate = new HotKeyGenerate(this.controller);

            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10));
            final ObservableList<HotKey> hotKeyList = this.controller.getHotKey();
            hotKeyList.forEach(hotkey -> this.vbox.getChildren().add(generate.createButton(hotkey)));
            scrollPane.setContent(vbox);
       }

        private void setCalendar() {
            final CalendarMonthController monthController = new CalendarMonthControllerImpl(this.controller.getDsi(), calendarHBox.getWidth(), calendarHBox.getHeight());

            final CalendarMonthView month = new CalendarMonthViewImpl(monthController, calendarHBox.getWidth(), calendarHBox.getHeight());

            monthController.disableButton(true);

            month.getMonthView().prefWidthProperty().bind(calendarHBox.widthProperty());
            month.getMonthView().prefHeightProperty().bind(calendarHBox.heightProperty());

            this.calendarHBox.getChildren().add(month.getMonthView());
        }

        public final Node getRoot() {
            return this.root;
        }

        public final void modifyClicked(final ActionEvent event) throws IOException {
            final HotKeyMenuView menu = new HotKeyMenuViewImpl(this.controller);
            this.paneCalendarHomePage.getChildren().clear();
            this.paneCalendarHomePage.getChildren().add(menu.getRoot());
        }
}
