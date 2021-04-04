package oop.focus.calendar.view;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import oop.focus.calendar.controller.CalendarSettingsControllerImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;


public class CalendarDaysViewImpl implements CalendarDaysView {


    //Classes
    private final HoursViewImpl hoursbox;
    private final EventViewImpl eventbox;
    private final DayImpl day;

    //View
    private ScrollPane scroller = new ScrollPane();
    private VBox dayview = new VBox();

    //Variables
    private final double width;
    private final double height;
    private double spacing;
    private Format format;


    /**
     * 
     * @param day    date of the day that we want build
     * @param width  max width of the day view.
     * @param height  max height of the day view.
     */
    public CalendarDaysViewImpl(final DayImpl day, final double width, final double height) {

        final CalendarSettingsControllerImpl optionscontroller = new CalendarSettingsControllerImpl();

        this.day = day;
        hoursbox = new HoursViewImpl();
        eventbox = new EventViewImpl(hoursbox, day);

        this.width = width;
        this.height = height;
        this.spacing = optionscontroller.getSpacing();
        format = optionscontroller.getFormat();
    }

    /*
     * used for configure the day view and build the third (from the top) box 
     * that is composed by the EventView and HoursView
     */
    private void configureDay(final HBox myhbox) {

        hoursbox.setFormat(format);
        hoursbox.setSpacing(spacing);

        hoursbox.buildVBox();
        eventbox.buildVBox();

        hoursbox.getVBox().prefWidthProperty().bind(myhbox.widthProperty().divide(2));
        eventbox.getVBox().prefWidthProperty().bind(myhbox.widthProperty());

        myhbox.getChildren().add(hoursbox.getVBox());
        myhbox.getChildren().add(eventbox.getVBox());
        myhbox.setAlignment(Pos.CENTER);
    }


    /**
     * used for build the daily event box (the second from the top of the view).
     * @param container : box where put the daily event label
     * @param daily : label where put the daily event
     */
    private void configureDailyEvent(final VBox container, final Label daily) {
        daily.prefWidthProperty().bind(container.widthProperty());
        daily.setAlignment(Pos.CENTER);
        daily.setTextAlignment(TextAlignment.CENTER);
        daily.setText("Attività giornaliere: \nLavoro\nAllenamento");
        daily.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.getChildren().add(daily);
    }


    public final void buildDay() {
        //container is used for contain all the component of the day
        final VBox container = new VBox();
        //myhbox is used for contain the HoursBox and EventBox
        final HBox myhbox = new HBox();

        configureDay(myhbox);


        final Label nameDay = new Label(day.getName());
        final Label numberDay = new Label(" " + day.getNumber() + " ");
        nameDay.setAlignment(Pos.CENTER);
        numberDay.setAlignment(Pos.CENTER);
        container.getChildren().add(nameDay);
        container.getChildren().add(numberDay);

        final Label daily = new Label();
        configureDailyEvent(container, daily);

        container.getChildren().add(myhbox);
        container.setBorder(new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.setAlignment(Pos.CENTER);


        setContainer(container);
        setScroller(container);
    }

    /**
     * Used for set the day box in a scrollable panel.
     * @param container : is the day box
     */
    private void setScroller(final VBox container) {
        this.scroller = new ScrollPane(container);
        this.scroller.setFitToWidth(true);
    }


    public final ScrollPane getScroller() {
        return this.scroller;
    }

    /**
     * Used for set the container panel of the day view.
     * @param container : is the day box
     */
    private void setContainer(final VBox container) {
        this.dayview = new VBox(container);
    }


    public final VBox getContainer() {
        return this.dayview;
    }


    public final double getWidth() {
        return this.width;
    }


    public final double getHeight() {
        return this.height;
    }


    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }


    public final void setFormat(final Format format) {
        this.format = format;
    }


}
