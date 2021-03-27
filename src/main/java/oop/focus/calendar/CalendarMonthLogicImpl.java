package oop.focus.calendar;

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

public class CalendarMonthLogicImpl implements CalendarMonthLogic {

    private static final int TABLEDAYS = 7;
    private static final int SPACING = 50;
    private static final int DIM = 50;
    private static final int FONTSIZE = 20;
    private int counter;
    private int count;

    /**
     * 
     * @param month    list with the days of the calendar
     * @param cells    Map that link the button to the day
     * @return grid    Grid with the days
     */
    public GridPane buildGridMonth(final List<DayImpl> month, final Map<Button, CalendarDaysLogicImpl> cells) {

        final EventHandler<ActionEvent> al = new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {

                final Button bt = (Button) event.getSource();
                final CalendarDaysLogicImpl p = cells.get(bt);

                Stage stage = new Stage();
                stage.setScene(new Scene(p.getScroller(), p.getWidth(), p.getHeight()));
                stage.show();

            }

        };

        final GridPane daysGrid = new GridPane();

        final DayImpl firstday = month.get(0);

        counter = 0;
        count = 0;


        for (int i = 0; i < dayNameLabel().size(); i++) {
            daysGrid.add(dayNameLabel().get(i), i + 1, 0);
            counter++;
        }

        count++;
        counter = 0;

        for (int i = 1; i < firstday.getDayOfTheWeek(); i++) {
            final Button jb = new Button();
            jb.setVisible(false);
            jb.setPrefSize(DIM, DIM);
            daysGrid.add(jb, i, count);
            counter++;
        }


        month.forEach(day -> {

            if (counter % TABLEDAYS == 0) {
                counter = 0;
                count++;
            }
            counter++;

            final Button jb = new Button(" " + day.getNumber() + " ");
            jb.setOnAction(al);
            jb.setPrefSize(DIM, DIM);
            final CalendarDaysLogicImpl p = new CalendarDaysLogicImpl(day, 200, 500, 50);
            p.buildDay();
            cells.put(jb, p);
            daysGrid.add(jb, counter, count);

        });

        daysGrid.setAlignment(Pos.CENTER);
        daysGrid.setHgap(SPACING / 10);
        daysGrid.setVgap(SPACING / 10);
        return daysGrid;
    }









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
