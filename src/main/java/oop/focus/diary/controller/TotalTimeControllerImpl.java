package oop.focus.diary.controller;

import oop.focus.common.View;
import oop.focus.diary.model.TotalTimeEvent;
import oop.focus.diary.model.TotalTimeEventImpl;
import oop.focus.diary.view.TotalTimeView;
import oop.focus.diary.view.UpdatableView;
import oop.focus.event.model.EventManager;
import org.joda.time.LocalTime;

/**
 * Immutable implementation of {@link TotalTimeController}. It can be used to calculate and
 * visualize the total time spent to do an activity.
 */
public class TotalTimeControllerImpl implements TotalTimeController {

    private final UpdatableView<LocalTime> totalTimeView;
    private final EventManager eventManager;

    /**
     *  Instantiates a new total time controller and creates the associated view.
     * @param eventManager  the event manager
     */
    public TotalTimeControllerImpl(final EventManager eventManager) {
        this.totalTimeView = new TotalTimeView();
        this.eventManager = eventManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTotalTime(final String event) {
        final TotalTimeEvent totalTimeEvent = new TotalTimeEventImpl(this.eventManager);
        if (totalTimeEvent.computePeriod(event).isPresent()) {
            this.totalTimeView.updateInput(LocalTime.MIDNIGHT.plus(totalTimeEvent.computePeriod(event).get()));
        } else {
            this.totalTimeView.updateInput(LocalTime.MIDNIGHT);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.totalTimeView;
    }
}
