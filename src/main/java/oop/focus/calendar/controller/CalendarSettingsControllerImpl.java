package oop.focus.calendar.controller;

import oop.focus.calendar.view.HoursViewImpl.Format;

public class CalendarSettingsControllerImpl implements CalendarSettingsController {

    //Variables
    private Format format;
    private double spacing;

    //Costants
    private static final double SPACING = 50; 
    private static final double MINSPACING = 30;

    public CalendarSettingsControllerImpl() {
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
                    System.out.println("MINIMO VALORE CONCESSO E' 30");
                    return false;
                }  else {
                    this.setSpacing(d);
                    return true;
                } 
            } catch (NumberFormatException nfe) {
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

}
