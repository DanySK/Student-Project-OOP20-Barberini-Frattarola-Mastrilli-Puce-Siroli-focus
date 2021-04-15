package oop.focus.diary.controller;
import oop.focus.common.View;

import oop.focus.diary.view.CreateHBox;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;

import java.util.List;

public class CounterGeneralControllerImpl implements CounterGeneralController {
    private final EventCounterController eventCounterController;
    private final TotalTimeController totalTimeController;
    private final CounterController counterController;
    private LocalTime localTime = LocalTime.MIDNIGHT;
    private String eventName;
    public CounterGeneralControllerImpl(final EventManager eventManager, final boolean isTimer) {
        this.counterController = new CounterControllerImpl(eventManager, isTimer, this);
        this.eventCounterController = new EventCounterController(eventManager, this);
        this.totalTimeController = new TotalTimeControllerImpl(eventManager);
    }
    @Override
    public final void disableButton(final boolean disable) {
        this.counterController.disableButton(disable);
    }
    @Override
    public final void setCounterName(final String event) {
        this.eventName = event;
        this.totalTimeController.setTotalTime(event);
        this.counterController.setStarter(event, this.localTime);
        this.localTime = LocalTime.MIDNIGHT;
    }
    @Override
    public final void setStarterValue(final LocalTime localTime) {
        this.localTime = localTime;
        this.setCounterName(this.eventName);
    }
    @Override
    public final View getView() {
        return new CreateHBox().createVBox(List.of(new CreateHBox().createHBox(List.of(this.eventCounterController.getView().getRoot(),
                this.totalTimeController.getView().getRoot())).getRoot(), this.counterController.getView().getRoot()));
    }
}
