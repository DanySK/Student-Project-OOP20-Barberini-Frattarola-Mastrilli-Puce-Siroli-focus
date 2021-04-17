package oop.focus.diary.controller;

import oop.focus.common.View;
import oop.focus.diary.view.InsertTimeTimerWindow;
import org.joda.time.LocalTime;

public class InsertTimeTimerControllerImpl implements InsertTimeTimerController {
    private final CounterGeneralController controllerCounter;
    private final InsertTimeTimerWindow insertTimeTimerWindow;
    public InsertTimeTimerControllerImpl(final CounterGeneralController controllerCounter) {
        this.controllerCounter = controllerCounter;
        this.insertTimeTimerWindow = new InsertTimeTimerWindow(this);
    }

    @Override
    public final void setNewValue(final LocalTime value) {
        this.controllerCounter.setStarterValue(value);
    }
    @Override
    public final View getView() {
        return this.insertTimeTimerWindow;
    }


}
