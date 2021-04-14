package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.diary.view.InsertTimeTimerWindow;
import org.joda.time.LocalTime;

public class InsertTimeTimerController implements Controller {
    private GeneralControllerCounter controllerCounter;
    private InsertTimeTimerWindow insertTimeTimerWindow;
    public InsertTimeTimerController(GeneralControllerCounter controllerCounter) {
        this.controllerCounter = controllerCounter;
        this.insertTimeTimerWindow = new InsertTimeTimerWindow(this);
    }

    public void setNewValue(LocalTime value) {
        this.controllerCounter.setStarterValue(value);
    }
    @Override
    public View getView() {
        return this.insertTimeTimerWindow;
    }


}
