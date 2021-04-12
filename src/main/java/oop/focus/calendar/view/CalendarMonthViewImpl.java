package oop.focus.calendar.view;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarDayController;
import oop.focus.calendar.controller.CalendarDayControllerImpl;
import oop.focus.calendar.controller.CalendarMonthController;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.model.DayImpl;
import oop.focus.diary.controller.DailyMoodControllerImpl;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.view.DailyMoodView;
import oop.focus.diary.view.DailyMoodViewImpl;



public class CalendarMonthViewImpl implements CalendarMonthView {

    //Classes
    private final CalendarMonthController monthcontroller;

    //View
    private final Label monthinfo;
    private Stage daywindows;
    private VBox monthbox;

    //Variables
    private int counter;     // count the days in a row
    private int count;     // count the rows
    private final CalendarType type;

    //Lists
    private Map<Button, Scene> cells;

    //Costants
    private static final int BORDER = 20;
    private static final int TABLEDAYS = 7;
    private static final int GAP = 10;
    private static final int DIM = 100;
    private static final double MOODFONT = 1.5;
    private static final double DAYWIDTH = 200;
    private static final double DAYHEIGHT = 500;

    /**
     * Used for Initialize the month view.
     * @param type : type of calendar to build
     * @param monthcontroller
     */
    public CalendarMonthViewImpl(final CalendarType type, final CalendarMonthController monthcontroller) {
        this.type = type;
        cells  = new HashMap<>();
        this.monthinfo = new Label();
        this.monthcontroller = monthcontroller;
        setMonthView(buildMonthView());
    }




    /**
     * Build the calendar month view.
     * @return panel
     */
    private VBox buildMonthView() {


        final VBox container = new VBox();

        container.setAlignment(Pos.CENTER);


        container.getChildren().add(buildTopPanel(container));

        container.getChildren().add(buildGridMonth());

        container.setPadding(new Insets(BORDER, BORDER, BORDER, BORDER));

        container.autosize();

        return container;

    }

