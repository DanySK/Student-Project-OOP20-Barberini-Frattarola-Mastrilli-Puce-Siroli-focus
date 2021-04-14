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
import static java.util.Objects.nonNull;

public class CalendarViewImpl implements CalendarView {

    //Classes
    private final CalendarController calendarController;

    //View
    private HBox calendarPage;

    //Variables
    private final double settingsWidth;
    private final double settingsHeight;

    //Costants
    private static final double WIDTH_BUTTON_PANEL = 0.2; 
    private static final double WIDTH_PANEL = 0.8;
    private static final double WIDTH_BUTTON = 0.7;
    private static final double GAP = 20;
    private static final double SETTING_WIDTH = 300;
    private static final double SETTING_HEIGHT = 150;


    public CalendarViewImpl(final CalendarController calendarcontroller) {
        this.calendarController = calendarcontroller;
        this.calendarPage = new HBox();
        this.settingsWidth = SETTING_WIDTH;
        this.settingsHeight = SETTING_HEIGHT;
    }

    /**
     * Used for build the calendar page box.
     * @return HBox
     */
    private HBox buildCalendarPage() {

        final VBox buttonColumn = new VBox();
        final VBox panelColumn = new VBox();

        calendarPage.getChildren().add(buttonColumn);
        calendarPage.getChildren().add(panelColumn);


        configureButtonColumn(buttonColumn);
        configurePanelColumn(panelColumn);


        columnButton(buttonColumn, "Mese", addPanel(panelColumn, calendarController.getMonthController().getView().getRoot()));
        columnButton(buttonColumn, "Settimana", addPanel(panelColumn, calendarController.getWeekController().getView().getRoot()));
        columnButton(buttonColumn, "Persone", addPanel(panelColumn, calendarController.getPersonController().getView().getRoot()));
        columnButton(buttonColumn, "Statistiche", addPanel(panelColumn, calendarController.getStatisticsController().getView().getRoot()));
        buttonColumn.getChildren().add(buildAddEventButton());
        buttonColumn.getChildren().add(buildSettingsWindows());

        panelColumn.getChildren().add(calendarController.getMonthController().getView().getRoot());

        return calendarPage;
    }


    /**
     * Used for configure the button column box.
     * @param buttoncolumn : column box to configure
     */
    private void configureButtonColumn(final VBox buttoncolumn) {
        buttoncolumn.prefWidthProperty().bind(calendarPage.widthProperty().multiply(WIDTH_BUTTON_PANEL));
        buttoncolumn.prefHeightProperty().bind(calendarPage.heightProperty());

        buttoncolumn.setAlignment(Pos.CENTER);
        buttoncolumn.setSpacing(GAP);

    }


    /**
     * Used for configure the panel column box.
     * @param panelcolumn : column box to configure
     */
    private void configurePanelColumn(final VBox panelcolumn) {
        panelcolumn.prefWidthProperty().bind(calendarPage.widthProperty().multiply(WIDTH_PANEL));
        panelcolumn.prefHeightProperty().bind(calendarPage.heightProperty());

        panelcolumn.setAlignment(Pos.CENTER);

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
        button.prefWidthProperty().bind(buttoncolumn.widthProperty().multiply(WIDTH_BUTTON));
        buttoncolumn.getChildren().add(button);
        button.setOnAction(openthispanel);
    }

    public final void setCalendarPage() {
        this.calendarPage = buildCalendarPage();
    }


    public final Button buildSettingsWindows() {
        final Button settings = new Button("IMPOSTAZIONI");

        final Stage settingsStage = new Stage();
        settingsStage.setScene(new Scene((Parent) calendarController.getSettingsController().getView().getRoot(), settingsWidth, settingsHeight));
        calendarController.getSettingsController().setWindow(settingsStage);
        settings.setOnAction((e) -> {
            settingsStage.show();
        });
        return settings;
    }

    public final Button buildAddEventButton() {
        final Button addEvents = new Button("Aggiungi Evento");

        final Stage addeventsstage = new Stage();
        addeventsstage.setScene(new Scene((Parent) calendarController.getNewEventController().getView().getRoot()));
        addEvents.setOnAction((e) -> {
            addeventsstage.show();
        });
        return addEvents;
    }

    public final EventHandler<ActionEvent> addPanel(final VBox panelColumn, final Node root) {
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
