package oop.focus.diary.controller;
import oop.focus.common.View;
import oop.focus.diary.model.CounterManager;
import oop.focus.diary.model.CounterManagerImpl;
import oop.focus.diary.view.ContainerFactoryImpl;
import oop.focus.diary.view.StartStopCounterView;
import oop.focus.diary.view.TimerButtons;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import java.util.List;

/**
 * Implementation of {@link CounterController}. It manages counters, setting starter value and starting
 * or stopping them.
 */
public class CounterControllerImpl implements CounterController {
    private final CounterManager counterManager;
    private final StartStopCounterView view;
    private TimerButtons timerView;
    private final boolean isTimer;
    private final GeneralCounterController generalController;

    /**
     * Instantiates a new counter controller and creates the associated view.
     * @param eventManager  the event manager
     * @param isTimer   a boolean which is true if the controller is a timer, false if it is a stopwatch
     * @param controllerCounter the general controller of counter's sections
     */
    public CounterControllerImpl(final EventManager eventManager, final boolean isTimer, final GeneralCounterController controllerCounter) {
        this.isTimer = isTimer;
        this.generalController = controllerCounter;
        this.counterManager = new CounterManagerImpl(eventManager,  isTimer);
        this.view = new StartStopCounterView(this, controllerCounter);
        if (isTimer) {
            this.timerView = new TimerButtons(controllerCounter);
        }
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void disableButton(final boolean disable) {
        this.view.disableComponent(disable);
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void setStarter(final String event, final LocalTime localTime) {
        this.counterManager.createCounter(event);
        this.counterManager.setStarterValue(new Period(LocalTime.MIDNIGHT, localTime).toStandardSeconds().getSeconds());
        this.view.updateInput(localTime);
        this.counterManager.setChangeListener(s -> {
            this.view.updateInput(LocalTime.MIDNIGHT.plusSeconds(s));
        });
        this.counterManager.setFinishListener(s -> {
            if (s.equals(0)) {
                this.view.updateInput(LocalTime.MIDNIGHT.plusSeconds(s));
            }
        });
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void stopSound() {
        this.generalController.disableChooseEvent(true);
        if (this.isTimer) {
            this.disableTimerButtons(false);
        }
        this.counterManager.stopSound();
    }

    /**
     * Sets if timer's buttons(those that allow the selection of time in timer's section) are disable or enabled.
     * @param disable   a boolean which is true if buttons must be disabled, false otherwise.
     */
    private void disableTimerButtons(final boolean disable) {
        this.timerView.disableComponent(disable);
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void startTimer() {
        this.generalController.disableChooseEvent(true);
        if (this.isTimer) {
            this.disableTimerButtons(true);
        }
        this.counterManager.startCounter();
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void stopTimer() {
        this.generalController.disableChooseEvent(true);
        if (this.isTimer) {
            this.disableTimerButtons(false);
        }
        this.counterManager.stopCounter();
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final View getView() {
        if (!this.isTimer) {
            return this.view;
        } else {
            return new ContainerFactoryImpl().mergeVertically(List.of(this.timerView.getRoot(), this.view.getRoot()));
        }
    }
}
