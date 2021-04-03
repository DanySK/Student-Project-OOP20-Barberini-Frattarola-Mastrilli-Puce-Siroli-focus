package oop.focus.homepage.controller;

import javafx.scene.Parent;
import oop.focus.homepage.view.WeekView;

public class WeekController {

    private WeekView weekView;

    public WeekController() {
        this.weekView = new WeekView(this);
    }

    public final Parent getView() {
        return this.weekView.getRoot();
    }
}
