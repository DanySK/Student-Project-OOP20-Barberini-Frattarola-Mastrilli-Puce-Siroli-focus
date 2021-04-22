package oop.focus.calendarhomepage.view;

import oop.focus.calendarhomepage.controller.HomePageController;

public interface HotKeyFactory {

    /**
     * This method is used to generate an activity hot key.
     * @param buttonName is the button name.
     * @param controller is the controller relative to the button.
     * @return an activity hot key.
     */
    ActivityHotKeyView getActivityButton(String buttonName, HomePageController controller);

    /**
     * This method is used to generate a counter hot key.
     * @param buttonName is the button name.
     * @param controller is the controller relative to the button.
     * @return an activity hot key.
     */
    CounterHotKeyView getCounterButton(String buttonName, HomePageController controller);

    /**
     * This method is used to generate an event hot key.
     * @param buttonName is the button name.
     * @param controller is the controller relative to the button.
     * @return an activity hot key.
     */
    EventHotKeyView getEventButton(String buttonName, HomePageController controller);
}
