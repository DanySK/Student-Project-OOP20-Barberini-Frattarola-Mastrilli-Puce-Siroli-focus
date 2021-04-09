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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;



public class CalendarMonthControllerImpl implements CalendarMonthController {


    //Classes
    private final CalendarLogic calendarlogic;
    private final CalendarMonthView monthview;


    //View
    private Stage daywindows;
    private VBox monthbox;

    //Variables
    private final double daywidth;
    private final double dayheight;
    private int counter;     // count the days in a row
    private int count;     // count the rows
    private Format format;
    private double spacing;
    private double fontsize;

    //List
    private List<DayImpl> month;
    private Map<Button, Scene> cells;

    //Costants
    private static final double SPACING = 50; 
    private static final int TABLEDAYS = 7;
    private static final int GAP = 10;
    private static final int DIM = 100;
    private static final int DEFAULTFONTSIZE = 18;


    public CalendarMonthControllerImpl(final double daywidth, final double dayheight) {
        cells  = new HashMap<>();
        this.daywidth = daywidth;
        this.dayheight = dayheight;
        this.format = Format.NORMAL;
        this.spacing = SPACING;
        this.fontsize = DEFAULTFONTSIZE;

        calendarlogic = new CalendarLogicImpl();
        this.month = calendarlogic.getMonth();

        monthview = new CalendarMonthViewImpl(this);
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
            jb.setShape(new Circle(DIM));
            jb.setFont(Font.font(fontsize));
            jb.setAlignment(Pos.CENTER);
            jb.setOnAction(getDayView());
            jb.setPrefSize(DIM, DIM);
            final CalendarDayController daycontroller = new CalendarDayControllerImpl(day, this.daywidth, this.dayheight);
            configureday(daycontroller);
            daycontroller.buildDay();
            cells.put(jb, new Scene(daycontroller.getScroller(), daycontroller.getWidth(), daycontroller.getHeight()));
            daysGrid.add(jb, counter, count);

        });

        daysGrid.setAlignment(Pos.CENTER);
        daysGrid.setHgap(GAP);
        daysGrid.setVgap(GAP);
        return daysGrid;
    }


    private void configureday(final CalendarDayController daycontroller) {
        daycontroller.setFormat(this.format);
        daycontroller.setSpacing(this.spacing);
    }

    public final void setFontSize(final double fontsize) {
        this.fontsize = fontsize;
    }

    public final double getFontSize() {
        return this.fontsize;
    }

    public final void setFormat(final Format format) {
        this.format = format;
    }


    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }


    public final List<DayImpl> getMonth() {
        return this.month;
    }

    public final void setMonthView(final VBox month) {
        this.monthbox = month;
    }

    public final VBox getMonthView() {
        monthview.setMonthView();
        return monthbox;
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
                final Scene daycheck = cells.get(bt);
                if (daywindows == null) {

                    daywindows = new Stage();
                    final Scene p = cells.get(bt);

                    daywindows.setScene(p);

                } else if (!daywindows.getScene().equals(daycheck)) {

                    daywindows.close();
                    daywindows = new Stage();
                    final Scene p = cells.get(bt);
                    daywindows.setScene(p);
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
        this.monthbox.getChildren().remove(this.monthbox.getChildren().size() - 1);
        this.monthbox.getChildren().add(buildGridMonth());
        this.setMonthInfo(monthview.getMonthInfo(), this.month.get(0).getYear() + "   " + this.month.get(0).getMonth());
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
        lunedi.setFont(Font.font(fontsize));
        final Label martedi = new Label("mar");
        martedi.setFont(Font.font(fontsize));
        final Label mercoledi = new Label("mer");
        mercoledi.setFont(Font.font(fontsize));
        final Label giovedi = new Label("gio");
        giovedi.setFont(Font.font(fontsize));
        final Label venerdi = new Label("ven");
        venerdi.setFont(Font.font(fontsize));
        final Label sabato = new Label("sab");
        sabato.setFont(Font.font(fontsize));
        final Label domenica = new Label("dom");
        domenica.setFont(Font.font(fontsize));

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
