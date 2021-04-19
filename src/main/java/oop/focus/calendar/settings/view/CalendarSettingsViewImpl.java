package oop.focus.calendar.settings.view;

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
import oop.focus.calendar.model.Format;
import oop.focus.calendar.settings.controller.CalendarSettingsController;

/**
 * Implementation of {@link CalendarSettingsView}.
 */
public class CalendarSettingsViewImpl implements CalendarSettingsView {

    //Classes
    private final CalendarSettingsController settingsController;

    //View
    private Stage settingsWindows;
    private final VBox settingsBox;


    public CalendarSettingsViewImpl(final CalendarSettingsController controller) {
        this.settingsController = controller;
        this.settingsBox = buildSettingsView();
    }


    /**
     * Used for build the settings view.
     * @return VBox
     */
    private VBox buildSettingsView() {

        final VBox container = new VBox();

        container.setBackground(new Background(
                new BackgroundFill(Color.valueOf("ffcccc"), CornerRadii.EMPTY, Insets.EMPTY)));

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
        final Label spacingLabel = new Label("spazio tra le ore");

        final TextField spacing = new TextField();

        settings.add(spacingLabel, 0, 0);
        settings.add(spacing, 1, 0);

        save.setOnAction(saveOnAction(spacing));

    }
 

    /**
     * Used for build the Format row.
     * @param settings : is the place where the row will be put
     */
    private void buildFormatRow(final GridPane settings) {
        final Label formatLabel = new Label("formato ore");

        final ComboBox<String> format = new ComboBox<>();

        final Format normal = Format.NORMAL;
        final Format extended = Format.EXTENDED;

        format.getItems().add(normal.getName());
        format.getItems().add(extended.getName());

        format.setOnAction((e) -> {
            settingsController.setFormat(format.getValue().equals(Format.NORMAL.getName()) ? Format.NORMAL : Format.EXTENDED);
        });


        settings.add(formatLabel, 0, 1);
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

    /**
     * Get the EventHandler of the save button.
     * @param spacing : TextField where is written the spacing to save
     * @return EventHandler
     */
    private EventHandler<ActionEvent> saveOnAction(final TextField spacing) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                 if (!settingsController.checkSpacing(spacing.getText())) {
                     spacing.setText(String.valueOf(settingsController.getSpacing()));
                 }
                 if (settingsController.getFormat() == null) {
                     settingsController.setFormat(Format.NORMAL);
                 }
                 settingsController.updateView();
                 settingsWindows.close();
           }

        };
    }

    /**
     * {@inheritDoc}
     */
    public final void setWindow(final Stage stage) {
        this.settingsWindows = stage;
    }

    /**
     * {@inheritDoc}
     */
    public final void windowsError(final String string) {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Attenzione");
        alert.setContentText(string);
        alert.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    public final Node getRoot() {
        return this.settingsBox;
    }

}
