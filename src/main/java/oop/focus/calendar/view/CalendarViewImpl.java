package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarController;
import oop.focus.common.View;

import static java.util.Objects.nonNull;

public class CalendarViewImpl implements View {

    //Classes
    private final CalendarController calendarController;

    //View
    private final HBox calendarPage;

    //Costants
    private static final double WIDTH_BUTTON_PANEL = 0.2; 
    private static final double WIDTH_PANEL = 0.8;
    private static final double WIDTH_BUTTON = 0.7;
    private static final double GAP = 20;
    private static final double SETTING_WIDTH = 300;
    private static final double SETTING_HEIGHT = 150;
    private static final double EVENT_WIDTH = 700;
    private static final double EVENT_HEIGHT = 600;


    public CalendarViewImpl(final CalendarController calendarController) {
        this.calendarController = calendarController;
        this.calendarPage = new HBox();
        buildCalendarPage();
    }

    /**
     * Used for build the calendar page box.
     * @return HBox
     */
    private void buildCalendarPage() {

        final VBox buttonColumn = new VBox();
        final VBox panelColumn = new VBox();

        calendarPage.getChildren().add(buttonColumn);
        calendarPage.getChildren().add(panelColumn);


        configureColumn(buttonColumn, WIDTH_BUTTON_PANEL);
        configureColumn(panelColumn, WIDTH_PANEL);


        columnButton(buttonColumn, "Mese", addPanel(panelColumn, calendarController.getMonthController().getView().getRoot()));
        columnButton(buttonColumn, "Settimana", addPanel(panelColumn, calendarController.getWeekController().getView().getRoot()));
        columnButton(buttonColumn, "Persone", addPanel(panelColumn, calendarController.getPersonController().getView().getRoot()));
        columnButton(buttonColumn, "Statistiche", addPanel(panelColumn, calendarController.getStatisticsController().getView().getRoot()));
        buildButtonWindows(buttonColumn, "Impostazioni", calendarController.getSettingsController().getView(), SETTING_WIDTH, SETTING_HEIGHT);
        buildButtonWindows(buttonColumn, "Aggiungi Evento", calendarController.getNewEventController().getView(), EVENT_WIDTH, EVENT_HEIGHT);
        buildButtonWindows(buttonColumn, "Info Eventi", calendarController.getEventInfoController().getView(), EVENT_WIDTH, EVENT_HEIGHT);
        panelColumn.getChildren().add(calendarController.getMonthController().getView().getRoot());

    }


    /**
     * Used for configure the columns box.
     * @param column : column box to configure
     */
    private void configureColumn(final VBox column, final double width) {
        column.prefWidthProperty().bind(calendarPage.widthProperty().multiply(width));
        column.prefHeightProperty().bind(calendarPage.heightProperty());
        column.setAlignment(Pos.CENTER);
        if (width == WIDTH_BUTTON_PANEL) {
        column.setSpacing(GAP);
        }

    }


    /**
     * Used for create the button to put in the button column (the left one of the view).
     * @param buttonColumn : where the button will be
     * @param string : name of the button
     * @param openThisPanel : is the EventHandler that open a panel
     */
    private void columnButton(final VBox buttonColumn, final String string, final EventHandler<ActionEvent> openThisPanel) {
        final Button button = new Button(string);
        button.getStyleClass().add("lateral-button");
        button.setPrefHeight(GAP * 2);
        button.setAlignment(Pos.CENTER);
        button.prefWidthProperty().bind(buttonColumn.widthProperty().multiply(WIDTH_BUTTON));
        buttonColumn.getChildren().add(button);
        button.setOnAction(openThisPanel);
    }

    /**
     * Used for build the button for open window.
     * @param buttonColumn : column where the button will be
     * @param name : String with the name of the button
     * @param view : controller of the windows to open
     * @param widht : width of the window
     * @param height : height of the window
     */

    private void buildButtonWindows(final VBox buttonColumn, final String name, final View view, final double width, final double height) {
        final Button button = new Button(name);
        button.getStyleClass().add("lateral-button");
        button.prefWidthProperty().bind(buttonColumn.widthProperty().multiply(WIDTH_BUTTON));
        button.setPrefHeight(GAP * 2);
        button.setAlignment(Pos.CENTER);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot(), width, height));
        if ("IMPOSTAZIONI".equalsIgnoreCase(name)) {
        calendarController.getSettingsController().setWindow(stage);
        }
        button.setOnAction((e) -> {
            stage.show();
        });
        buttonColumn.getChildren().add(button);
    }

    /**
     * Used for show the panel of the button that we have clicked.
     * @param panelColumn : column where we put the panel
     * @param root : root of the panel
     * @return EventHandler
     */
    private EventHandler<ActionEvent> addPanel(final VBox panelColumn, final Node root) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (nonNull(panelColumn.getChildren())) {
                    panelColumn.getChildren().remove(0);
                    panelColumn.getChildren().add(root);
                } else {
                    panelColumn.getChildren().add(root);
                }
            }

        };
    }


    public final Node getRoot() {
        return this.calendarPage;
    }

}
