package oop.focus.calendar.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;



public class CalendarControllerImpl implements CalendarController {

    //Classes
    private final CalendarSettingsController settingscontroller;
    private final CalendarMonthView monthview;

    //Variables
    private final double optionwidth;
    private final double optionheight;

    public CalendarControllerImpl(final double optionwidth, final double optionheight, final double daywidth, final double dayheight) {
        this.optionwidth = optionwidth;
        this.optionheight = optionheight;

        final CalendarMonthController monthcontroller = new CalendarMonthControllerImpl(daywidth, dayheight);
        this.monthview = new CalendarMonthViewImpl(monthcontroller);

        settingscontroller = new CalendarSettingsControllerImpl(monthcontroller, this.monthview);
    }



    public final Button buildSettingsWindows() {
        final Button settings = new Button("IMPOSTAZIONI");

        final Stage settingsstage = new Stage();
        settingsstage.setScene(new Scene(settingscontroller.getSettings(), optionwidth, optionheight));
        settingscontroller.setWindow(settingsstage);
        settings.setOnAction((e) -> {
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

                } else {

                }
            }

        };
    }

    public final EventHandler<ActionEvent> statisticsPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);

                } else {

                }
            }

        };
    }


}
