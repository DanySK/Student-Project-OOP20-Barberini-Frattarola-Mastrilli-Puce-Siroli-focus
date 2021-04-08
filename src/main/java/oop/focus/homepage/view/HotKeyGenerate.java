package oop.focus.homepage.view;

import javafx.scene.Parent;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;

public class HotKeyGenerate {

    private final HotKeyFactory factory;
    private final HomePageController controller;

    public HotKeyGenerate(final HomePageController controller) {
        this.factory = new HotKeyFactoryImpl();
        this.controller = controller;
    }

    public final Parent createButton(final HotKey hotKey) {
        switch (hotKey.getType()) {
        case EVENT :
            return this.factory.getEventButton(hotKey.getName(), this.controller);
        case COUNTER :
            return this.factory.getCounterButton(hotKey.getName(), this.controller);
        default:
            return this.factory.getActivityButton(hotKey.getName(), this.controller);
        }
    }

}
