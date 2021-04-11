package oop.focus.calendar.view;


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
import oop.focus.calendar.controller.CalendarDayController;
import oop.focus.calendar.controller.CalendarDayControllerImpl;




public class CalendarDaysViewImpl implements CalendarDaysView {

    private final CalendarDayController daycontroller;

    //View
    private VBox daybox;

    //Variables
    private String dailyevents;


    public CalendarDaysViewImpl(final CalendarDayControllerImpl daycontroller) {
        this.daycontroller = daycontroller;

        this.daybox = new VBox();

    }

    /*
     * used for configure the day view and build the third (from the top) box 
     * that is composed by the EventView and HoursView
     */
    private void configureDay(final HBox myhbox) {

        daycontroller.getHoursBox().setFormat(daycontroller.getFormat());
        daycontroller.getHoursBox().setSpacing(daycontroller.getSpacing());

        daycontroller.getHoursBox().buildVBox();
        daycontroller.getEventBox().buildVBox();

        daycontroller.getHoursBox().getVBox().prefWidthProperty().bind(myhbox.widthProperty().divide(2));
        daycontroller.getEventBox().getVBox().prefWidthProperty().bind(myhbox.widthProperty());

        myhbox.getChildren().add(daycontroller.getHoursBox().getVBox());
        myhbox.getChildren().add(daycontroller.getEventBox().getVBox());
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

        this.setDailyEvent();
        daily.setText(this.getDailyEvent());

        daily.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.getChildren().add(daily);
    }


    public final void buildDay() {
        //container is used for contain all the component of the day
        final VBox container = new VBox();
        //myhbox is used for contain the HoursBox and EventBox
        final HBox myhbox = new HBox();

        configureDay(myhbox);


        final Label nameDay = new Label(daycontroller.getDay().getName());
        final Label numberDay = new Label(" " + daycontroller.getDay().getNumber() + " ");
        nameDay.setAlignment(Pos.CENTER);
        numberDay.setAlignment(Pos.CENTER);
        container.getChildren().add(nameDay);
        container.getChildren().add(numberDay);

        final Label daily = new Label();
        configureDailyEvent(container, daily);

        container.getChildren().add(myhbox);
        container.setBorder(new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.setAlignment(Pos.CENTER);

        this.setContainer(container);
    }

    public final void setContainer(final VBox container) {
        this.daybox = new VBox(container);
    }


    public final VBox getContainer() {
        return this.daybox;
    }

    public final void setDailyEvent() {
        this.dailyevents = daycontroller.writeDailyEvent();
    }

    public final String getDailyEvent() {
        return this.dailyevents;
    }

    public final Node getRoot() {
        return getContainer();
    }


}
