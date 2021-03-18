package oop.focus.calendar;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;

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
import oop.focus.finance.Repetition;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;



public class EventViewImpl implements EventView {

    private final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
    private final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 26, 11, 00), new LocalDateTime(2021, 9, 26, 11, 30), Repetition.ONCE);
    private final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 12, 00), new LocalDateTime(2021, 9, 26, 12, 30), Repetition.ONCE);
    private final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.ONCE);

    private final HoursViewImpl hours;
    private VBox myvbox;
    private final List<Event> events = new ArrayList<>();
    private double temp10;

    public EventViewImpl(final HoursViewImpl hours) {
        subito();
        this.hours = hours;
    }


    private void subito() {
        this.events.add(first);
        this.events.add(second);
        this.events.add(third);
        this.events.add(four);
    }

    /**
     * @param i qualxcosa
     * @return qualcosa
     */
    public double getY(final int i) {
        final double temp3 = hours.getSpacing() / 60;
        final double temp4 = temp3 * this.events.get(i).getStartHour().getMinuteOfHour();
        return hours.getY(this.events.get(i).getStartHour().getHourOfDay()) + temp4;
    }

    /**
     *@param vbox set the events VBox.
     */
    public void setVBox(final VBox vbox) {
        this.myvbox = vbox;
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

    private void buildVBox() {
        final VBox vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        for (int i = 0; i < this.events.size(); i++) {
                final Pane panel = new Pane();
                final Label name = new Label(this.events.get(i).getName());

                panel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf(this.events.get(i).getColor()), CornerRadii.EMPTY, Insets.EMPTY)));
                panel.setBorder(new Border(
                        new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                panel.getChildren().add(name);




                //System.out.println(temp4);
                if (i != 0) {
                panel.setTranslateY(this.getY(i) - temp10);
                } else {
                panel.setTranslateY(this.getY(i));
                }

                final float temp = this.events.get(i).getEndHour().getHourOfDay() - this.events.get(i).getStartHour().getHourOfDay();
                final float temp2 = (this.events.get(i).getEndHour().getMinuteOfHour() - this.events.get(i).getStartHour().getMinuteOfHour()) / 10;
                System.out.println(temp10);
                panel.setPrefHeight(temp * hours.getY(0) * 2 + temp2 * (hours.getY(0) / 3));
                temp10 += temp * hours.getY(0) * 2 + temp2 * (hours.getY(0) / 3);
                vbox.getChildren().add(panel);
        }
        setVBox(vbox);
    }



}
