package oop.focus.calendar;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
     * 
     * @param panel qualxcosa
     */
    public void setY(final Pane panel) {

    }

    /**
     *@param vbox set VBox hours.
     */
    public void setVBox(final VBox vbox) {
        this.myvbox = vbox;
    }

    /**
     * @return get the hours box.
     */
    public VBox getVBox() {
        if (this.myvbox == null) {
            buildVBox();
        }
        return this.myvbox;
    }

    private void buildVBox() {
        final VBox vbox = new VBox();
        for (int i = 0; i < this.events.size(); i++) {
                final Pane panel = new Pane();
                final Label name = new Label(this.events.get(i).getName());
                panel.getChildren().add(name);
                panel.setLayoutY(i);
                vbox.getChildren().add(panel);
        }
        setVBox(vbox);
    }



}
