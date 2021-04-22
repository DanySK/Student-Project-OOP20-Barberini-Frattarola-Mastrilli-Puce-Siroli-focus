package oop.focus.calendar.day.view;


import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.event.model.Event;



/**
 * Implementation of {@link VBoxManager}.
 */
public class EventViewImpl implements VBoxManager {

    //Classes
    private final HoursView hours;
    private final Day day;

    //View
    private VBox myVBox;

    //Variables
    private double spacing;
    private double insertEventsDuration;
    private double height;

    //List
    private final List<Event> events;

    //Constants
    private static final double MINUTES_IN_HOURS = 60;


    /**
     * Used for Initialize Event view.
     * @param hours : the box of the {@link HoursView}
     * @param day : the {@link Day}
     */
    public EventViewImpl(final HoursView hours, final Day day) {
        this.events = new ArrayList<>(day.getEvents());
        this.hours = hours;
        this.day = day;
        this.height = 0;
    }

    /**
     * Used for check if we are setting the right spacing according to the format.
     */
    private void checkSpacing() {
        this.spacing = this.hours.getSpacing();
        if (this.hours.getFormat() == Format.EXTENDED.getNumber()) {
            this.spacing = this.spacing * 2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public final double getY(final int i) {
        final double spaceForMinute = this.spacing / MINUTES_IN_HOURS;
        final double minutesTotalSpace = spaceForMinute * this.events.get(i).getStartHour().getMinuteOfHour();
        if (this.events.get(i).getStartDate().getDayOfMonth() != this.day.getNumber()) {
            if (this.events.get(i).getEndHour().getHourOfDay() != 0) {
                return this.events.get(0).getStartHour().getHourOfDay();
            } else {
                return 0;
            }
        } else {
            return this.hours.getY(this.events.get(i).getStartHour().getHourOfDay()) + minutesTotalSpace;
        }
    }

    /**
     * {@inheritDoc}
     */
    public final VBox getVBox() {
        if (this.myVBox == null) {
            this.buildVBox();
        }
        return this.myVBox;
    }

    /**
     * Used for build the events panel.
     * @param vbox is the box where the events will be
     * @param i index of the events
     */
    private void buildPanel(final VBox vbox, final int i) {
        final VBox panel = new VBox();
        final Label name = new Label(" " + this.events.get(i).getName());
        name.setWrapText(true);

        panel.setBackground(new Background(
                new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        panel.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        panel.getChildren().add(name);

        if (i != 0) {
            panel.setTranslateY(this.getY(i) - this.insertEventsDuration);
        } else {
            panel.setTranslateY(this.getY(i));
        }


        final double endHour;
        final double startHour;

        if (this.events.get(i).getStartDate().getDayOfMonth() != this.day.getNumber()) {
            startHour = 0;
            endHour = this.events.get(i).getEndHour().getHourOfDay();
        } else if (this.events.get(i).getEndDate().getDayOfMonth() != this.day.getNumber()) {
            startHour = this.events.get(i).getStartHour().getHourOfDay();
            endHour = Format.NORMAL.getNumber();
        } else {
            startHour = this.events.get(i).getStartHour().getHourOfDay();
            endHour = this.events.get(i).getEndHour().getHourOfDay();
        }

        final double durationEventInHours = endHour - startHour;


        final double endMinutes;
        final double startMinutes;

        if (this.events.get(i).getStartDate().getDayOfMonth() != this.day.getNumber()) {
            startMinutes = 0;
            endMinutes = (double) this.events.get(i).getEndHour().getMinuteOfHour();
        } else if (this.events.get(i).getEndDate().getDayOfMonth() != this.day.getNumber()) {
            startMinutes = 0;
            endMinutes = 0;
        } else {
            startMinutes = (double) this.events.get(i).getStartHour().getMinuteOfHour();
            endMinutes = (double) this.events.get(i).getEndHour().getMinuteOfHour();
        }

        final double durationEventInMinutes = endMinutes - startMinutes;

        this.height += (this.spacing / MINUTES_IN_HOURS) * (durationEventInMinutes + durationEventInHours * MINUTES_IN_HOURS);
        panel.setPrefHeight(this.height);
        this.insertEventsDuration += this.height;
        this.height = 0;

        vbox.getChildren().add(panel);
    }

    /**
     * {@inheritDoc}
     */
    public final void buildVBox() {
        this.checkSpacing();
        final VBox vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.valueOf("ffcccc"), CornerRadii.EMPTY, Insets.EMPTY)));
        for (int i = 0; i < this.events.size(); i++) {
            this.buildPanel(vbox, i);
        }
        this.myVBox = vbox;
    }



}
