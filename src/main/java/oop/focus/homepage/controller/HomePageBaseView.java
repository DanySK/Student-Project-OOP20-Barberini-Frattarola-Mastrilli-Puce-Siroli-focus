package oop.focus.homepage.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class HomePageBaseView implements Initializable {

    @FXML
    private Pane paneCalendarHomePage;

    @FXML
    private Button modifyButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox, calendarVBox;


    private List<HotKey> hotKeyList;

    private String eventName;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        //final CalendarMonthView calendar = new CalendarMonthView(20, 20);
        //final VBox calendarVBox = calendar.buildMonthView();
        //this.paneCalendarHomePage.getChildren().add(calendarVBox);
        DayImpl day = new DayImpl(LocalDate.now());
        final CalendarDaysView days = new CalendarDaysViewImpl(day, 20, 20, 10);
        days.buildDay();
        ScrollPane scroller = days.getScroller();
        this.paneCalendarHomePage.getChildren().add(scroller);
        this.hotKeyList = new ArrayList<>();
        this.hotKeyList.add(new HotKeyImpl("Shopping", HotKeyType.EVENT));
        this.hotKeyList.add(new HotKeyImpl("Bere", HotKeyType.COUNTER));
        this.hotKeyList.add(new HotKeyImpl("Discoteca", HotKeyType.EVENT));
        this.hotKeyList.add(new HotKeyImpl("Addominali", HotKeyType.COUNTER));
        this.hotKeyList.add(new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY));

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        scrollPane.setContent(vbox);

        for (int i = 0; i < this.hotKeyList.size(); i++) {
            this.fullScrollPane(this.hotKeyList.get(i));
        }
        scrollPane.setContent(vbox);
    }


    private void fullScrollPane(final HotKey hotKey) {
        switch (hotKey.getType()) {
        case EVENT :
            final Button button = new Button(hotKey.getName());
            button.setOnAction(event -> {
                try {
                    this.eventName = hotKey.getName();
                    this.changeScene(event);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            vbox.getChildren().add(button);
            break;
        case COUNTER :
            final CounterHotKeyView counter = new CounterHotKeyView(hotKey.getName());
            counter.getButton().setOnAction(event -> {
                int count = Integer.valueOf(counter.getLabelValue());
                count++;
                counter.setLabelText(String.valueOf(count));
            });
            vbox.getChildren().add(counter);
            break;
        case ACTIVITY:
            final CheckBox activity = new CheckBox(hotKey.getName());
            activity.setOnAction(event -> {
                if (!activity.isSelected()) {
                    activity.setSelected(true);
                }
            });
            vbox.getChildren().add(activity);
            break;
        default :
            break;
        }
    }

    private void changeScene(final ActionEvent event) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/homepage/newEvent.fxml"));
        final Parent root = loader.load();

        final NewEventView controller2 = loader.getController();
        controller2.setText(this.eventName);

        final Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public final String getEventName() {
        return this.eventName;
    }

    @FXML
    public final void modifyClicked(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("/layouts/homepage/choiceMenu.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}
