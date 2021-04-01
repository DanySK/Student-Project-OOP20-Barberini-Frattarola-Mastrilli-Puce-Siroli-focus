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

    private final HoursViewImpl hours;
    private VBox myvbox;
    private final List<Event> events = new ArrayList<>();
    private static final double MINUTESINHOUR = 60;
    private double spacing;
    private double inserteventsduration;

    public EventViewImpl(final HoursViewImpl hours, final DayImpl day) {
        this.events.addAll(day.getEvents());
        this.hours = hours;
    }


    private void checkSpacing() {
        this.spacing = hours.getSpacing();
        if (hours.getFormat() == HoursViewImpl.Format.EXTENDED.getNumber()) {
            this.spacing = this.spacing * 2;
        }
    }

    /**
     * @param i  Index of the events
     * @return position of the object in the VBox
     */
    public double getY(final int i) {
        final double spaceforminute = this.spacing / MINUTESINHOUR;
        final double minutestotalspace = spaceforminute * this.events.get(i).getStartHour().getMinuteOfHour();
        return hours.getY(this.events.get(i).getStartHour().getHourOfDay()) + minutestotalspace;
    }


    /**
     * @return get the events box.
     */
    public VBox getVBox() {
        if (this.myvbox == null) {
            buildVBox();
        }
        return this.myvbox;
    }

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

    /**
     * build the vbox.
     */
    public void buildVBox() {
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
