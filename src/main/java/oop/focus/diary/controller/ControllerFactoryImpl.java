package oop.focus.diary.controller;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

public class ControllerFactoryImpl implements ControllersFactory {
    private final EventManager em = new EventManagerImpl(new DataSourceImpl());
    @Override
    public final CounterControllerImpl createTimer() {
        return new CounterControllerImpl(this.em, true);
    }

    @Override
    public final CounterControllerImpl createStopwatch() {
        return new CounterControllerImpl(this.em, false);
    }

    @Override
    public final TotalTimeControllerImpl createCounterController() {
        return new TotalTimeControllerImpl(this.em);
    }
}
