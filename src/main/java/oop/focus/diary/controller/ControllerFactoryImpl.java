package oop.focus.diary.controller;

public class ControllerFactoryImpl implements ControllersFactory {

    @Override
    public final CounterControllerImpl createTimer() {
        return new CounterControllerImpl(UseEventManager.getEventManager(), true);
    }

    @Override
    public final CounterControllerImpl createStopwatch() {
        return new CounterControllerImpl(UseEventManager.getEventManager(), false);
    }

    @Override
    public final TotalTimeControllerImpl createCounterController() {
        return new TotalTimeControllerImpl(UseEventManager.getEventManager());
    }
}
