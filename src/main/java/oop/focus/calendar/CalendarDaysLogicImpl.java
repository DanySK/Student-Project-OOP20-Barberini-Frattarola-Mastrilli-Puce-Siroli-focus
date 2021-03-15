package oop.focus.calendar;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class CalendarDaysLogicImpl extends Application implements CalendarDaysLogic {


    private final HoursViewImpl hoursbox = new HoursViewImpl();
    private final EventViewImpl eventbox = new EventViewImpl(hoursbox);

    /**
     * create the Hours Box for the Day.
     * @param stage qualcosa
     */
    public void start(final Stage stage) {


            final HBox myhbox = new HBox();
            myhbox.getChildren().add(hoursbox.getVBox());
            myhbox.getChildren().add(eventbox.getVBox());
            final ScrollPane scroller = new ScrollPane(myhbox);
            scroller.setFitToWidth(true);

            // create a scene 
            final Scene scene = new Scene(scroller, 100, 600); 

            // set the scene 
            stage.setScene(scene); 


            stage.show(); 


    }

    public static void main(final String... args) { 
        // launch the application 
        launch(args); 

    }




}
