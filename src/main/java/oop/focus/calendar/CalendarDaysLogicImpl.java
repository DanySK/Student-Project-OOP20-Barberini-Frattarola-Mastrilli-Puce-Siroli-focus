package oop.focus.calendar;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import oop.focus.calendar.HoursViewImpl.Format;


public class CalendarDaysLogicImpl extends Application implements CalendarDaysLogic {


    private final HoursViewImpl hoursbox = new HoursViewImpl();
    private final EventViewImpl eventbox = new EventViewImpl(hoursbox);
    private ScrollPane scroller = new ScrollPane();
    private static final int DIMX = 200;
    private static final int DIMY = 450;
    private static final int SPACING = 50;


    private void configureDay(final HBox myhbox) {

        hoursbox.setFormat(Format.NORMAL);
        hoursbox.setSpacing(SPACING);

        hoursbox.buildVBox();
        eventbox.buildVBox();

        hoursbox.getVBox().prefWidthProperty().bind(myhbox.widthProperty().divide(2));
        eventbox.getVBox().prefWidthProperty().bind(myhbox.widthProperty());

        myhbox.getChildren().add(hoursbox.getVBox());
        myhbox.getChildren().add(eventbox.getVBox());
        myhbox.setAlignment(Pos.CENTER);
    }



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
     * @return scene
     */
    public Scene buildDay() {

        final VBox container = new VBox();
        final HBox myhbox = new HBox();

        configureDay(myhbox);


        final Label nameDay = new Label("lunedi");
        final Label numberDay = new Label("07");
        nameDay.setAlignment(Pos.CENTER);
        numberDay.setAlignment(Pos.CENTER);
        container.getChildren().add(nameDay);
        container.getChildren().add(numberDay);

        final Label daily = new Label();
        configureDailyEvent(container, daily);

        container.getChildren().add(myhbox);
        container.setBorder(new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.setAlignment(Pos.CENTER);


        final ScrollPane scroller = new ScrollPane(container);
        setScroller(scroller);
        scroller.setFitToWidth(true);
        return new Scene(scroller, DIMX, DIMY); 
    }

    private void setScroller(final ScrollPane scroller) {
        this.scroller = scroller;
    }

    /**
     * Get the scroller with all the object of the day.
     * @return stage
     */
    public ScrollPane getScroller() {
        return this.scroller;
    }


    /**
     * Qualcosa.
     * @param stage qualcosa
     */
    public void start(final Stage stage) {


        // set the scene 
        stage.setScene(buildDay()); 

        stage.show();


    }

    public static void main(final String... args) { 
        // launch the application 
        launch(args); 

    }




}
