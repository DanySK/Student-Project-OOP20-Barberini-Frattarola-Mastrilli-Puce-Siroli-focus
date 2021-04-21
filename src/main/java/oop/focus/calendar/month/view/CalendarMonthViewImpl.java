package oop.focus.calendar.month.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static java.util.Objects.nonNull;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import oop.focus.calendar.day.controller.CalendarDayController;
import oop.focus.calendar.day.controller.CalendarDayControllerImpl;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.diary.controller.DailyMoodControllerImpl;
import oop.focus.diary.model.DailyMoodManagerImpl;
import oop.focus.diary.view.DailyMoodView;



/**
 * Implementation of {@link CalendarMonthView}.
 */
public class CalendarMonthViewImpl implements CalendarMonthView {

    //Classes
    private final CalendarMonthController monthController;

    //View
    private final Label monthInfo;
    private Stage dayWindows;
    private final VBox monthBox;

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
    private static final int DIM = 200;
    private static final double MOOD_FONT = 1.5;
    private static final double DAY_WIDTH = 200;
    private static final double DAY_HEIGHT = 500;



    /**
     * Used for Initialize the month view.
     * @param type : the {@link CalendarType} of calendar to build
     * @param monthController : the {@link CalendarMonthController} of the month
     */
    public CalendarMonthViewImpl(final CalendarType type, final CalendarMonthController monthController) {
        this.type = type;
        this.cells = new HashMap<>();
        this.monthInfo = new Label();
        this.monthController = monthController;
        this.monthBox = this.buildMonthView();
    }




    /**
     * Build the calendar month view.
     * @return VBox
     */
    private VBox buildMonthView() {


        final VBox container = new VBox();

        container.setAlignment(Pos.CENTER);


        container.getChildren().add(this.buildTopPanel(container));

        container.getChildren().add(this.buildGridMonth());

        container.setPadding(new Insets(BORDER, BORDER, BORDER, BORDER));

        container.autosize();

        return container;

    }

    /**
     * Used for build the grid with the days of the month.
     * @return grid : Grid with the days
     */
    private GridPane buildGridMonth() {

        final GridPane daysGrid = new GridPane();


        final Day firstDay = this.monthController.getMonth().get(0);

        this.counter = 0;
        this.count = 0;

        //used for create the first row with the name of the days
        for (int i = 0; i < this.dayNameLabel().size(); i++) {
            daysGrid.add(this.dayNameLabel().get(i), i + 1, 0);
            this.counter++;
        }

        // count the days in a row
        this.count++;
        // count the rows
        this.counter = 0;

        //used for put the button of the day in the correct position
        for (int i = 1; i < firstDay.getDayOfTheWeek(); i++) {
            final Button jb = new Button();
            jb.setVisible(false);
            jb.setPrefSize(DIM, DIM);
            daysGrid.add(jb, i, this.count);
            this.counter++;
        }


        //build the month grid
        this.monthController.getMonth().forEach(day -> {

            if (this.counter % TABLE_DAYS == 0) {
                this.counter = 0;
                this.count++;
            }
            this.counter++;

            if (this.type.equals(CalendarType.NORMAL)) {
                this.normalCalendar(daysGrid, day);
            } else if (this.type.equals(CalendarType.DIARY)) {
                this.diaryCalendar(daysGrid, day);
            } else if (this.type.equals(CalendarType.HOMEPAGE)) {
                this.homepageCalendar(daysGrid, day);
            }
        });
        daysGrid.setAlignment(Pos.CENTER);
        daysGrid.setHgap(GAP);
        daysGrid.setVgap(GAP);
        return daysGrid;
    }

    /**
     * Used for build a {@link CalendarMonthView} calendar.
     * It is made up of buttons that, when clicked,
     * open a window with the information of the day
     * @param daysGrid : grid where put the day
     * @param day : the {@link Day} from where start to build the calendar
     */
    private void normalCalendar(final GridPane daysGrid, final Day day) {
        final Button jb = new Button(" " + day.getNumber() + " ");
        jb.setFont(Font.font(this.monthController.getFontSize()));
        jb.setAlignment(Pos.CENTER);
        jb.setOnAction(this.getDayView());
        jb.setPrefSize(DIM, DIM);
        if (!day.getEvents().isEmpty()) {
            jb.getStyleClass().add("grid-event");
        } else {
            jb.getStyleClass().add("grid");
        }
        final CalendarDayController dayController = new CalendarDayControllerImpl(day, DAY_WIDTH, DAY_HEIGHT);
        this.monthController.configureDay(dayController);
        dayController.buildDay();
        final ScrollPane dayPane = new ScrollPane(dayController.getView().getRoot());
        dayPane.setFitToWidth(true);
        this.cells.put(jb, new Scene(dayPane, dayController.getWidth(), dayController.getHeight()));
        daysGrid.add(jb, this.counter, this.count);
    }

    /**
     * Used for build an {@link oop.focus.homepage.view.HomePageBaseView} calendar.
     * Is composed only with label with the number of the day.
     * @param daysGrid : grid where put the day
     * @param day : the {@link Day} from where start to build the calendar
     */
    private void homepageCalendar(final GridPane daysGrid, final Day day) {
        final Label jb = new Label(" " + day.getNumber() + " ");
        jb.setFont(Font.font(this.monthController.getFontSize()));
        jb.setAlignment(Pos.CENTER);
        jb.setPrefSize(DIM, DIM);
        if (!day.getEvents().isEmpty()) {
            jb.getStyleClass().add("grid-event");
        } else {
            jb.getStyleClass().add("grid");
        }
        daysGrid.add(jb, this.counter, this.count);
    }