    public final GridPane buildGridMonth() {

        final GridPane daysGrid = new GridPane();


        final DayImpl firstday = monthcontroller.getMonth().get(0);

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
        monthcontroller.getMonth().forEach(day -> {

            if (counter % TABLEDAYS == 0) {
                counter = 0;
                count++;
            }
            counter++;

            if (type.equals(CalendarType.NORMAL)) {
                normalCalendar(daysGrid, day);
            } else if (type.equals(CalendarType.DIARY)) {
                try {
                    diaryCalendar(daysGrid, day);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type.equals(CalendarType.HOMEPAGE)) {
                homepageCalendar(daysGrid, day);
            }
        });

        daysGrid.setAlignment(Pos.CENTER);
        daysGrid.setHgap(GAP);
        daysGrid.setVgap(GAP);
        return daysGrid;
    }

    private void normalCalendar(final GridPane daysGrid, final DayImpl day) {
        final Button jb = new Button(" " + day.getNumber() + " ");
        jb.setFont(Font.font(monthcontroller.getFontSize()));
        jb.setAlignment(Pos.CENTER);
        jb.setOnAction(getDayView());
        jb.setPrefSize(DIM, DIM);
        final CalendarDayController daycontroller = new CalendarDayControllerImpl(day, DAYWIDTH, DAYHEIGHT);
        monthcontroller.configureday(daycontroller);
        daycontroller.buildDay();
        final ScrollPane daypane = new ScrollPane(daycontroller.getView().getRoot());
        daypane.setFitToWidth(true);
        cells.put(jb, new Scene(daypane, daycontroller.getWidth(), daycontroller.getHeight()));
        daysGrid.add(jb, counter, count);
    }

    private void homepageCalendar(final GridPane daysGrid, final DayImpl day) {
        final Label jb = new Label(" " + day.getNumber() + " ");
        jb.setFont(Font.font(monthcontroller.getFontSize()));
        jb.setAlignment(Pos.CENTER);
        jb.setPrefSize(DIM, DIM);
        daysGrid.add(jb, counter, count);
    }

    private void diaryCalendar(final GridPane daysGrid, final DayImpl day) throws IOException {
        final VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(DIM, DIM);
        final Label daynumber = new Label(" " + day.getNumber() + " ");
        daynumber.setFont(Font.font(monthcontroller.getFontSize() / MOODFONT));
        container.getChildren().add(daynumber);
        final LocalDate localday = new LocalDate(day.getYear(), day.getMonthNumber(), day.getNumber());
        final DailyMoodControllerImpl moodcontroller = new DailyMoodControllerImpl(new DailyMoodManagerImpl(monthcontroller.getDataSource()));
        final DailyMoodView moodview = new DailyMoodViewImpl(moodcontroller);
       if (moodcontroller.getValueByDate(localday).isPresent()) {
           final ImageView jb = moodview.getImages().get(moodcontroller.getValueByDate(localday).get());
           jb.fitWidthProperty().bind(container.widthProperty().divide(2));
           jb.fitHeightProperty().bind(container.heightProperty().divide(2));
           container.getChildren().add(jb);
        }
        daysGrid.add(container, counter, count);
    }

    /**
     * used for create the names of the day row.
     * @return list : dayNameLabel
     */
    private List<Label> dayNameLabel() {


        final List<Label> daysname = new ArrayList<>();

        final Label lunedi = new Label("lun");
        lunedi.setFont(Font.font(monthcontroller.getFontSize()));
        final Label martedi = new Label("mar");
        martedi.setFont(Font.font(monthcontroller.getFontSize()));
        final Label mercoledi = new Label("mer");
        mercoledi.setFont(Font.font(monthcontroller.getFontSize()));
        final Label giovedi = new Label("gio");
        giovedi.setFont(Font.font(monthcontroller.getFontSize()));
        final Label venerdi = new Label("ven");
        venerdi.setFont(Font.font(monthcontroller.getFontSize()));
        final Label sabato = new Label("sab");
        sabato.setFont(Font.font(monthcontroller.getFontSize()));
        final Label domenica = new Label("dom");
        domenica.setFont(Font.font(monthcontroller.getFontSize()));

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




    /**
     * Used for build the top panel of the month view.
     * @param container : is the place where the box will be.
     */
    private HBox buildTopPanel(final VBox container) {
        final HBox toppanel = new HBox();
        this.monthinfo.setText(monthcontroller.getMonth().get(0).getYear() + "   " + monthcontroller.getMonth().get(0).getMonth());
        this.monthinfo.setFont(Font.font(monthcontroller.getFontSize()));
        this.monthinfo.setAlignment(Pos.CENTER);

        final Button next = new Button("prossimo");
        final Button previous = new Button("precedente");
        next.setOnAction(changeMonthButton(this, false));
        previous.setOnAction(changeMonthButton(this, true));

        toppanel.getChildren().add(previous);
        toppanel.getChildren().add(monthinfo);
        toppanel.getChildren().add(next);
        toppanel.setAlignment(Pos.CENTER);
        toppanel.prefWidthProperty().bind(container.widthProperty());
        toppanel.setSpacing(BORDER);
        return toppanel;
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
                monthcontroller.getCalendarLogic().changeMonth(flag);
                monthcontroller.setMonth();
                updateView(monthview);
            }

        };
    }


    public final void updateView(final CalendarMonthView monthview) {
        cells  = new HashMap<>();
        monthcontroller.getCalendarLogic().generateMonth();
        monthcontroller.setMonth();
        this.monthbox.getChildren().remove(this.monthbox.getChildren().size() - 1);
        this.monthbox.getChildren().add(buildGridMonth());
        this.setMonthInfo(monthview.getMonthInfo(), monthcontroller.getMonth().get(0).getYear() + "   " + monthcontroller.getMonth().get(0).getMonth());
    }

    public final void setMonthView(final VBox month) {
        this.monthbox = month;
    }

    public final VBox getMonthView() {
        monthcontroller.updateView();
        return monthbox;
    }

    public final Label getMonthInfo() {
        return this.monthinfo;
    }


    public final void setMonthInfo(final Label monthinfo, final String string) {
        monthinfo.setText(string);
    }


    public final Node getRoot() {
        return getMonthView();
    }

}
