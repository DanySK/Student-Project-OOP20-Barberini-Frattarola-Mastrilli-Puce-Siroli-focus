package oop.focus.calendar.controller;

import oop.focus.calendar.view.HoursViewImpl.Format;

public class CalendarSettingsControllerImpl implements CalendarSettingsController {

    private static final double SPACING = 50; 
    private static final double MINSPACING = 30;
    private Format format;
    private double spacing;

    public CalendarSettingsControllerImpl() {
        this.format = Format.NORMAL;
        this.spacing = SPACING;
    }

    /**
     * 
     * @param format
     */
    public void setFormat(final Format format) {
        this.format = format;
    }

    /**
     * @return format
     */
    public Format getFormat() {
        return this.format;
    }

    /**
     * @param spacing
     * @return true
     */
    public boolean checkSpacing(final String spacing) {
        if (spacing.isBlank()) {
            this.setSpacing(SPACING);
            return true;
        } else {
            try {
                final double d = Double.parseDouble(spacing);
                this.setSpacing(d);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

    }

    /**
     * 
     * @param spacing
     */
    public void setSpacing(final double spacing) {
        if (spacing < MINSPACING) {
            System.out.println("MINIMO VALORE CONCESSO E' 30");
        }  else {
            this.spacing = spacing;
        } 
    }

    /**
     * @return format
     */
    public double getSpacing() {
        return this.spacing;
    }

}