    /**
     * Used for build an {@link oop.focus.diary.view.DiaryView} calendar.
     * Is composed only with label with the number of the day
     * and an icon that represent you daily humor.
     * @param daysGrid : grid where put the day
     * @param day : the {@link Day} from where start to build the calendar
     */
    private void diaryCalendar(final GridPane daysGrid, final Day day) {
        final VBox container = new VBox();
        final Label dayNumber = new Label(" " + day.getNumber() + " ");
        dayNumber.setFont(Font.font(this.monthController.getFontSize() / MOOD_FONT));
        container.getChildren().add(dayNumber);
        final LocalDate localDay = new LocalDate(day.getYear(), day.getMonthNumber(), day.getNumber());
        final DailyMoodControllerImpl moodController = new DailyMoodControllerImpl(new DailyMoodManagerImpl(this.monthController.getDataSource()));
        if (moodController.getValueByDate(localDay).isPresent()) {
            final Optional<Integer> index = moodController.getValueByDate(localDay);
            index.ifPresent(integer -> container.getChildren().add(new DailyMoodView(integer).getRoot()));
        }
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(DIM, DIM);
        daysGrid.add(container, this.counter, this.count);
    }

    /**
     * used for create the row of the days names.
     * @return list : dayNameLabel
     */
    private List<Label> dayNameLabel() {


        final List<Label> daysName = new ArrayList<>();

        final Label lunedi = new Label("lun");
        lunedi.setFont(Font.font(this.monthController.getFontSize()));
        final Label martedi = new Label("mar");
        martedi.setFont(Font.font(this.monthController.getFontSize()));
        final Label mercoledi = new Label("mer");
        mercoledi.setFont(Font.font(this.monthController.getFontSize()));
        final Label giovedi = new Label("gio");
        giovedi.setFont(Font.font(this.monthController.getFontSize()));
        final Label venerdi = new Label("ven");
        venerdi.setFont(Font.font(this.monthController.getFontSize()));
        final Label sabato = new Label("sab");
        sabato.setFont(Font.font(this.monthController.getFontSize()));
        final Label domenica = new Label("dom");
        domenica.setFont(Font.font(this.monthController.getFontSize()));

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
        this.monthInfo.setText(this.monthController.getMonth().get(0).getYear() + "   " + this.monthController.getMonth().get(0).getMonth());
        this.monthInfo.setFont(Font.font(this.monthController.getFontSize()));
        this.monthInfo.setAlignment(Pos.CENTER);

        final Button next = new Button("prossimo");
        final Button previous = new Button("precedente");
        next.setOnAction(this.changeMonthButton(false));
        previous.setOnAction(this.changeMonthButton(true));

        next.getStyleClass().add("calendar-upper-button");
        previous.getStyleClass().add("calendar-upper-button");

        topPanel.getChildren().add(previous);
        topPanel.getChildren().add(this.monthInfo);
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
        return event -> {
            final Button bt = (Button) event.getSource();
            final Scene dayCheck = this.cells.get(bt);
            if (this.dayWindows == null) {

                this.dayWindows = new Stage();
                final Scene p = this.cells.get(bt);

                this.dayWindows.setScene(p);

            } else if (!this.dayWindows.getScene().equals(dayCheck)) {

                this.dayWindows.close();
                this.dayWindows = new Stage();
                final Scene p = this.cells.get(bt);
                this.dayWindows.setScene(p);
            }

            this.dayWindows.show();

        };
    }


    /**
     * Is an EventHandler for change the month (next or previous one).
     * @param flag : true previous month, false next month
     * @return EventHandler
     */
    private EventHandler<ActionEvent> changeMonthButton(final Boolean flag) {
        return event -> {
            this.monthController.getCalendarLogic().changeMonth(flag);
            this.monthController.setMonth();
            this.updateView();
        };
    }


    /**
     * {@inheritDoc}
     */
    public final void updateView() {
        if (nonNull(this.dayWindows)) {
            this.dayWindows.close();
        }
        this.cells = new HashMap<>();
        this.monthController.getCalendarLogic().generateMonth();
        this.monthController.setMonth();
        this.monthBox.getChildren().remove(this.monthBox.getChildren().size() - 1);
        this.monthBox.getChildren().add(this.buildGridMonth());
        this.setMonthInfo(this.monthInfo, this.monthController.getMonth().get(0).getYear() + "   " + this.monthController.getMonth().get(0).getMonth());
    }

    /**
     * {@inheritDoc}
     */
    public final VBox getMonthView() {
        this.monthController.updateView();
        return this.monthBox;
    }


    /**
     * Used for set the month view.
     * @param monthInfo : the box that will contain all the object of the month view
     */
    private void setMonthInfo(final Label monthInfo, final String string) {
        monthInfo.setText(string);
    }

    /**
     * {@inheritDoc}
     */
    public final Node getRoot() {
        return this.getMonthView();
    }

}
