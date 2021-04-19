package oop.focus.diary.controller;
import oop.focus.common.View;

import oop.focus.diary.view.ContainerFactoryImpl;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;

import java.util.List;

/**
 * Implementation of {@link GeneralCounterController}. This class puts in communication the
 * different Controller of counter.
 */
public class GeneralCounterControllerImpl implements GeneralCounterController {
    private final EventCounterController eventCounterController;
    private final TotalTimeController totalTimeController;
    private final CounterController counterController;
    private LocalTime localTime = LocalTime.MIDNIGHT;
    private String eventName;

    /**
     * Instantiates a new general counter controller and initializes other Controller relatives to counter.
     * @param eventManager  the event manager
     * @param isTimer   a boolean which is true if the counter is a timer, false if it is a stopwatch
     */
    public GeneralCounterControllerImpl(final EventManager eventManager, final boolean isTimer) {
        this.counterController = new CounterControllerImpl(eventManager, isTimer, this);
        this.eventCounterController = new EventCounterControllerImpl(eventManager, this);
        this.totalTimeController = new TotalTimeControllerImpl(eventManager);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void disableButton(final boolean disable) {
        this.counterController.disableButton(disable);
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public void disableChooseEvent(final boolean disable) {
        this.eventCounterController.disableChooseEvent(disable);
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void setCounterName(final String event) {
        this.eventName = event;
        this.totalTimeController.setTotalTime(event);
        this.counterController.setStarter(event, this.localTime);
        this.localTime = LocalTime.MIDNIGHT;
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void setStarterValue(final LocalTime localTime) {
        this.localTime = localTime;
        this.setCounterName(this.eventName);
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final View getView() {
        return new ContainerFactoryImpl().mergeVertically(List.of(this.eventCounterController.getView().getRoot(),
                this.totalTimeController.getView().getRoot(), this.counterController.getView().getRoot()));
    }
}
