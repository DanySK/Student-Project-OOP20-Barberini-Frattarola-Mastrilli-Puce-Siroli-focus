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
    private final CalendarControllerImpl prova = new CalendarControllerImpl(300, 150, 200, 400);

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

        columnButton(buttoncolumn, "Mese", prova.monthPanel(panelcolumn));
        columnButton(buttoncolumn, "Settimana", prova.weekPanel(panelcolumn));
        buttoncolumn.getChildren().add(prova.buildSettingsWindows());

        calendarpage.getChildren().add(buttoncolumn);
        calendarpage.getChildren().add(panelcolumn);

        calendarpage.prefWidthProperty().bind(this.generalview.widthProperty());
        calendarpage.prefHeightProperty().bind(this.generalview.heightProperty());

        configureButtonColumn(buttoncolumn);
        configurePanelColumn(panelcolumn);


        return calendarpage;
    }


    /**
     * Used for configure the button column box.
     * @param buttoncolumn : column box to configure
     */
    private void configureButtonColumn(final VBox buttoncolumn) {
        buttoncolumn.prefWidthProperty().bind(this.calendarpage.widthProperty().multiply(WIDTHBUTTONPANEL));
        buttoncolumn.prefHeightProperty().bind(this.generalview.heightProperty());

        buttoncolumn.setAlignment(Pos.CENTER);
        buttoncolumn.setSpacing(GAP);

        buttoncolumn.setBackground(new Background(
                new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    /**
     * Used for configure the panel column box.
     * @param panelcolumn : column box to configure
     */
    private void configurePanelColumn(final VBox panelcolumn) {
        panelcolumn.prefWidthProperty().bind(this.calendarpage.widthProperty().multiply(WIDTHPANEL));
        panelcolumn.prefHeightProperty().bind(this.generalview.heightProperty());

        panelcolumn.setAlignment(Pos.CENTER);

        panelcolumn.setBackground(new Background(
                new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    /**
     * Used for create the button to put in the button column (the left one of the view).
     * @param buttoncolumn : where the button will be
     * @param string : name of the button
     * @param openthispanel : is the EventHandler that open a panel
     */
    private void columnButton(final VBox buttoncolumn, final String string, final EventHandler<ActionEvent> openthispanel) {
        final Button button = new Button(string);
        button.prefWidthProperty().bind(buttoncolumn.widthProperty().multiply(WIDTHBUTTON));
        buttoncolumn.getChildren().add(button);
        button.setOnAction(openthispanel);
    }



}
