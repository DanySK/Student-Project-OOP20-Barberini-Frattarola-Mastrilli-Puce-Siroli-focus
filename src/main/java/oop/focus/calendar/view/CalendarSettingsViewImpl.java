package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarSettingsController;
import oop.focus.calendar.model.Format;


public class CalendarSettingsViewImpl implements CalendarSettingsView {

    //Classes
    private final CalendarSettingsController settingscontroller;

    //View
    private Stage settingswindows;
    private final VBox settingsbox;

    public CalendarSettingsViewImpl(final CalendarSettingsController controller) {
        this.settingscontroller = controller;
        this.settingsbox = buildSettingsView();
    }


    /**
     * Used for build the settings view.
     * @return vbox
     */
    private VBox buildSettingsView() {

        final VBox container = new VBox();

        container.setBackground(new Background(
                new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        final GridPane settings = new GridPane();

        final Button save = new Button("salva");

        this.buildSpacingRow(settings, save);

        this.buildFormatRow(settings);

        this.configureSettingsGrid(settings);


        container.getChildren().add(settings);
        container.getChildren().add(save);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);

        return container;
    }



    /**
     * Used for build the Spacing row.
     * @param settings : is the place where the row will be put
     * @param save : is the button that will save the changes
     */
    private void buildSpacingRow(final GridPane settings, final Button save) {
        final Label spacinglabel = new Label("spazio tra le ore");

        final TextField spacing = new TextField();

        settings.add(spacinglabel, 0, 0);
        settings.add(spacing, 1, 0);

        save.setOnAction(saveOnAction(spacing));

    }
 

    /**
     * Used for build the Format row.
     * @param settings : is the place where the row will be put
     */
    private void buildFormatRow(final GridPane settings) {
        final Label formatlabel = new Label("formato ore");

        final ComboBox<Format> format = new ComboBox<>();

        final Format normal = Format.NORMAL;
        final Format extended = Format.EXTENDED;

        format.getItems().add(normal);
        format.getItems().add(extended);

        format.setOnAction((e) -> {
            settingscontroller.setFormat(format.getValue());
        });


        settings.add(formatlabel, 0, 1);
        settings.add(format, 1, 1);
    }

    /**
     * Used for configure the settings grid.
     * @param settings : is the grid to be setting up 
     */
    private void configureSettingsGrid(final GridPane settings) {
        settings.setAlignment(Pos.CENTER);
        settings.setVgap(10);
        settings.setHgap(10);
    }

    public final EventHandler<ActionEvent> saveOnAction(final TextField spacing) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                 if (!settingscontroller.checkSpacing(spacing.getText())) {
                     spacing.setText(String.valueOf(settingscontroller.getSpacing()));
                 }
                 if (settingscontroller.getFormat() == null) {
                     settingscontroller.setFormat(Format.NORMAL);
                 }
                 settingscontroller.updateView();
                 settingswindows.close();
           }

        };
    }

    public final void setWindow(final Stage stage) {
        this.settingswindows = stage;
    }

    public final void windowsError(final String string) {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Attenzione");
        alert.setContentText(string);
        alert.showAndWait();
    }

    public final Node getRoot() {
        return this.settingsbox;
    }

}
