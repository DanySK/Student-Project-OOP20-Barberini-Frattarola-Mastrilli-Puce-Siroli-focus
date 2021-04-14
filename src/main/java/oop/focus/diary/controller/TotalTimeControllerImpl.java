package oop.focus.diary.controller;

import oop.focus.common.View;
import oop.focus.diary.model.TotalTimeEvent;
import oop.focus.diary.model.TotalTimeEventImpl;
import oop.focus.diary.view.TotalTimeView;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;

/**
 * Immutable implementation of TotalTimeController.
 */
public class TotalTimeControllerImpl implements TotalTimeController {

    private final TotalTimeView totalTimeView;
    private final EventManager eventManager;
    public TotalTimeControllerImpl(final EventManager eventManager) {
        this.totalTimeView = new TotalTimeView();
        this.eventManager = eventManager;
    }

    public final void setTotalTime(final String event) {
        System.out.println(event);
        final TotalTimeEvent totalTimeEvent = new TotalTimeEventImpl(this.eventManager);
        if (totalTimeEvent.computePeriod(event).isPresent()) {
            this.totalTimeView.setLabel(LocalTime.MIDNIGHT.plus(totalTimeEvent.computePeriod(event).get()));
        } else {
            this.totalTimeView.setLabel(LocalTime.MIDNIGHT);
        }
    }

    @Override
    public final View getView() {
        return this.totalTimeView;
    }
}
