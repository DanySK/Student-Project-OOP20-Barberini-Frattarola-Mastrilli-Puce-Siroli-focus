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
    private final CalendarMonthController monthController;

    //View
    private final Label monthInfo;
    private Stage dayWindows;
    private VBox monthBox;

    //Variables
    private int counter;     // count the days in a row
    private int count;     // count the rows
    private final CalendarType type;

    //Lists
    private Map<Button, Scene> cells;

    //Costants
    private static final int BORDER = 20;
    private static final int TABLE_DAYS = 7;
    private static final int GAP = 10;
    private static final int DIM = 100;
    private static final double MOOD_FONT = 1.5;
    private static final double DAY_WIDTH = 200;
    private static final double DAY_HEIGHT = 500;

    /**
     * Used for Initialize the month view.
     * @param type : type of calendar to build
     * @param monthcontroller : controller of the month
     */
    public CalendarMonthViewImpl(final CalendarType type, final CalendarMonthController monthcontroller) {
        this.type = type;
        cells  = new HashMap<>();
        this.monthInfo = new Label();
        this.monthController = monthcontroller;
        setMonthView(buildMonthView());
    }




    /**
     * Build the calendar month view.
     * @return VBox
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


        final DayImpl firstDay = monthController.getMonth().get(0);

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
        for (int i = 1; i < firstDay.getDayOfTheWeek(); i++) {
            final Button jb = new Button();
            jb.setVisible(false);
            jb.setPrefSize(DIM, DIM);
            daysGrid.add(jb, i, count);
            counter++;
        }


        //build the month grid
        monthController.getMonth().forEach(day -> {

            if (counter % TABLE_DAYS == 0) {
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

    /**
     * Used for build a normal calendar.
     * It is made up of buttons that, when clicked,
     * open a window with the information of the day
     * @param daysGrid : grid where put the day
     * @param day : the day from where start to build the calendar
     */
    private void normalCalendar(final GridPane daysGrid, final DayImpl day) {
        final Button jb = new Button(" " + day.getNumber() + " ");
        jb.setFont(Font.font(monthController.getFontSize()));
        jb.setAlignment(Pos.CENTER);
        jb.setOnAction(getDayView());
        jb.setPrefSize(DIM, DIM);
        final CalendarDayController daycontroller = new CalendarDayControllerImpl(day, DAY_WIDTH, DAY_HEIGHT);
        monthController.configureday(daycontroller);
        daycontroller.buildDay();
        final ScrollPane daypane = new ScrollPane(daycontroller.getView().getRoot());
        daypane.setFitToWidth(true);
        cells.put(jb, new Scene(daypane, daycontroller.getWidth(), daycontroller.getHeight()));
        daysGrid.add(jb, counter, count);
    }

    /**
     * Used for build an Homepage calendar.
     * Is composed only with label with the number of the day.
     * @param daysGrid : grid where put the day
     * @param day : the day from where start to build the calendar
     */
    private void homepageCalendar(final GridPane daysGrid, final DayImpl day) {
        final Label jb = new Label(" " + day.getNumber() + " ");
        jb.setFont(Font.font(monthController.getFontSize()));
        jb.setAlignment(Pos.CENTER);
        jb.setPrefSize(DIM, DIM);
        daysGrid.add(jb, counter, count);
    }

    /**
     * Used for build an Diary calendar.
     * Is composed only with label with the number of the day
     * and an icon that represent you daily humor.
     * @param daysGrid : grid where put the day
     * @param day : the day from where start to build the calendar
     */
    private void diaryCalendar(final GridPane daysGrid, final DayImpl day) throws IOException {
        final VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(DIM, DIM);
        final Label daynumber = new Label(" " + day.getNumber() + " ");
        daynumber.setFont(Font.font(monthController.getFontSize() / MOOD_FONT));
        container.getChildren().add(daynumber);
        final LocalDate localday = new LocalDate(day.getYear(), day.getMonthNumber(), day.getNumber());
        final DailyMoodControllerImpl moodcontroller = new DailyMoodControllerImpl(new DailyMoodManagerImpl(monthController.getDataSource()));
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
     * used for create the row of the days names.
     * @return list : dayNameLabel
     */
    private List<Label> dayNameLabel() {


        final List<Label> daysName = new ArrayList<>();

        final Label lunedi = new Label("lun");
        lunedi.setFont(Font.font(monthController.getFontSize()));
        final Label martedi = new Label("mar");
        martedi.setFont(Font.font(monthController.getFontSize()));
        final Label mercoledi = new Label("mer");
        mercoledi.setFont(Font.font(monthController.getFontSize()));
        final Label giovedi = new Label("gio");
        giovedi.setFont(Font.font(monthController.getFontSize()));
        final Label venerdi = new Label("ven");
        venerdi.setFont(Font.font(monthController.getFontSize()));
        final Label sabato = new Label("sab");
        sabato.setFont(Font.font(monthController.getFontSize()));
        final Label domenica = new Label("dom");
        domenica.setFont(Font.font(monthController.getFontSize()));

        daysName.add(lunedi);
        daysName.add(martedi);
        daysName.add(mercoledi);
        daysName.add(giovedi);
        daysName.add(venerdi);
        daysName.add(sabato);
        daysName.add(domenica);

        daysName.forEach(e -> e.setPrefSize(DIM, DIM));
        daysName.forEach(e -> e.setAlignment(Pos.CENTER));

        return daysName;
    }




    /**
     * Used for build the top panel of the month view.
     * Composed by the change month button and a label where are
     * written the year and the month.
     * @param container : is the place where the box will be.
     */
    private HBox buildTopPanel(final VBox container) {
        final HBox topPanel = new HBox();
        this.monthInfo.setText(monthController.getMonth().get(0).getYear() + "   " + monthController.getMonth().get(0).getMonth());
        this.monthInfo.setFont(Font.font(monthController.getFontSize()));
        this.monthInfo.setAlignment(Pos.CENTER);

        final Button next = new Button("prossimo");
        final Button previous = new Button("precedente");
        next.setOnAction(changeMonthButton(this, false));
        previous.setOnAction(changeMonthButton(this, true));

        topPanel.getChildren().add(previous);
        topPanel.getChildren().add(monthInfo);
        topPanel.getChildren().add(next);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.prefWidthProperty().bind(container.widthProperty());
        topPanel.setSpacing(BORDER);
        return topPanel;
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
                final Scene dayCheck = cells.get(bt);
                if (dayWindows == null) {

                    dayWindows = new Stage();
                    final Scene p = cells.get(bt);

                    dayWindows.setScene(p);

                } else if (!dayWindows.getScene().equals(dayCheck)) {

                    dayWindows.close();
                    dayWindows = new Stage();
                    final Scene p = cells.get(bt);
                    dayWindows.setScene(p);
                }

                dayWindows.show();

            }

        };
    }


    public final EventHandler<ActionEvent> changeMonthButton(final CalendarMonthViewImpl monthView, final Boolean flag) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                monthController.getCalendarLogic().changeMonth(flag);
                monthController.setMonth();
                updateView(monthView);
            }

        };
    }


    public final void updateView(final CalendarMonthView monthInfo) {
        cells  = new HashMap<>();
        monthController.getCalendarLogic().generateMonth();
        monthController.setMonth();
        this.monthBox.getChildren().remove(this.monthBox.getChildren().size() - 1);
        this.monthBox.getChildren().add(buildGridMonth());
        this.setMonthInfo(monthInfo.getMonthInfo(), monthController.getMonth().get(0).getYear() + "   " + monthController.getMonth().get(0).getMonth());
    }

    public final void setMonthView(final VBox month) {
        this.monthBox = month;
    }

    public final VBox getMonthView() {
        monthController.updateView();
        return monthBox;
    }

    public final Label getMonthInfo() {
        return this.monthInfo;
    }


    public final void setMonthInfo(final Label monthInfo, final String string) {
        monthInfo.setText(string);
    }


    public final Node getRoot() {
        return getMonthView();
    }

}
