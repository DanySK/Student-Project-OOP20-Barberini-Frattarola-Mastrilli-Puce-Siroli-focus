package oop.focus.diary.controller;

import javafx.collections.FXCollections;

import javafx.collections.ObservableSet;
import oop.focus.diary.model.TotalTimeEvent;
import oop.focus.diary.model.TotalTimeEventImpl;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;


public class TotalTimeControllerImpl implements TotalTimeController {
    private final EventManager eventManager;
    private final ObservableSet<String> set = FXCollections.observableSet();
    public TotalTimeControllerImpl(final EventManager em) {
        this.eventManager = em;
        this.eventManager.getAll().forEach(s -> this.set.add(s.getName()));
    }
    @Override
    public final ObservableSet<String> getAllEvents() {
        return this.set;
    }
    @Override
    public final void addValue(final String text) {
        this.set.add(text);
    }
    @Override
    public final LocalTime getTotalTime(final String event) {
        System.out.println(event);
        final TotalTimeEvent totalTimeEvent = new TotalTimeEventImpl(this.eventManager);
        if (totalTimeEvent.computePeriod(event).isPresent()) {
            return LocalTime.MIDNIGHT.plus(totalTimeEvent.computePeriod(event).get());
        }
        return LocalTime.MIDNIGHT;
    }
}
