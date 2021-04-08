package oop.focus.calendar.controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.calendar.view.EventViewImpl;
import oop.focus.calendar.view.HoursViewImpl;


public class CalendarDayControllerImpl implements CalendarDayController {

    //Classes
    private final HoursViewImpl hoursbox;
    private final EventViewImpl eventbox;
    private final DayImpl day;

    //View
    private final CalendarDaysView dayviewlogic;
    private ScrollPane scroller;
    private VBox dayview;

    //Variables
    private final double width;
    private final double height;
    private double spacing;
    private Format format;
    private String dailyevents;

    //Costants
    private static final double SPACING = 50; 

    /**
     * 
     * @param day    date of the day that we want build
     * @param width  max width of the day view.
     * @param height  max height of the day view.
     */
    public CalendarDayControllerImpl(final DayImpl day, final double width, final double height) {

        dayviewlogic = new CalendarDaysViewImpl(this);

        this.scroller = new ScrollPane();
        this.dayview = new VBox();
        this.dailyevents = "Attività giornaliere:\n";

        this.day = day;
        hoursbox = new HoursViewImpl();
        eventbox = new EventViewImpl(hoursbox, day);

        this.width = width;
        this.height = height;
        setSpacing(SPACING);
        setFormat(Format.NORMAL);
    }


    public final void buildDay() {
        this.dayviewlogic.buildDay();
    }

    public final HoursViewImpl getHoursBox() {
        return this.hoursbox;
    }

    public final EventViewImpl getEventBox() {
        return this.eventbox;
    }

    public final DayImpl getDay() {
        return this.day;
    }


    public final void setScroller(final VBox container) {
        this.scroller = new ScrollPane(container);
        this.scroller.setFitToWidth(true);
    }


    public final ScrollPane getScroller() {
        return this.scroller;
    }


    public final void setContainer(final VBox container) {
        this.dayview = new VBox(container);
    }


    public final VBox getContainer() {
        return this.dayview;
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

    public final String getDailyEvent() {
        return this.dailyevents;
    }

    public final void setDailyEvent() {
        this.day.getDailyEvents().forEach(e -> {
            dailyevents += e.getName() + "\n";
        });
    }
}
