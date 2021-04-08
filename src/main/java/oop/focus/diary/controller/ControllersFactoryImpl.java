package oop.focus.diary.controller;

public class ControllersFactoryImpl implements ControllersFactory {
    @Override
    public final CounterControllerImpl createTimer() {
        return new CounterControllerImpl(UseEventManager.getEventManager(), true);
    }

    @Override
    public final CounterControllerImpl createStopwatch() {
        return new CounterControllerImpl(UseEventManager.getEventManager(), false);
    }
}
