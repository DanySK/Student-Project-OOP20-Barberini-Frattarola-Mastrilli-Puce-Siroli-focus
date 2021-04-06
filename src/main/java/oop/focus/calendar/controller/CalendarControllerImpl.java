package oop.focus.calendar.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;
import oop.focus.calendar.view.CalendarSettingsView;
import oop.focus.calendar.view.CalendarSettingsViewImpl;


public class CalendarControllerImpl implements CalendarController {


    private final CalendarSettingsView settingsview;

    private final CalendarMonthView monthview;

    private final double optionwidth;
    private final double optionheight;

    public CalendarControllerImpl(final double monthwidth, final double monthheight, final double optionwidth, final double optionheight, final double daywidth, final double dayheight) {
        this.optionwidth = optionwidth;
        this.optionheight = optionheight;
        final CalendarSettingsController settingscontroller = new CalendarSettingsControllerImpl();
        final CalendarMonthController monthcontroller = new CalendarMonthControllerImpl(settingscontroller, daywidth, dayheight);
        this.monthview = new CalendarMonthViewImpl(monthwidth, monthheight, monthcontroller);
        this.settingsview = new CalendarSettingsViewImpl(settingscontroller, monthcontroller, this.monthview);
    }



    public final Button buildSettingsWindows() {
        final Button settings = new Button("IMPOSTAZIONI");

        final Stage settingsstage = new Stage();
        settingsstage.setScene(new Scene(settingsview.getSettings(), optionwidth, optionheight));

        settings.setOnAction((e) -> {
            settingsview.setWindow(settingsstage);
            settingsstage.show();
        });
        return settings;
    }

    public final EventHandler<ActionEvent> monthPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);
                    monthview.getMonthView().setBackground(new Background(
                            new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                    panelcolumn.getChildren().add(monthview.getMonthView());
                } else {
                    panelcolumn.getChildren().add(monthview.getMonthView());
                }
            }

        };
    }

    public final EventHandler<ActionEvent> weekPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);
                    panelcolumn.getChildren().add(monthview.getMonthView());
                } else {
                    panelcolumn.getChildren().add(monthview.getMonthView());
                }
            }

        };
    }


}
