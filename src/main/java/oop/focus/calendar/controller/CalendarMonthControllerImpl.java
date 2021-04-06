package oop.focus.calendar.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;


public class CalendarMonthControllerImpl implements CalendarMonthController {


    //Classes
    private final CalendarSettingsController settingscontroller;
    private final CalendarLogic calendarlogic;


    //View
    private Stage daywindows;


    //Variables
    private final double daywidth;
    private final double dayheight;
    private int counter;     // count the days in a row
    private int count;     // count the rows

    //List
    private List<DayImpl> month;
    private Map<Button, CalendarDayController> cells;

    //Costants
    private static final int TABLEDAYS = 7;
    private static final int GAP = 10;
    private static final int DIM = 100;
    private static final int FONTSIZE = 18;


    public CalendarMonthControllerImpl(final CalendarSettingsController optioncontroller, final double daywidth, final double dayheight) {
        cells  = new HashMap<>();
        this.daywidth = daywidth;
        this.dayheight = dayheight;
        this.settingscontroller = optioncontroller;
        calendarlogic = new CalendarLogicImpl();
        this.month = calendarlogic.getMonth();
    }



    public final GridPane buildGridMonth() {

        final GridPane daysGrid = new GridPane();


        final DayImpl firstday = this.month.get(0);

        counter = 0;
        count = 0;

        //used for create the first row with the name of the days
        for (int i = 0; i < dayNameLabel().size(); i++) {
            daysGrid.add(dayNameLabel().get(i), i + 1, 0);
            counter++;
        }

        // count the days in a row
        count++;
        // count the rows
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
        this.month.forEach(day -> {

            if (counter % TABLEDAYS == 0) {
                counter = 0;
                count++;
            }
            counter++;

            final Button jb = new Button(" " + day.getNumber() + " ");
            jb.setAlignment(Pos.CENTER);
            jb.setOnAction(getDayView());
            jb.setPrefSize(DIM, DIM);
            final CalendarDayController daycontroller = new CalendarDayControllerImpl(day, this.daywidth, this.dayheight);
            configureday(daycontroller);
            daycontroller.buildDay();
            cells.put(jb, daycontroller);
            daysGrid.add(jb, counter, count);

        });

        daysGrid.setAlignment(Pos.CENTER);
        daysGrid.setHgap(GAP);
        daysGrid.setVgap(GAP);
        return daysGrid;
    }


    private void configureday(final CalendarDayController daycontroller) {
        daycontroller.setFormat(settingscontroller.getFormat());
        daycontroller.setSpacing(settingscontroller.getSpacing());
    }


    public final List<DayImpl> getMonth() {
        return this.month;
    }

    /**
     * Used for launch a windows with the day view of the clicked one.
     * @return EventHandler
     */
    private EventHandler<ActionEvent> getDayView() {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                final Button bt = (Button) event.getSource();
                final CalendarDayController daycheck = cells.get(bt);
                if (daywindows == null) {

                    daywindows = new Stage();
                    final CalendarDayController p = cells.get(bt);

                    daywindows.setScene(new Scene(p.getScroller(), p.getWidth(), p.getHeight()));

                } else if (!daywindows.getScene().getRoot().equals(daycheck.getScroller())) {

                    daywindows.close();
                    daywindows = new Stage();
                    final CalendarDayController p = cells.get(bt);
                    daywindows.setScene(new Scene(p.getScroller(), p.getWidth(), p.getHeight()));
                }

                daywindows.show();

            }

        };
    }


    public final EventHandler<ActionEvent> changeMonthButton(final CalendarMonthViewImpl monthview, final Boolean flag) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                calendarlogic.changeMonth(flag);
                month = calendarlogic.getMonth();
                updateView(monthview);
            }

        };
    }


    public final void updateView(final CalendarMonthView monthview) {
        cells  = new HashMap<>();
        this.month = calendarlogic.generateMonth();
        monthview.getMonthView().getChildren().remove(monthview.getMonthView().getChildren().size() - 1);
        monthview.getMonthView().getChildren().add(buildGridMonth());
        monthview.setMonthView(monthview.getMonthView());
        this.setMonthInfo(monthview.getMonthInfo(), this.month.get(0).getMonth() + "   " + this.month.get(0).getYear());
    }


    public final void setMonthInfo(final Label monthinfo, final String string) {
        monthinfo.setText(string);
    }


    /**
     * used for create the names of the day row.
     * @return list : dayNameLabel
     */
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


    public final void disableButton(final boolean flag) {
        cells.forEach((button, day) -> button.setDisable(flag));
    }
}
