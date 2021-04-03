package oop.focus.diary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.diary.model.TotalTimeEvent;
import oop.focus.diary.model.TotalTimeEventImpl;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;

public class TotalTimeControllerImpl implements TotalTimeController {
    private final EventManager eventManager;
    private final TotalTimeEvent totalTimeEvent;
    public TotalTimeControllerImpl(final EventManager em) {
        this.eventManager = em;
        this.totalTimeEvent = new TotalTimeEventImpl(em);
    }
    @Override
    public final ObservableSet<String> getAllEvents() {
        final ObservableSet<String> set = FXCollections.observableSet();
        this.eventManager.getAll().forEach(s -> set.add(s.getName()));
        return set;
    }
    @Override
    public final LocalTime getTotalTime(final String event) {
        System.out.println(event);
        if (this.totalTimeEvent.computePeriod(event).isPresent()) {
            return LocalTime.MIDNIGHT.plus(this.totalTimeEvent.computePeriod(event).get());
        }
        return LocalTime.MIDNIGHT;
    }
}
