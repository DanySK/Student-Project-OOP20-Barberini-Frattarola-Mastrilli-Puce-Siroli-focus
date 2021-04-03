package oop.focus.homepage.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import oop.focus.homepage.controller.HomePageController;

public interface HotKeyFactory {

    Button getEventButton(String buttonName, HomePageController controller);

    CheckBox getActivityButton(String buttonNamer);

    CounterHotKeyView getCounterButton(String buttonName);
}
