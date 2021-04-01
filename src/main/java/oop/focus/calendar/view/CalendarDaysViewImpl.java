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
import oop.focus.calendar.controller.CalendarOptionsControllerImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;


public class CalendarDaysViewImpl implements CalendarDaysView {



    private final CalendarOptionsControllerImpl optionscontroller = new CalendarOptionsControllerImpl();
    private final HoursViewImpl hoursbox = new HoursViewImpl();
    private final EventViewImpl eventbox = new EventViewImpl(hoursbox);
    private final DayImpl day;
    private ScrollPane scroller = new ScrollPane();
    private VBox dayview = new VBox();
    private final int width;
    private final int height;
    private double spacing;
    private Format format;

    /**
     * 
     * @param day    date of the day that we want build
     * @param width  max width of the day view.
     * @param height  max height of the day view.
     */
    public CalendarDaysViewImpl(final DayImpl day, final int width, final int height) {
        this.day = day; 
        this.width = width;
        this.height = height;
        this.spacing = optionscontroller.getSpacing();
        format = optionscontroller.getFormat();
    }

    /*
     * used for configure the day view and build the third (from the top) box 
     * that is composed by the EventView and HoursView
     *
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


    //used for build the daily event box (the second from the top of the view)
    private void configureDailyEvent(final VBox container, final Label daily) {
        daily.prefWidthProperty().bind(container.widthProperty());
        daily.setAlignment(Pos.CENTER);
        daily.setTextAlignment(TextAlignment.CENTER);
        daily.setText("Attività giornaliere: \nLavoro\nAllenamento");
        daily.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.getChildren().add(daily);
    }

    /**
     * Used for create Day for Calendar.
     */
    public void buildDay() {
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

    private void setScroller(final VBox container) {
        this.scroller = new ScrollPane(container);
        this.scroller.setFitToWidth(true);
    }

    /**
     * Get the scroller with all the object of the day.
     * @return scrollable view of the day
     */
    public ScrollPane getScroller() {
        return this.scroller;
    }

    private void setContainer(final VBox container) {
        this.dayview = new VBox(container);
        //this.dayview.maxWidth(width);
    }

    /**
     * Get the VBox with all the object of the day.
     * @return VBox view of the day
     */
    public VBox getContainer() {
        return this.dayview;
    }

    /**
     * Get the width of the scroller.
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the scroller.
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * 
     * @param spacing
     */
    public void setSpacing(final double spacing) {
        this.spacing = spacing;
    }

    /**
     * 
     * @param format
     */
    public void setFormat(final Format format) {
        this.format = format;
    }


}
