package oop.focus.calendar.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarSettingsView;
import oop.focus.calendar.view.CalendarSettingsViewImpl;


public class CalendarControllerImpl implements CalendarController {


    private final CalendarSettingsView settingsview;

    private final CalendarMonthView monthview;

    private final double optionwidth;
    private final double optionheight;

    public CalendarControllerImpl(final double monthwidth, final double monthheight, final double optionwidth, final double optionheight, final double daywidth, final double dayheight) {
        this.optionwidth = optionwidth;
        this.optionheight = optionheight;
        final CalendarSettingsController settingscontroller = new CalendarSettingsControllerImpl();
        final CalendarMonthController monthcontroller = new CalendarMonthControllerImpl(settingscontroller, daywidth, dayheight);
        this.monthview = new CalendarMonthView(monthwidth, monthheight, monthcontroller);
        this.settingsview = new CalendarSettingsViewImpl(settingscontroller, monthcontroller, this.monthview);
    }

    public final HBox buildMonth() {
        final HBox test = new HBox();


        test.getChildren().add(this.buildSettingsWindows());
        test.getChildren().add(monthview.getMonthView());

        monthview.getMonthView().prefWidthProperty().bind(test.widthProperty().divide(2));
        test.setAlignment(Pos.CENTER);

        return test;
    }


    /**
     * Used for build the Settings button and his window.
     * @return settings button
     */
    private Button buildSettingsWindows() {
        final Button settings = new Button("OPZIONI");


        final Stage settingsstage = new Stage();
        settingsstage.setScene(new Scene(settingsview.getOptions(), optionwidth, optionheight));

        settings.setOnAction((e) -> {
            settingsview.setWindow(settingsstage);
            settingsstage.show();
        });
        return settings;
    }


}
