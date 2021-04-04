package oop.focus.calendar.view;


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
import oop.focus.calendar.model.DayImpl;
import oop.focus.homepage.model.Event;




public class EventViewImpl implements VBoxManager {

    //Classes
    private final HoursViewImpl hours;

    //View
    private VBox myvbox;

    //Variables
    private double spacing;
    private double inserteventsduration;

    //List
    private final List<Event> events = new ArrayList<>();

    //Constants
    private static final double MINUTESINHOUR = 60;


    public EventViewImpl(final HoursViewImpl hours, final DayImpl day) {
        this.events.addAll(day.getEvents());
        this.hours = hours;
    }

    /**
     * Used for check if we are setting the right spacing according to the format.
     */
    private void checkSpacing() {
        this.spacing = hours.getSpacing();
        if (hours.getFormat() == HoursViewImpl.Format.EXTENDED.getNumber()) {
            this.spacing = this.spacing * 2;
        }
    }


    public final double getY(final int i) {
        final double spaceforminute = this.spacing / MINUTESINHOUR;
        final double minutestotalspace = spaceforminute * this.events.get(i).getStartHour().getMinuteOfHour();
        return hours.getY(this.events.get(i).getStartHour().getHourOfDay()) + minutestotalspace;
    }


    public final VBox getVBox() {
        if (this.myvbox == null) {
            buildVBox();
        }
        return this.myvbox;
    }

    /**
     * Used for build the events panel.
     * @param vbox is the box where the events will be
     * @param i index of the events
     */
    private void buildPanel(final VBox vbox, final int i) {
        final Pane panel = new Pane();
        final Label name = new Label(this.events.get(i).getName());

        panel.setBackground(new Background(
                new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        panel.setBorder(new Border(
                new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        panel.getChildren().add(name);

        if (i != 0) {
        panel.setTranslateY(this.getY(i) - inserteventsduration);
        } else {
        panel.setTranslateY(this.getY(i));
        }

        final double durationeventinhours = this.events.get(i).getEndHour().getHourOfDay() - this.events.get(i).getStartHour().getHourOfDay();
        final double durationeventinminutes = (double) this.events.get(i).getEndHour().getMinuteOfHour() - (double) this.events.get(i).getStartHour().getMinuteOfHour();
        panel.setPrefHeight((this.spacing / MINUTESINHOUR) * (durationeventinminutes + durationeventinhours * MINUTESINHOUR));
        inserteventsduration += (this.spacing / MINUTESINHOUR) * (durationeventinminutes + durationeventinhours * MINUTESINHOUR);

        vbox.getChildren().add(panel);
    }


    public final void buildVBox() {
        checkSpacing();
        final VBox vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        for (int i = 0; i < this.events.size(); i++) {
            buildPanel(vbox, i);
        }
        this.myvbox = vbox;
    }



}
