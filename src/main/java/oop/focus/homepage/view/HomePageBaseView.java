package oop.focus.homepage.view;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class HomePageBaseView implements Initializable, View {

        @FXML
        private Pane paneCalendarHomePage;

        @FXML
        private Button modifyButton;

        @FXML
        private ScrollPane scrollPane;

        @FXML
        private VBox vbox, calendarVBox;

        private Parent root;
        private final HomePageController controller;

        public HomePageBaseView(final HomePageController controller) {
            this.controller = controller;
            final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/calendarHomePage.fxml"));
            loader.setController(this);

            try {
                this.root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public final void initialize(final URL location, final ResourceBundle resources) {

            final DayImpl day = new DayImpl(LocalDate.now());
            final CalendarDaysView days = new CalendarDaysViewImpl(day, 20, 20);
            days.buildDay();
            days.getScroller().prefWidthProperty().bind(calendarVBox.widthProperty());
            days.getScroller().prefHeightProperty().bind(calendarVBox.heightProperty());

            calendarVBox.getChildren().add(days.getScroller());

            final HotKeyGenerate generate = new HotKeyGenerate(this.controller);

            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10));
            final ObservableList<HotKey> hotKeyList = FXCollections.observableArrayList();
            hotKeyList.addAll(new HotKeyImpl("Shopping", HotKeyType.EVENT), new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY), new HotKeyImpl("Bere", HotKeyType.COUNTER), new HotKeyImpl("Addominali", HotKeyType.COUNTER), new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY));
            hotKeyList.forEach(hotkey -> this.vbox.getChildren().add(generate.createButton(hotkey)));
            //this.controller.getHotKey().forEach(hotkey -> System.out.print(hotkey.getName()));
            scrollPane.setContent(vbox);
       }

        public final Parent getRoot() {
            return this.root;
        }

        public final void modifyClicked(final ActionEvent event) throws IOException {
            final HotKeyMenuView menu = new HotKeyMenuView(this.controller);
            this.paneCalendarHomePage.getChildren().clear();
            this.paneCalendarHomePage.getChildren().add(menu.getRoot());
        }

        public final void changeView(final Parent root, final ActionEvent event) {
            final NewEventView newEvent = new NewEventView(this.controller);
            this.paneCalendarHomePage.getChildren().clear();
            this.paneCalendarHomePage.getChildren().add(newEvent.getRoot());
        }



}
