package oop.focus.calendar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysViewImpl;

public class CalendarMonthControllerImpl implements CalendarMonthController {

    private static final int TABLEDAYS = 7;
    private static final int SPACING = 10;
    private static final int DIM = 50;
    private static final int FONTSIZE = 12;
    private int counter;
    private int count;


    /**
     * 
     * @param month    list with the days of the calendar
     * @param cells    Map that link the button to the day
     * @return grid    Grid with the days
     */
    public GridPane buildGridMonth(final List<DayImpl> month, final Map<Button, CalendarDaysViewImpl> cells) {

        //used for launch a windows with the day view of the clicked one
        final EventHandler<ActionEvent> getdayview = new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {

                final Button bt = (Button) event.getSource();
                final CalendarDaysViewImpl p = cells.get(bt);

                Stage stage = new Stage();
                stage.setScene(new Scene(p.getScroller(), p.getWidth(), p.getHeight()));
                stage.show();

            }

        };

        final GridPane daysGrid = new GridPane();


        final DayImpl firstday = month.get(0);

        counter = 0;
        count = 0;

        //used for create the first row with the name of the days
        for (int i = 0; i < dayNameLabel().size(); i++) {
            daysGrid.add(dayNameLabel().get(i), i + 1, 0);
            counter++;
        }

        count++;
        counter = 0;

        //used for put the button of the day in the correct position
        for (int i = 1; i < firstday.getDayOfTheWeek(); i++) {
            final Button jb = new Button();
            jb.setVisible(false);
            jb.setPrefSize(DIM, DIM);
            daysGrid.add(jb, i, count);
            counter++;
        }


        //build the month grid
        month.forEach(day -> {

            if (counter % TABLEDAYS == 0) {
                counter = 0;
                count++;
            }
            counter++;

            final Button jb = new Button(" " + day.getNumber() + " ");
            jb.setOnAction(getdayview);
            jb.setPrefSize(DIM, DIM);
            final CalendarDaysViewImpl p = new CalendarDaysViewImpl(day, 200, 500);
            p.buildDay();
            cells.put(jb, p);
            daysGrid.add(jb, counter, count);

        });

        daysGrid.setAlignment(Pos.CENTER);
        daysGrid.setHgap(SPACING / 10);
        daysGrid.setVgap(SPACING / 10);
        return daysGrid;
    }








    //used for create build the names of the day row
    private List<Label> dayNameLabel() {


        final List<Label> daysname = new ArrayList<>();

        final Label lunedi = new Label("lun");
        lunedi.setFont(Font.font(FONTSIZE));
        final Label martedi = new Label("mar");
        martedi.setFont(Font.font(FONTSIZE));
        final Label mercoledi = new Label("mer");
        mercoledi.setFont(Font.font(FONTSIZE));
        final Label giovedi = new Label("gio");
        giovedi.setFont(Font.font(FONTSIZE));
        final Label venerdi = new Label("ven");
        venerdi.setFont(Font.font(FONTSIZE));
        final Label sabato = new Label("sab");
        sabato.setFont(Font.font(FONTSIZE));
        final Label domenica = new Label("dom");
        domenica.setFont(Font.font(FONTSIZE));

        daysname.add(lunedi);
        daysname.add(martedi);
        daysname.add(mercoledi);
        daysname.add(giovedi);
        daysname.add(venerdi);
        daysname.add(sabato);
        daysname.add(domenica);

        daysname.forEach(e -> e.setPrefSize(DIM, DIM));
        daysname.forEach(e -> e.setAlignment(Pos.CENTER));

        return daysname;
    }

}
