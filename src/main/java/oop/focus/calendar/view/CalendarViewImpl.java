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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import oop.focus.calendar.controller.CalendarControllerImpl;


public class CalendarViewImpl implements CalendarView {

    //Classes
    private final CalendarControllerImpl calendarcontroller;


    //Costants
    private static final double WIDTHBUTTONPANEL = 0.2; 
    private static final double WIDTHPANEL = 0.8;
    private static final double WIDTHBUTTON = 0.7;
    private static final double GAP = 20;


    public CalendarViewImpl(final CalendarControllerImpl calendarcontroller) {
        this.calendarcontroller = calendarcontroller;
    }

    /**
     * Used for get the calendar page.
     * @return HBox
     */
    private HBox buildCalendarPage() {

        final VBox buttoncolumn = new VBox();
        final VBox panelcolumn = new VBox();

        final HBox calendarpage = calendarcontroller.getCalendarPage();
        calendarpage.getChildren().add(buttoncolumn);
        calendarpage.getChildren().add(panelcolumn);


        configureButtonColumn(buttoncolumn);
        configurePanelColumn(panelcolumn);


        columnButton(buttoncolumn, "Mese", calendarcontroller.monthPanel(panelcolumn));
        columnButton(buttoncolumn, "Settimana", calendarcontroller.weekPanel(panelcolumn));
        columnButton(buttoncolumn, "Statistiche", calendarcontroller.statisticsPanel(panelcolumn));
        buttoncolumn.getChildren().add(calendarcontroller.buildAddEventButton());
        buttoncolumn.getChildren().add(calendarcontroller.buildSettingsWindows());



        return calendarpage;
    }

    public final void setCalendarBox() {
        calendarcontroller.setCalendarPage(buildCalendarPage());
    }

    /**
     * Used for configure the button column box.
     * @param buttoncolumn : column box to configure
     */
    private void configureButtonColumn(final VBox buttoncolumn) {
        buttoncolumn.prefWidthProperty().bind(calendarcontroller.getCalendarPage().widthProperty().multiply(WIDTHBUTTONPANEL));
        buttoncolumn.prefHeightProperty().bind(calendarcontroller.getCalendarPage().heightProperty());

        buttoncolumn.setAlignment(Pos.CENTER);
        buttoncolumn.setSpacing(GAP);

        buttoncolumn.setBackground(new Background(
                new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    /**
     * Used for configure the panel column box.
     * @param panelcolumn : column box to configure
     */
    private void configurePanelColumn(final VBox panelcolumn) {
        panelcolumn.prefWidthProperty().bind(calendarcontroller.getCalendarPage().widthProperty().multiply(WIDTHPANEL));
        panelcolumn.prefHeightProperty().bind(calendarcontroller.getCalendarPage().heightProperty());

        panelcolumn.setAlignment(Pos.CENTER);

        panelcolumn.setBackground(new Background(
                new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    /**
     * Used for create the button to put in the button column (the left one of the view).
     * @param buttoncolumn : where the button will be
     * @param string : name of the button
     * @param openthispanel : is the EventHandler that open a panel
     */
    private void columnButton(final VBox buttoncolumn, final String string, final EventHandler<ActionEvent> openthispanel) {
        final Button button = new Button(string);
        button.setPrefHeight(GAP * 2);
        button.setAlignment(Pos.CENTER);
        button.prefWidthProperty().bind(buttoncolumn.widthProperty().multiply(WIDTHBUTTON));
        buttoncolumn.getChildren().add(button);
        button.setOnAction(openthispanel);
    }



}
