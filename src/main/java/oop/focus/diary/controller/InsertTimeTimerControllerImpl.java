package oop.focus.diary.controller;

import oop.focus.common.View;
import oop.focus.diary.view.InsertTimeTimerWindow;
import org.joda.time.LocalTime;

/**
 * Implementation of {@link InsertTimeTimerController}. It sets the value chosen by the user as starter value
 * of timer.
 */
public class InsertTimeTimerControllerImpl implements InsertTimeTimerController {
    private final GeneralCounterController controllerCounter;
    private final View insertTimeTimerWindow;

    /**
     * Instantiates a new insert time timer controller and creates the associated view.
     * @param controllerCounter the general counter controller
     */
    public InsertTimeTimerControllerImpl(final GeneralCounterController controllerCounter) {
        this.controllerCounter = controllerCounter;
        this.insertTimeTimerWindow = new InsertTimeTimerWindow(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNewValue(final LocalTime value) {
        this.controllerCounter.setStarterValue(value);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.insertTimeTimerWindow;
    }


}
