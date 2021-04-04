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
import javafx.stage.WindowEvent;
import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysView;
import oop.focus.calendar.view.CalendarDaysViewImpl;
import oop.focus.calendar.view.CalendarMonthViewImpl;


public class CalendarMonthControllerImpl implements CalendarMonthController {


    //Classes
    private final CalendarSettingsController settingscontroller;
    private final CalendarLogic calendarlogic;
    private CalendarDaysView daycheck;

    //View
    private Stage daywindows;

    //Variables
    private final double daywidth;
    private final double dayheight;
    private int counter;
    private int count;

    //List
    private List<DayImpl> month;
    private final Map<Button, CalendarDaysView> cells;

    //Costants
    private static final int TABLEDAYS = 7;
    private static final int GAP = 5;
    private static final int DIM = 50;
    private static final int FONTSIZE = 12;


    public CalendarMonthControllerImpl(final CalendarSettingsController optioncontroller, final double daywidth, final double dayheight) {
        cells  = new HashMap<>();
        this.daywidth = daywidth;
        this.dayheight = dayheight;
        this.settingscontroller = optioncontroller;
        calendarlogic = new CalendarLogicImpl();
        month = calendarlogic.getMonth();
    }



    public final GridPane buildGridMonth() {

        //used for launch a windows with the day view of the clicked one
        final EventHandler<ActionEvent> getdayview = new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                final Button bt = (Button) event.getSource();
                daycheck = cells.get(bt);
                if (daywindows == null) {

                    daywindows = new Stage();
                    daycheck = cells.get(bt);
                    final CalendarDaysView p = cells.get(bt);

                    daywindows.setScene(new Scene(p.getScroller(), p.getWidth(), p.getHeight()));

                } else if (daywindows.getScene().getRoot() != daycheck.getScroller()) {
                    daywindows.close();
                    daywindows = new Stage();
                    daycheck = cells.get(bt);
                    final CalendarDaysView p = cells.get(bt);

                    daywindows.setScene(new Scene(p.getScroller(), p.getWidth(), p.getHeight()));
                }


                daywindows.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(final  WindowEvent event) {
                        daywindows.close();
                    }
                });

                daywindows.show();

            }

        };

        final GridPane daysGrid = new GridPane();


        final DayImpl firstday = this.month.get(0);

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
        this.month.forEach(day -> {

            if (counter % TABLEDAYS == 0) {
                counter = 0;
                count++;
            }
            counter++;

            final Button jb = new Button(" " + day.getNumber() + " ");
            jb.setOnAction(getdayview);
            jb.setPrefSize(DIM, DIM);
            final CalendarDaysView dayview = new CalendarDaysViewImpl(day, this.daywidth, this.dayheight);
            configureday(dayview);
            dayview.buildDay();
            cells.put(jb, dayview);
            daysGrid.add(jb, counter, count);

        });

        daysGrid.setAlignment(Pos.CENTER);
        daysGrid.setHgap(GAP);
        daysGrid.setVgap(GAP);
        return daysGrid;
    }


    private void configureday(final CalendarDaysView dayview) {
        dayview.setFormat(settingscontroller.getFormat());
        dayview.setSpacing(settingscontroller.getSpacing());
    }


    public final List<DayImpl> getMonth() {
        return this.month;
    }


    public final EventHandler<ActionEvent> changeMonthButton(final CalendarMonthViewImpl monthview, final Boolean flag, final CalendarMonthController monthcontroller) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                calendarlogic.changeMonth(flag);
                month = calendarlogic.getMonth();
                monthcontroller.updateView(monthview, monthcontroller);
            }

        };
    }


    public final void updateView(final CalendarMonthViewImpl monthview, final CalendarMonthController logics) {
        monthview.getMonthView().getChildren().remove(monthview.getMonthView().getChildren().size() - 1);
        monthview.getMonthView().getChildren().add(logics.buildGridMonth());
        monthview.setMonthView(monthview.getMonthView());
        this.setMonthInfo(monthview.getMonthInfo(), logics.getMonth().get(0).getMonth() + "   " + logics.getMonth().get(0).getYear());
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

}
