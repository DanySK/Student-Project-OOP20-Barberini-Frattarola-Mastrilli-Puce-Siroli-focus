package oop.focus.calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CalendarMonthView {

    private final Map<Button, DayImpl> cells  = new HashMap<>();
    private Label monthname;
    private List<DayImpl> month;




    /**
     * Build the calendar month.
     * @return panel
     */
    public VBox buildMonthView() {

        final CalendarMonthLogic logics = new CalendarMonthLogicImpl();
        final CalendarLogicImpl calendarlogic = new CalendarLogicImpl();
        month = calendarlogic.getMonth();


        final VBox container = new VBox();

        final Button next = new Button();
        final Button previous = new Button();

        next.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                calendarlogic.changeMonth(false);
                month = calendarlogic.getMonth();
                updateView(container, logics, monthname);
            }

        });

        previous.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                calendarlogic.changeMonth(true);
                month = calendarlogic.getMonth();
                updateView(container, logics, monthname);
            }

        });

        container.getChildren().add(previous);
        container.getChildren().add(next);

        monthname = new Label(month.get(0).getMonth());

        monthname.setAlignment(Pos.CENTER);

        container.getChildren().add(monthname);

        container.getChildren().add(dayNameLabel());

        container.getChildren().add(logics.buildMonth(month, cells));

        return container;

    }

    private void updateView(final VBox container, final CalendarMonthLogic logics, final Label monthname) {
        container.getChildren().remove(container.getChildren().size() - 1);
        container.getChildren().add(logics.buildMonth(month, cells));
        monthname.setText(month.get(0).getMonth());
    }

    private HBox dayNameLabel() {

        final HBox daysname = new HBox();

        final Label lunedi = new Label("lunedì");
        final Label martedi = new Label("martedì");
        final Label mercoledi = new Label("mercoledì");
        final Label giovedi = new Label("giovedì");
        final Label venerdi = new Label("venerdì");
        final Label sabato = new Label("sabato");
        final Label domenica = new Label("domenica");

        daysname.getChildren().add(lunedi);
        daysname.getChildren().add(martedi);
        daysname.getChildren().add(mercoledi);
        daysname.getChildren().add(giovedi);
        daysname.getChildren().add(venerdi);
        daysname.getChildren().add(sabato);
        daysname.getChildren().add(domenica);

        return daysname;
    }

}
