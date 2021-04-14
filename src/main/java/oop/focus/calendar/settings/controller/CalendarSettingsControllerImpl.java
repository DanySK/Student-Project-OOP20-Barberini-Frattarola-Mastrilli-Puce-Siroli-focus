package oop.focus.calendar.settings.controller;


import javafx.stage.Stage;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.settings.view.CalendarSettingsView;
import oop.focus.calendar.settings.view.CalendarSettingsViewImpl;
import oop.focus.common.View;


public class CalendarSettingsControllerImpl implements CalendarSettingsController {

    //Classes
    private final CalendarMonthController monthController;
    private final CalendarSettingsView settingsView;

    //Variables
    private Format format;
    private double spacing;

    //Costants
    private static final double SPACING = 50; 
    private static final double MIN_SPACING = 30;

    /**
     * Used for Initialize the settings controller.
     * @param monthController : controller of the month
     */
    public CalendarSettingsControllerImpl(final CalendarMonthController monthController) {
        this.monthController = monthController;
        this.settingsView = new CalendarSettingsViewImpl(this);
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
                if (d < MIN_SPACING) {
                    this.settingsView.windowsError("minimo valore concesso e' 30");
                    return false;
                }  else {
                    this.setSpacing(d);
                    return true;
                } 
            } catch (NumberFormatException nfe) {
                this.settingsView.windowsError("inserire dei numeri");
                return false;
            }
        }

    }


    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }


    public final double getSpacing() {
        return this.spacing;
    }


    public final void updateView() {
        this.monthController.setFormat(this.format);
        this.monthController.setSpacing(this.spacing);
        this.monthController.updateView();
    }

    public final void setWindow(final Stage stage) {
        this.settingsView.setWindow(stage);
    }

    public final View getView() {
        return this.settingsView;
    }

}
