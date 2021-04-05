package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import oop.focus.calendar.controller.CalendarControllerImpl;


public class CalendarViewImpl implements CalendarView {

    //Classes
    private final CalendarControllerImpl prova = new CalendarControllerImpl(400, 400, 300, 150, 200, 400);

    //Views
    private final HBox calendarpage;
    private final Pane generalview;

    //Costants
    private static final double WIDTHBUTTONPANEL = 0.2; 
    private static final double WIDTHPANEL = 0.8;
    private static final double WIDTHBUTTON = 0.7;
    private static final double GAP = 20;


    public CalendarViewImpl(final Pane generalview) {
        this.calendarpage = new HBox();
        this.generalview = generalview;
    }

    public final HBox buildCalendarPage() {

        final VBox buttoncolumn = new VBox();
        final VBox panelcolumn = new VBox();
        columnButton(buttoncolumn, panelcolumn, "Mese");
        buttoncolumn.getChildren().add(prova.buildSettingsWindows());

        calendarpage.getChildren().add(buttoncolumn);
        calendarpage.getChildren().add(panelcolumn);

        buttoncolumn.setSpacing(GAP);

        buttoncolumn.setAlignment(Pos.CENTER);
        buttoncolumn.setBackground(new Background(
                new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        panelcolumn.setAlignment(Pos.CENTER);
        panelcolumn.setBackground(new Background(
                new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));

        calendarpage.prefWidthProperty().bind(this.generalview.widthProperty());
        calendarpage.prefHeightProperty().bind(this.generalview.heightProperty());
        buttoncolumn.prefWidthProperty().bind(this.calendarpage.widthProperty().multiply(WIDTHBUTTONPANEL));
        buttoncolumn.prefHeightProperty().bind(this.generalview.heightProperty());
        panelcolumn.prefWidthProperty().bind(this.calendarpage.widthProperty().multiply(WIDTHPANEL));
        panelcolumn.prefHeightProperty().bind(this.generalview.heightProperty());
        return calendarpage;
    }



    private void columnButton(final VBox buttoncolumn, final VBox panelcolumn, final String string) {
        final Button button = new Button(string);
        button.prefWidthProperty().bind(buttoncolumn.widthProperty().multiply(WIDTHBUTTON));
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
