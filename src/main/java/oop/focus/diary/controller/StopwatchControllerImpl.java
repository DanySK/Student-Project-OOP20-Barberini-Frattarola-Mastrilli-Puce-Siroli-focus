package oop.focus.diary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.model.CounterManager;
import oop.focus.diary.model.CounterManagerImpl;
import oop.focus.diary.model.TotalTimeEvent;
import oop.focus.diary.model.TotalTimeEventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import org.joda.time.LocalTime;

public class StopwatchControllerImpl {
    private final CounterManager counterManager;
    private final EventManager eventManager;
    private final TotalTimeEvent totalTimeEvent;
    private final ObservableList<LocalTime> list;
    private LocalTime counter;
    public StopwatchControllerImpl() {
        this.eventManager = new EventManagerImpl(new DataSourceImpl());
        this.list = FXCollections.observableArrayList();
        this.counter = LocalTime.MIDNIGHT;
        this.list.add(this.counter);
        this.totalTimeEvent = new TotalTimeEventImpl(this.eventManager);
        this.counterManager = new CounterManagerImpl(this.eventManager, false);
    }
    public ObservableList<String> getAllEvents() {
        final ObservableList<String> list = FXCollections.observableArrayList();
        list.add("prova");
        this.eventManager.getAll().forEach(s -> list.add(s.getName()));
        return list;
    }
    public LocalTime getTotalTime(final String event) {
        if (this.totalTimeEvent.computePeriod(event).isPresent()) {
            return LocalTime.MIDNIGHT.plus(this.totalTimeEvent.computePeriod(event).get());
        }
        return LocalTime.MIDNIGHT;
    }
    public LocalTime getCounter() {
        return this.counter;
    }
    public ObservableList<LocalTime> getValue() {
        return this.list;
    }
    public void setStarter(final String event) {
        this.counterManager.createCounter(event);
        this.counter = LocalTime.MIDNIGHT;
        this.list.add(this.counter);
        this.counterManager.setStarterValue(this.counter.getSecondOfMinute());
        this.counterManager.setChangeListener(s -> {
            this.counter = LocalTime.MIDNIGHT.plusSeconds(s);
            this.list.add(this.counter);
            System.out.println(this.counter);
        });
    }
    public void startTimer() {
        this.counterManager.startCounter();
    }
    public void stopTimer() {
        this.counterManager.stopCounter();
    }

}
