package oop.focus.calendar.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;
import oop.focus.calendar.view.CalendarView;
import oop.focus.calendar.view.CalendarViewImpl;



public class CalendarControllerImpl implements CalendarController {

    //Classes
    private final CalendarSettingsController settingscontroller;
    private final CalendarMonthController monthcontroller;

    //View
    private HBox calendarpage;

    //Variables
    private final double optionwidth;
    private final double optionheight;

    /**
     * Used for initialize the calendar controller.
     * @param optionwidth
     * @param optionheight
     * @param daywidth
     * @param dayheight
     */
    public CalendarControllerImpl(final double optionwidth, final double optionheight, final double daywidth, final double dayheight) {
        this.optionwidth = optionwidth;
        this.optionheight = optionheight;
        this.calendarpage = new HBox();

        this.monthcontroller = new CalendarMonthControllerImpl(daywidth, dayheight);
        final CalendarMonthView monthview = new CalendarMonthViewImpl(monthcontroller);

        settingscontroller = new CalendarSettingsControllerImpl(monthcontroller, monthview);

        final CalendarView calendarview = new CalendarViewImpl(this);
        calendarview.setCalendarBox();
    }

    public final void setCalendarPage(final HBox calendarpage) {
        this.calendarpage = calendarpage;
    }

    public final HBox getCalendarPage() {
        return this.calendarpage;
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

    public final Button buildAddEventButton() {
        final Button addevents = new Button("Aggiungi Evento");

        final Stage addeventsstage = new Stage();
        //addeventsstage.setScene(new Scene());
        addevents.setOnAction((e) -> {
            addeventsstage.show();
        });
        return addevents;
    }

    public final EventHandler<ActionEvent> monthPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);
                    panelcolumn.getChildren().add(monthcontroller.getMonthView());
                } else {
                    panelcolumn.getChildren().add(monthcontroller.getMonthView());
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
