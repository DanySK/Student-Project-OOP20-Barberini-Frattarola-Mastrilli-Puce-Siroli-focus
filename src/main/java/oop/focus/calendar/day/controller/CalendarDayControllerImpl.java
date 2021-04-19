package oop.focus.calendar.day.controller;


import oop.focus.calendar.day.view.CalendarDaysView;
import oop.focus.calendar.day.view.CalendarDaysViewImpl;
import oop.focus.calendar.day.view.EventViewImpl;
import oop.focus.calendar.day.view.HoursView;
import oop.focus.calendar.day.view.HoursViewImpl;
import oop.focus.calendar.day.view.VBoxManager;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.common.View;

/**
 * Implementation of {@link CalendarDayController}.
 */
public class CalendarDayControllerImpl implements CalendarDayController {

    //Classes
    private final HoursView hoursBox;
    private final VBoxManager eventBox;
    private final Day day;

    //View
    private final CalendarDaysView calendarDayView;

    //Variables
    private final double width;
    private final double height;
    private double spacing;
    private Format format;
    private String dailyEvents;

    //Costants
    private static final double SPACING = 50; 
    private static final String SEP = System.lineSeparator();

    /**
     * Used for Initialize the day controller.
     * @param day    date of the day that we want build
     * @param width  max width of the day view.
     * @param height  max height of the day view.
     */
    public CalendarDayControllerImpl(final Day day, final double width, final double height) {
        this.calendarDayView = new CalendarDaysViewImpl(this);


        this.day = day;
        this.hoursBox = new HoursViewImpl();
        this.eventBox = new EventViewImpl(hoursBox, day);

        this.dailyEvents = " Attivita' giornaliere: " + SEP;
        this.width = width;
        this.height = height;
        setSpacing(SPACING);
        setFormat(Format.NORMAL);
    }

    /**
     * {@inheritDoc}
     */
    public final void buildDay() {
        this.calendarDayView.buildDay();
    }

    /**
     * {@inheritDoc}
     */
    public final HoursView getHoursBox() {
        return this.hoursBox;
    }

    /**
     * {@inheritDoc}
     */
    public final VBoxManager getEventBox() {
        return this.eventBox;
    }

    /**
     * {@inheritDoc}
     */
    public final Day getDay() {
        return this.day;
    }

    /**
     * {@inheritDoc}
     */
    public final double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    public final double getHeight() {
        return this.height;
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
    public final void setFormat(final Format format) {
        this.format = format;
    }

    /**
     * {@inheritDoc}
     */
    public final Format getFormat() {
        return this.format;
    }

    /**
     * {@inheritDoc}
     */
    public final String writeDailyEvent() {
        this.day.getDailyEvents().forEach(e -> this.dailyEvents += e.getName() + SEP);
        return this.dailyEvents;
    }

    /**
     * {@inheritDoc}
     */
    public final View getView() {
        return this.calendarDayView;
    }
}
