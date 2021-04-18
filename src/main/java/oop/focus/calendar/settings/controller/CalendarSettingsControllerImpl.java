package oop.focus.calendar.settings.controller;


import javafx.stage.Stage;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.settings.view.CalendarSettingsView;
import oop.focus.calendar.settings.view.CalendarSettingsViewImpl;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.common.View;

/**
 * Implementation of {@link CalendarSettingsController}.
 */
public class CalendarSettingsControllerImpl implements CalendarSettingsController {

    //Classes
    private final CalendarMonthController monthController;
    private final WeekController weekController;
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
     * @param weekController : controller of the week
     */
    public CalendarSettingsControllerImpl(final CalendarMonthController monthController, final WeekController weekController) {
        this.weekController = weekController;
        this.monthController = monthController;
        this.settingsView = new CalendarSettingsViewImpl(this);
        this.format = Format.NORMAL;
        this.spacing = SPACING;
    }

    /**
     * {@inheritDoc}
     */
    public final void setFormat(final Format format) {
        this.format = format;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }

    /**
     * {@inheritDoc}
     */
    public final double getSpacing() {
        return this.spacing;
    }

    /**
     * {@inheritDoc}
     */
    public final void updateView() {
        this.monthController.setFormat(this.format);
        this.monthController.setSpacing(this.spacing);
        this.monthController.updateView();

        this.weekController.getView().setDayProperty(this.format, this.spacing);

    }

    /**
     * {@inheritDoc}
     */
    public final void setWindow(final Stage stage) {
        this.settingsView.setWindow(stage);
    }

    /**
     * {@inheritDoc}
     */
    public final View getView() {
        return this.settingsView;
    }

}
