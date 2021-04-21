package oop.focus.calendar.day.view;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import oop.focus.calendar.day.controller.CalendarDayController;



/**
 * Implementation of {@link CalendarDaysView}.
 */
public class CalendarDaysViewImpl implements CalendarDaysView {

    //Classes
    private final CalendarDayController dayController;

    //View
    private VBox dayBox;

    //Variables
    private String dailyEvents;


    /**
     * Used for Initialize days view.
     * @param dayController : the {@link CalendarDayController} of the day
     */
    public CalendarDaysViewImpl(final CalendarDayController dayController) {
        this.dayController = dayController;
        this.dayBox = new VBox();
    }

    /*
     * used for configure the day view and build the third (from the top) box 
     * that is composed by the {@link EventViewImpl} and {@link HoursView}
     */
    private void configureDay(final HBox myHBox) {

        this.dayController.getHoursBox().setFormat(this.dayController.getFormat());
        this.dayController.getHoursBox().setSpacing(this.dayController.getSpacing());

        this.dayController.getHoursBox().buildVBox();
        this.dayController.getEventBox().buildVBox();

        this.dayController.getHoursBox().getVBox().prefWidthProperty().bind(myHBox.widthProperty().divide(2));
        this.dayController.getEventBox().getVBox().prefWidthProperty().bind(myHBox.widthProperty());

        myHBox.getChildren().add(this.dayController.getHoursBox().getVBox());
        myHBox.getChildren().add(this.dayController.getEventBox().getVBox());
        myHBox.setAlignment(Pos.CENTER);
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

        this.setDailyEvent();
        daily.setText(this.getDailyEvent());

        daily.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.getChildren().add(daily);
    }

    /**
     * {@inheritDoc}
     */
    public final void buildDay() {
        //container is used for contain all the component of the day
        final VBox container = new VBox();
        //myhbox is used for contain the HoursBox and EventBox
        final HBox myHBox = new HBox();

        this.configureDay(myHBox);


        final Label nameDay = new Label(this.dayController.getDay().getName());
        final Label numberDay = new Label(" " + this.dayController.getDay().getNumber() + " ");
        nameDay.setAlignment(Pos.CENTER);
        numberDay.setAlignment(Pos.CENTER);
        container.getChildren().add(nameDay);
        container.getChildren().add(numberDay);

        final Label daily = new Label();
        this.configureDailyEvent(container, daily);

        container.getChildren().add(myHBox);
        container.setBorder(new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.setAlignment(Pos.CENTER);

        this.setContainer(container);
    }


    /**
     * Used for set the container panel of the day view.
     * @param container : is the day box
     */
    private void setContainer(final VBox container) {
        this.dayBox = new VBox(container);
    }

    /**
     * {@inheritDoc}
     */
    public final VBox getContainer() {
        return this.dayBox;
    }

    /**
     * Used for set the string where are written the daily events.
     */
    private void setDailyEvent() {
        this.dailyEvents = this.dayController.writeDailyEvent();
    }

    /**
     * Used for get the string where are written the daily events.
     * @return String
     */
    private String getDailyEvent() {
        return this.dailyEvents;
    }

    /**
     * {@inheritDoc}
     */
    public final Node getRoot() {
        return this.getContainer();
    }


}
