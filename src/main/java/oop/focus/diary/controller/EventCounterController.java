package oop.focus.diary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.EventCounterView;
import oop.focus.homepage.model.EventManager;

public class EventCounterController implements Controller {
    private final EventCounterView view;
    private final ObservableSet<String> set;
    private final GeneralControllerCounter generalControllerCounter;
    public EventCounterController(final EventManager eventManager, final GeneralControllerCounter generalControllerCounter) {
        this.set = FXCollections.observableSet();
        eventManager.getAll().forEach(s -> this.set.add(s.getName()));
        this.view = new EventCounterView(this);
        this.generalControllerCounter = generalControllerCounter;
    }
    public final ObservableSet<String> getEventsNames() {
        return this.set;
    }
    public final void disableButton(final boolean disable) {
        this.generalControllerCounter.disableButton(disable);
    }
    public final void setChosen(final String eventChosen) {
        System.out.println(eventChosen);
        this.generalControllerCounter.setTotalTime(eventChosen);
    }

    public final void addEvent(final String newEvent) {
        this.set.add(newEvent);
        this.view.setNewValue(newEvent);
    }
    @Override
    public final View getView() {
        return this.view;
    }
}
