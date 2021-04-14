package oop.focus.homepage.view;

import oop.focus.homepage.controller.HomePageController;


public class HotKeyFactoryImpl implements HotKeyFactory {

    @Override
    public final ActivityHotKeyView getActivityButton(final String buttonName, final HomePageController controller) {
        return new ActivityHotKeyView(buttonName, controller);
    }

    @Override
    public final CounterHotKeyView getCounterButton(final String buttonName, final HomePageController controller) {
    return new CounterHotKeyView(buttonName, controller);
    }

    @Override
    public final EventHotKeyView getEventButton(final String buttonName, final HomePageController controller) {
        return new EventHotKeyView(buttonName, controller);
    }

}
