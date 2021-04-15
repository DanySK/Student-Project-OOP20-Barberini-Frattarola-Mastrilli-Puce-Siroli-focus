package oop.focus.diary.controller;
import oop.focus.common.View;
import oop.focus.diary.model.CounterManager;
import oop.focus.diary.model.CounterManagerImpl;
import oop.focus.diary.view.CreateBoxFactoryImpl;
import oop.focus.diary.view.StartStopView;
import oop.focus.diary.view.TimerButtons;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import java.util.List;

public class CounterControllerImpl implements CounterController {
    private final CounterManager counterManager;
    private final StartStopView view;
    private TimerButtons timerView;
    private final boolean isTimer;
    public CounterControllerImpl(final EventManager me, final boolean isTimer, final CounterGeneralControllerImpl controllerCounter) {
        this.isTimer = isTimer;
        this.counterManager = new CounterManagerImpl(me,  isTimer);
        this.view = new StartStopView(this, controllerCounter);
        if (isTimer) {
            this.timerView = new TimerButtons(controllerCounter);
        }
    }
    public final void disableButton(final boolean disable) {
        this.view.disableButton(disable);
    }
    @Override
    public final void setStarter(final String event, final LocalTime localTime) {
        this.counterManager.createCounter(event);
        this.counterManager.setStarterValue(new Period(LocalTime.MIDNIGHT, localTime).toStandardSeconds().getSeconds());
        this.view.updateValue(localTime);
        this.counterManager.setChangeListener(s -> {
            this.view.updateValue(LocalTime.MIDNIGHT.plusSeconds(s));
        });
        this.counterManager.setFinishListener(s -> {
            if (s.equals(0)) {
                this.view.updateValue(LocalTime.MIDNIGHT.plusSeconds(s));
            }
        });
    }

    @Override
    public final void stopSound() {
        if (this.isPlaying()) {
            System.out.println(this.isPlaying());
            this.counterManager.stopSound();
        }
    }
    @Override
    public final void startTimer() {
        this.counterManager.startCounter();
    }
    @Override
    public final void stopTimer() {
        this.counterManager.stopCounter();
    }
    /**
     * The method can be used to check if counter's alarm is started.
     * @return  true if alarm is playing, false otherwise
     */
    private boolean isPlaying() {
        return this.counterManager.isPlaying();
    }

    @Override
    public final View getView() {
        if (!this.isTimer) {
            return this.view;
        } else {
            return new CreateBoxFactoryImpl().createVBox(List.of(this.timerView.getRoot(), this.view.getRoot()));
        }
    }
}
