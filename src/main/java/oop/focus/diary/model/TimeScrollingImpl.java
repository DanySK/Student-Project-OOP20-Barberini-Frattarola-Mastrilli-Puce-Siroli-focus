package oop.focus.diary.model;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import oop.focus.homePage.model.EventImpl;
import oop.focus.homePage.model.Ripetitions;

public class TimeScrollingImpl implements TimeScrolling {
    private boolean stop;
    private int starterCounter;
    private final Function<Integer, Integer> fun;
    private final Predicate<Integer> pre;
    private LocalDate startDay;
    private LocalTime startHour;
    private final String eventName;
    public TimeScrollingImpl(final Function<Integer, Integer> function, final Predicate<Integer> predicate, final String eventName) {
        this.pre = predicate;
        this.stop = false;
        this.fun = function;
        this.eventName = eventName;
    }
    @Override
    public final int getCounter() {
        return this.starterCounter;
    }
    @Override
    public final void setStarterValue(final int starterCounter) {
        this.starterCounter = starterCounter;
    }
    @Override
    public final void startCounter() {
        this.startDay = LocalDate.now();
        this.startHour = LocalTime.now();
        this.stop = false;
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            while (!end()) {
                System.out.println(this.starterCounter);
                this.starterCounter = fun.apply(this.starterCounter);
                 try { 
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
            }
            this.createEvent();
        });
    }
    @Override
    public final void stopCounter() {
        this.stop = true;
    }
    private void createEvent() {
        new EventImpl(this.eventName, this.startDay, LocalDate.now(), this.startHour, LocalTime.now(), Ripetitions.NEVER);
    }
    @Override
    public final boolean end() {
        return this.stop || pre.test(starterCounter);
    }
}
