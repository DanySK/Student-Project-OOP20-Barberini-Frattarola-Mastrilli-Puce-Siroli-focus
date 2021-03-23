package oop.focus.calendar;

import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CalendarMonthLogicImpl implements CalendarMonthLogic {

    private static final int TABLEDAYS = 7;

    private int counter;
    private int count;


    /**
     * 
     * @param month    list with the days of the calendar
     * @param cells    Map that link the button to the day
     * @return grid    Grid with the days
     */
    public GridPane buildMonth(final List<DayImpl> month, final Map<Button, DayImpl> cells) {

        final EventHandler<ActionEvent> al = new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {

                final Button bt = (Button) event.getSource();
                final DayImpl p = cells.get(bt);
                System.out.println(p.getNumber() + ", " + p.getName());
            }

        };

        final GridPane daysGrid = new GridPane(); 

        counter = 0;
        count = 0;


        month.forEach(day -> {

            if (counter % TABLEDAYS == 0) {
                counter = 0;
                count++;
            }
            counter++;

            final Button jb = new Button(" " + day.getNumber() + " ");
            jb.setOnAction(al);
            cells.put(jb, day);
            daysGrid.add(jb, counter, count);

        });
        return daysGrid;
    }

}
