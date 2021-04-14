package oop.focus.homepage.view;

import oop.focus.homepage.controller.HomePageController;

public interface HotKeyFactory {

    ActivityHotKeyView getActivityButton(String buttonNamer, HomePageController controller);

    CounterHotKeyView getCounterButton(String buttonName, HomePageController controller);

    EventHotKeyView getEventButton(String buttonName, HomePageController controller);
}
