package oop.focus.calendar.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarSettingsView;
import oop.focus.calendar.view.CalendarSettingsViewImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;

public class CalendarSettingsControllerImpl implements CalendarSettingsController {

    //Classes
    private final CalendarMonthController monthcontroller;
    private final CalendarMonthView monthview;

    //View
    private Stage settingswindows;
    private final VBox settingsbox;

    //Variables
    private Format format;
    private double spacing;

    //Costants
    private static final double SPACING = 50; 
    private static final double MINSPACING = 30;
    private static final double ERRORWIDTH = 300;
    private static final double ERRORHEIGHT = 100;

    public CalendarSettingsControllerImpl(final CalendarMonthController monthcontroller, final CalendarMonthView monthview) {
        this.monthcontroller = monthcontroller;
        this.monthview = monthview;

        final CalendarSettingsView settingsview = new CalendarSettingsViewImpl(this);
        this.settingsbox = settingsview.getSettingsBox();
        this.format = Format.NORMAL;
        this.spacing = SPACING;
    }


    public final void setFormat(final Format format) {
        this.format = format;
    }


    public final Format getFormat() {
        return this.format;
    }


    public final boolean checkSpacing(final String spacing) {
        if (spacing.isBlank()) {
            this.setSpacing(SPACING);
            return true;
        } else {
            try {
                final double d = Double.parseDouble(spacing);
                if (d < MINSPACING) {
                    windowsError("ATTENZIONE:\nminimo valore concesso è 30");
                    return false;
                }  else {
                    this.setSpacing(d);
                    return true;
                } 
            } catch (NumberFormatException nfe) {
                windowsError("    ATTENZIONE:\ninserire dei numeri");
                return false;
            }
        }

    }

    private void windowsError(final String string) {
        final Stage windowserror = new Stage();
        final Label error = new Label(string);
        error.setAlignment(Pos.CENTER);
        windowserror.setScene(new Scene(error, ERRORWIDTH, ERRORHEIGHT));
        windowserror.show();
    }


    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }


    public final double getSpacing() {
        return this.spacing;
    }

    public final void setWindow(final Stage stage) {
        this.settingswindows = stage;
    }


    public final VBox getSettings() {
        return this.settingsbox;
    }

    public final EventHandler<ActionEvent> saveOnAction(final TextField spacing) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                 if (!checkSpacing(spacing.getText())) {
                     spacing.setText(String.valueOf(getSpacing()));
                 }
                 if (format == null) {
                     format = Format.NORMAL;
                 }
                 updateView();
                 settingswindows.close();
           }

        };
    }

    /**
     * Used for update all the view that are connected with the settings.
     */
    private void updateView() {

        monthcontroller.setFormat(this.format);
        monthcontroller.setSpacing(this.spacing);
        monthcontroller.updateView(monthview);
    }

}
