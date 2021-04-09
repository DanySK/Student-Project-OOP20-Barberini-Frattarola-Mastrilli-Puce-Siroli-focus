package oop.focus.homepage.view;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.controller.CalendarDayController;
import oop.focus.calendar.controller.CalendarDayControllerImpl;
import oop.focus.calendar.controller.CalendarMonthController;
import oop.focus.calendar.controller.CalendarMonthControllerImpl;
import oop.focus.calendar.controller.CalendarSettingsControllerImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;

public class HomePageBaseView implements Initializable, View {

        @FXML
        private Pane paneCalendarHomePage;

        @FXML
        private Button modifyButton;

        @FXML
        private ScrollPane scrollPane;

        @FXML
        private VBox vbox, calendarVBox;

        @FXML
        private HBox calendarHBox;

        private Parent root;
        private final HomePageController controller;

        public HomePageBaseView(final HomePageController controller) {
            this.controller = controller;
            final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/base.fxml"));
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

            final DayImpl day = new DayImpl(LocalDate.now());
            final CalendarDayController days = new CalendarDayControllerImpl(day, 20, 20);

            days.buildDay();
            days.getScroller().prefWidthProperty().bind(calendarVBox.widthProperty());
            days.getScroller().prefHeightProperty().bind(calendarVBox.heightProperty());

            calendarVBox.getChildren().add(days.getScroller());

            final HotKeyGenerate generate = new HotKeyGenerate(this.controller);

            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10));
            final ObservableList<HotKey> hotKeyList = this.controller.getHotKey();
            hotKeyList.forEach(hotkey -> this.vbox.getChildren().add(generate.createButton(hotkey)));
            scrollPane.setContent(vbox);
       }

        private void setCalendar() {
            final CalendarMonthController monthController = new CalendarMonthControllerImpl(calendarHBox.getWidth(), calendarHBox.getHeight());

            monthController.getMonthView().prefWidthProperty().bind(calendarHBox.widthProperty());
            monthController.getMonthView().prefHeightProperty().bind(calendarHBox.heightProperty());
            monthController.disableButton(true);

            this.calendarHBox.getChildren().add(monthController.getMonthView());
        }

        public final Parent getRoot() {
            return this.root;
        }

        public final void modifyClicked(final ActionEvent event) throws IOException {
            final HotKeyMenuView menu = new HotKeyMenuView(this.controller);
            this.paneCalendarHomePage.getChildren().clear();
            this.paneCalendarHomePage.getChildren().add(menu.getRoot());
        }
}
