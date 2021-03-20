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


public class CalendarDaysLogicImpl extends Application implements CalendarDaysLogic {


    private final HoursViewImpl hoursbox = new HoursViewImpl();
    private final EventViewImpl eventbox = new EventViewImpl(hoursbox);
    private ScrollPane scroller = new ScrollPane();
    private static final int DIMX = 80;
    private static final int DIMY = 450;
    private static final int SPACING = 50;

    /**
     * Used for create Day for Calendar.
     * @return scene
     */
    public Scene buildDay() {

        hoursbox.setSpacing(SPACING);
        final VBox container = new VBox();
        final HBox myhbox = new HBox();
        myhbox.getChildren().add(hoursbox.getVBox());
        myhbox.getChildren().add(eventbox.getVBox());
        myhbox.setAlignment(Pos.CENTER);

        final Label nameDay = new Label("lunedi");
        final Label numberDay = new Label("07");
        nameDay.setAlignment(Pos.CENTER);
        numberDay.setAlignment(Pos.CENTER);
        container.getChildren().add(nameDay);
        container.getChildren().add(numberDay);

        final Label giornaliero = new Label();
        giornaliero.setAlignment(Pos.CENTER);
        giornaliero.setTextAlignment(TextAlignment.CENTER);
        giornaliero.setText("Attività giornaliere: \nLavoro\nAllenamento");
        giornaliero.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        container.getChildren().add(giornaliero);

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
