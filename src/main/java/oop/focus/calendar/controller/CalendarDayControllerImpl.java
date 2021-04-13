package oop.focus.calendar.controller;


import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.calendar.view.EventViewImpl;
import oop.focus.calendar.view.HoursView;
import oop.focus.calendar.view.HoursViewImpl;
import oop.focus.common.View;


public class CalendarDayControllerImpl implements CalendarDayController {

    //Classes
    private final HoursView hoursBox;
    private final EventViewImpl eventBox;
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

        this.dailyEvents = "Attività giornaliere:" + SEP;
        this.width = width;
        this.height = height;
        setSpacing(SPACING);
        setFormat(Format.NORMAL);
    }


    public final void buildDay() {
        this.calendarDayView.buildDay();
    }

    public final HoursView getHoursBox() {
        return this.hoursBox;
    }

    public final EventViewImpl getEventBox() {
        return this.eventBox;
    }

    public final Day getDay() {
        return this.day;
    }

    public final double getWidth() {
        return this.width;
    }

    public final double getHeight() {
        return this.height;
    }

    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }

    public final double getSpacing() {
        return this.spacing;
    }

    public final void setFormat(final Format format) {
        this.format = format;
    }

    public final Format getFormat() {
        return this.format;
    }

    public final String writeDailyEvent() {
        this.day.getDailyEvents().forEach(e -> {
            this.dailyEvents += e.getName() + SEP;
        });
        return this.dailyEvents;
    }


    public final View getView() {
        return this.calendarDayView;
    }
}
