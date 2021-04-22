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
import javafx.scene.layout.Pane;
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
    private static final double HALF_HOUR = 30;


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
        this.spacing = hours.getSpacing();
        if (hours.getFormat() == Format.EXTENDED.getNumber()) {
            this.spacing = this.spacing * 2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public final double getY(final int i) {
        final int hour;
        final double spaceForMinute = this.spacing / MINUTES_IN_HOURS;
        final double minutesTotalSpace = spaceForMinute * this.events.get(i).getStartHour().getMinuteOfHour();
        if (this.events.get(i).getEndDate().getDayOfMonth() == day.getNumber() && this.events.get(i).getStartDate().getDayOfMonth() != day.getNumber()) {
            return 0;
        } else {
            hour = this.events.get(i).getStartHour().getHourOfDay();
            return hours.getY(hour) + minutesTotalSpace;
        }
    }

    /**
     * {@inheritDoc}
     */
    public final VBox getVBox() {
        if (this.myVBox == null) {
            buildVBox();
        }
        return this.myVBox;
    }

    /**
     * Used for build the events panel.
     * @param vbox is the box where the events will be
     * @param i index of the events
     */
    private void buildPanel(final VBox vbox, final int i) {
        final Pane panel = new Pane();
        final Label name = new Label(" " + this.events.get(i).getName());

        panel.setBackground(new Background(
                new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        panel.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        panel.getChildren().add(name);

        if (i != 0) {
            panel.setTranslateY(this.getY(i) - insertEventsDuration);
        } else {
            if (this.getY(i) == 0 && this.hours.getFormat() == Format.EXTENDED.getNumber()) {
                panel.setTranslateY(this.getY(i));
                this.height -= HALF_HOUR;
            } else {
                panel.setTranslateY(this.getY(i));
            }
        }


        double endHour;
        double startHour;

        if (this.events.get(i).getEndDate().getDayOfMonth() == day.getNumber() && this.events.get(i).getStartDate().getDayOfMonth() != day.getNumber()) {
            endHour = this.events.get(i).getEndHour().getHourOfDay();
            startHour = 0;
        } else {
            startHour = this.events.get(i).getStartHour().getHourOfDay();
            if (this.events.get(i).getEndHour().getHourOfDay() == 0) {
                endHour = Format.NORMAL.getNumber();
            } else {
                if (!this.events.get(i).getEndDate().dayOfMonth().equals(this.events.get(i).getStartDate().dayOfMonth())) {
                    endHour = Format.NORMAL.getNumber();
                } else {
                    endHour = this.events.get(i).getEndHour().getHourOfDay();
                }
            }
        }


        final double durationEventInHours = endHour - startHour;
        double durationEventInMinutes = (double) this.events.get(i).getEndHour().getMinuteOfHour() - (double) this.events.get(i).getStartHour().getMinuteOfHour();
        if (this.getY(i) == 0) {
            if (this.events.get(i).getEndHour().getHourOfDay() == 0) {
            durationEventInMinutes = HALF_HOUR;
            } else {
            durationEventInMinutes = HALF_HOUR + (double) this.events.get(i).getEndHour().getMinuteOfHour();
            }
        }
        this.height += (this.spacing / MINUTES_IN_HOURS) * (durationEventInMinutes + durationEventInHours * MINUTES_IN_HOURS);
        panel.setPrefHeight(this.height);
        insertEventsDuration += this.height;
        this.height = 0;

        vbox.getChildren().add(panel);
    }

    /**
     * {@inheritDoc}
     */
    public final void buildVBox() {
        checkSpacing();
        final VBox vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.valueOf("ffcccc"), CornerRadii.EMPTY, Insets.EMPTY)));
        for (int i = 0; i < this.events.size(); i++) {
            buildPanel(vbox, i);
        }
        this.myVBox = vbox;
    }



}
