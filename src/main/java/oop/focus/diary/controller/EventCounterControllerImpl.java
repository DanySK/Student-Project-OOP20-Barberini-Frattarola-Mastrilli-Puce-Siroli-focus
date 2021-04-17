package oop.focus.diary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.EventCounterView;
import oop.focus.homepage.model.EventManager;

public class EventCounterControllerImpl implements Controller, EventCounterController {
    private final EventCounterView view;
    private final ObservableSet<String> set;
    private final CounterGeneralControllerImpl generalControllerCounter;
    public EventCounterControllerImpl(final EventManager eventManager, final CounterGeneralControllerImpl generalControllerCounter) {
        this.set = FXCollections.observableSet();
        eventManager.getAll().forEach(s -> this.set.add(s.getName()));
        this.view = new EventCounterView(this);
        this.generalControllerCounter = generalControllerCounter;
    }
    @Override
    public final ObservableSet<String> getEventsNames() {
        return this.set;
    }
    @Override
    public final void disableButton(final boolean disable) {
        this.generalControllerCounter.disableButton(disable);
    }
    public void disableChooseEvent(boolean disable) {
        this.view.disableChooseEvent(disable);
    }
    @Override
    public final void setChosen(final String eventChosen) {
        this.generalControllerCounter.setCounterName(eventChosen);
    }
    @Override
    public final void addEvent(final String newEvent) {
        this.set.add(newEvent);
        this.view.setNewValue(newEvent);
    }
    @Override
    public final View getView() {
        return this.view;
    }
}
