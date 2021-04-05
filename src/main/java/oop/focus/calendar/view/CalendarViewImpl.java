package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.controller.CalendarControllerImpl;


public class CalendarViewImpl implements CalendarView {

    //Classes
    private final CalendarControllerImpl prova = new CalendarControllerImpl(400, 400, 300, 150, 200, 400);

    //Views
    private final HBox calendarpage;
    private final Pane generalview;

    //Costants
    private static final double WIDTHBUTTON = 0.3; 
    private static final double WIDTHPANEL = 0.7;


    public CalendarViewImpl(final Pane generalview) {
        this.calendarpage = new HBox();
        this.generalview = generalview;
    }

    public final HBox buildCalendarPage() {

        final VBox buttoncolumn = new VBox();
        final VBox panelcolumn = new VBox();
        pageButton(buttoncolumn, panelcolumn, "Mese");
        calendarpage.getChildren().add(buttoncolumn);
        calendarpage.getChildren().add(panelcolumn);

        buttoncolumn.setAlignment(Pos.CENTER);
        panelcolumn.setAlignment(Pos.CENTER);

        calendarpage.prefWidthProperty().bind(this.generalview.widthProperty());
        calendarpage.prefHeightProperty().bind(this.generalview.heightProperty());
        buttoncolumn.prefWidthProperty().bind(this.calendarpage.widthProperty().multiply(WIDTHBUTTON));
        buttoncolumn.prefHeightProperty().bind(this.generalview.heightProperty());
        panelcolumn.prefWidthProperty().bind(this.calendarpage.widthProperty().multiply(WIDTHPANEL));
        panelcolumn.prefHeightProperty().bind(this.generalview.heightProperty());
        return calendarpage;
    }



    public final void pageButton(final VBox buttoncolumn, final VBox panelcolumn, final String string) {
        final Button button = new Button(string);
        button.setOnAction(changeWindows(panelcolumn, string));
        buttoncolumn.getChildren().add(button);
    }

    public final EventHandler<ActionEvent> changeWindows(final VBox panelcolumn, final String string) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                System.out.println(string);
                if (panelcolumn.getChildren().size() == 0) {
                    panelcolumn.getChildren().add(prova.buildMonth());
                }
            }

        };
    }
}
