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
import oop.focus.calendar.controller.CalendarOptionsControllerImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;

public class CalendarOptionsViewImpl implements CalendarOptionsView {


    private final CalendarOptionsControllerImpl controller = new CalendarOptionsControllerImpl();

    private VBox options = new VBox();

    private Format temp;
    private Stage optionwindows;

    private VBox buildOptionsView() {

        final VBox container = new VBox();

        final GridPane settings = new GridPane();

        final Label spacinglabel = new Label("spaziatura ore");

        final TextField spacing = new TextField();

        settings.add(spacinglabel, 0, 0);
        settings.add(spacing, 1, 0);

        final Label formatlabel = new Label("formato ore");

        final ComboBox<Format> format = new ComboBox<>();

        final Format normal = Format.NORMAL;
        final Format extended = Format.EXTENDED;

        format.getItems().add(normal);
        format.getItems().add(extended);

        format.setOnAction((e) -> {
            this.temp = format.getValue();
        });

        settings.add(formatlabel, 0, 1);
        settings.add(format, 1, 1);

        final Button save = new Button("salva");

        save.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(final ActionEvent event) {
                controller.setSpacing(spacing.getText());
                controller.setFormat(temp);
                optionwindows.close();
            }
        });


        settings.setAlignment(Pos.CENTER);
        settings.setVgap(10);
        settings.setHgap(10);

        container.getChildren().add(settings);

        container.getChildren().add(save);

        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);

        return container;
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
