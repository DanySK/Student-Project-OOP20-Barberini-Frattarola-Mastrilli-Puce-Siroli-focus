package oop.focus.calendar.controller;

import oop.focus.calendar.view.HoursViewImpl.Format;

public class CalendarOptionsControllerImpl implements CalendarOptionsController {

    private static final double SPACING = 50; 
    private Format format;
    private double spacing;

    public CalendarOptionsControllerImpl() {
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
     * 
     * @param spacing
     */
    public void setSpacing(final String spacing) {
        if (spacing.isBlank()) {
            this.spacing = SPACING;
        } else {
            try {
                final double d = Double.parseDouble(spacing);
                this.spacing = d;
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

    }

    /**
     * @return format
     */
    public double getSpacing() {
        return this.spacing;
    }

}
