package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarMonthController;
import oop.focus.calendar.controller.CalendarOptionsController;
import oop.focus.calendar.view.HoursViewImpl.Format;

public class CalendarOptionsViewImpl implements CalendarOptionsView {


    private final CalendarOptionsController optioncontroller;
    private final CalendarMonthController monthcontroller;
    private final CalendarMonthView monthview;

    private VBox options = new VBox();

    private Format hoursformat;
    private Stage optionwindows;

    public CalendarOptionsViewImpl(final CalendarOptionsController controller, final CalendarMonthController monthcontroller, final CalendarMonthView monthview) {
        this.optioncontroller = controller;
        this.monthcontroller = monthcontroller;
        this.monthview = monthview;
    }



    private VBox buildOptionsView() {

        final VBox container = new VBox();

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

    private EventHandler<ActionEvent> saveOnAction(final TextField spacing) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                 if (!optioncontroller.checkSpacing(spacing.getText())) {
                     System.out.println("ERRORE: inserire dei numeri");
                     spacing.setText(String.valueOf(optioncontroller.getSpacing()));
                 }
                 if (hoursformat == null) {
                     hoursformat = Format.NORMAL;
                 }
                 optioncontroller.setFormat(hoursformat);
                 monthcontroller.updateView(monthview, monthcontroller);
                 optionwindows.close();
           }

        };
    }

    private void buildSpacingRow(final GridPane settings, final Button save) {
        final Label spacinglabel = new Label("spaziatura ore");

        final TextField spacing = new TextField();

        settings.add(spacinglabel, 0, 0);
        settings.add(spacing, 1, 0);

        save.setOnAction(this.saveOnAction(spacing));
    }
 
    private void buildFormatRow(final GridPane settings) {
        final Label formatlabel = new Label("formato ore");

        final ComboBox<Format> format = new ComboBox<>();

        final Format normal = Format.NORMAL;
        final Format extended = Format.EXTENDED;

        format.getItems().add(normal);
        format.getItems().add(extended);

        format.setOnAction((e) -> {
                this.hoursformat = format.getValue();
        });


        settings.add(formatlabel, 0, 1);
        settings.add(format, 1, 1);
    }

    private void configureSettingsGrid(final GridPane settings) {
        settings.setAlignment(Pos.CENTER);
        settings.setVgap(10);
        settings.setHgap(10);
    }


    public final VBox getOptions() {
        if (this.options.getChildren().size() == 0) {
            this.options = buildOptionsView();
        }
        return this.options;
    }


    public final void setWindow(final Stage stage) {
        this.optionwindows = stage;
    }

}
