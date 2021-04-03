package oop.focus.homepage.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import oop.focus.homepage.controller.HomePageController;

import java.io.IOException;

public class HotKeyFactoryImpl implements HotKeyFactory {

    @Override
    public final Button getEventButton(final String buttonName, final HomePageController controller) {
        return new Button(buttonName);
    }

    @Override
    public final CheckBox getActivityButton(final String buttonName) {
        final CheckBox activity = new CheckBox(buttonName);
        activity.setOnAction(event -> {
            if (!activity.isSelected()) {
                activity.setSelected(true);
            }
        });
        return activity;
    }

    @Override
    public final CounterHotKeyView getCounterButton(final String buttonName) {
    final CounterHotKeyView counter = new CounterHotKeyView(buttonName);
    counter.getButton().setOnAction(event -> {
        int count = Integer.valueOf(counter.getLabelValue());
        count++;
        counter.setLabelText(String.valueOf(count));
    });
    return counter;
    }

}
