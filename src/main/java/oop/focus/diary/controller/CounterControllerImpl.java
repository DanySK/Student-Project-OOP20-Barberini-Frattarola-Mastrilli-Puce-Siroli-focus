package oop.focus.diary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.diary.model.CounterManager;
import oop.focus.diary.model.CounterManagerImpl;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CounterControllerImpl implements CounterController {
    private final CounterManager counterManager;
    private final ObservableList<LocalTime> list;
    private LocalTime counter;
    private final List<Consumer<Integer>> listener;

    public CounterControllerImpl(final EventManager me, final boolean isTimer) {
        this.list = FXCollections.observableArrayList();
        this.listener = new ArrayList<>();
        this.counter = LocalTime.MIDNIGHT;
        this.list.add(this.counter);
        this.counterManager = new CounterManagerImpl(me, isTimer);
    }
    @Override
    public final void setListener(final Consumer<Integer> consumer) {
        this.listener.add(consumer);
    }
    @Override
    public final ObservableList<LocalTime> getValue() {
        return this.list;
    }
    @Override
    public final void setStarter(final String event, final LocalTime localTime) {
        this.counterManager.createCounter(event);
        this.counter = localTime;
        this.list.add(this.counter);
        this.counterManager.setStarterValue(new Period(LocalTime.MIDNIGHT, localTime).toStandardSeconds().getSeconds());
        this.counterManager.setChangeListener(s -> {
            this.counter = LocalTime.MIDNIGHT.plusSeconds(s);
            this.list.add(this.counter);
            System.out.println(this.counter);
        });
        this.counterManager.setFinishListener(s -> {
            if (s.equals(0)) {
                this.counter = LocalTime.MIDNIGHT.plusSeconds(s);
                this.list.add(this.counter);
            }
            this.listener.forEach(a -> a.accept(s));
            this.counterManager.createEvent();
        });
    }
    @Override
    public final void stopSound() {
        this.counterManager.stopSound();
    }
    @Override
    public final void startTimer() {
        this.counterManager.startCounter();
    }
    @Override
    public final void stopTimer() {
        this.counterManager.stopCounter();
    }
    @Override
    public final boolean isPlaying() {
        return this.counterManager.isPlaying();
    }

}
