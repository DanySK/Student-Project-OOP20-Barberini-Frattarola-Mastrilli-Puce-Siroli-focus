package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarControllerImpl;


public class CalendarViewImpl implements CalendarView {

    //Classes
    private final CalendarControllerImpl calendarcontroller;

    //View
    private HBox calendarpage;

    //Variables
    private final double optionwidth;
    private final double optionheight;

    //Costants
    private static final double WIDTHBUTTONPANEL = 0.2; 
    private static final double WIDTHPANEL = 0.8;
    private static final double WIDTHBUTTON = 0.7;
    private static final double GAP = 20;
    private static final double OPTIONWIDTH = 300;
    private static final double OPTIONHEIGHT = 150;


    public CalendarViewImpl(final CalendarControllerImpl calendarcontroller) {
        this.calendarcontroller = calendarcontroller;
        this.calendarpage = new HBox();
        this.optionwidth = OPTIONWIDTH;
        this.optionheight = OPTIONHEIGHT;
    }

    /**
     * Used for get the calendar page.
     * @return HBox
     */
    private HBox buildCalendarPage() {

        final VBox buttoncolumn = new VBox();
        final VBox panelcolumn = new VBox();

        calendarpage.getChildren().add(buttoncolumn);
        calendarpage.getChildren().add(panelcolumn);


        configureButtonColumn(buttoncolumn);
        configurePanelColumn(panelcolumn);


        columnButton(buttoncolumn, "Mese", monthPanel(panelcolumn));
        columnButton(buttoncolumn, "Settimana", weekPanel(panelcolumn));
        columnButton(buttoncolumn, "Persone", personPanel(panelcolumn));
        columnButton(buttoncolumn, "Statistiche", statisticsPanel(panelcolumn));
        buttoncolumn.getChildren().add(buildAddEventButton());
        buttoncolumn.getChildren().add(buildSettingsWindows());



        return calendarpage;
    }


    /**
     * Used for configure the button column box.
     * @param buttoncolumn : column box to configure
     */
    private void configureButtonColumn(final VBox buttoncolumn) {
        buttoncolumn.prefWidthProperty().bind(calendarpage.widthProperty().multiply(WIDTHBUTTONPANEL));
        buttoncolumn.prefHeightProperty().bind(calendarpage.heightProperty());

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
        panelcolumn.prefWidthProperty().bind(calendarpage.widthProperty().multiply(WIDTHPANEL));
        panelcolumn.prefHeightProperty().bind(calendarpage.heightProperty());

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

    public final void setCalendarPage() {
        this.calendarpage = buildCalendarPage();
    }


    public final Button buildSettingsWindows() {
        final Button settings = new Button("IMPOSTAZIONI");

        final Stage settingsstage = new Stage();
        settingsstage.setScene(new Scene((Parent) calendarcontroller.getSettingsController().getView().getRoot(), optionwidth, optionheight));
        calendarcontroller.getSettingsController().setWindow(settingsstage);
        settings.setOnAction((e) -> {
            settingsstage.show();
        });
        return settings;
    }

    public final Button buildAddEventButton() {
        final Button addevents = new Button("Aggiungi Evento");

        final Stage addeventsstage = new Stage();
        //addeventsstage.setScene(new Scene());
        addevents.setOnAction((e) -> {
            addeventsstage.show();
        });
        return addevents;
    }

    public final EventHandler<ActionEvent> monthPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);
                    panelcolumn.getChildren().add(calendarcontroller.getMonthController().getView().getRoot());
                } else {
                    panelcolumn.getChildren().add(calendarcontroller.getMonthController().getView().getRoot());
                }
            }

        };
    }

    public final EventHandler<ActionEvent> weekPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);

                } else {

                }
            }

        };
    }

    public final EventHandler<ActionEvent> personPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);

                } else {

                }
            }

        };
    }

    public final EventHandler<ActionEvent> statisticsPanel(final VBox panelcolumn) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (panelcolumn.getChildren().size() != 0) {
                    panelcolumn.getChildren().remove(0);

                } else {

                }
            }

        };
    }

    public final Node getRoot() {
        return this.calendarpage;
    }

}
