package oop.focus.diary.model;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;

import org.joda.time.LocalDateTime;


import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.Repetition;

public class TimeScrollingImpl implements TimeScrolling {
    private boolean stop;
    private int starterCounter;
    private final Function<Integer, Integer> fun;
    private final Predicate<Integer> pre;
    private LocalDateTime start;
    private final String eventName;
    private final ManagerEvent me;
    public TimeScrollingImpl(final Function<Integer, Integer> function, final Predicate<Integer> predicate, final String eventName, final ManagerEvent me) {
        this.pre = predicate;
        this.stop = false;
        this.fun = function;
        this.eventName = eventName;
        this.me = me;
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
        this.start = LocalDateTime.now();
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
        me.addEvent(new EventImpl(this.eventName, this.start, LocalDateTime.now(), Repetition.NEVER));
    }
    @Override
    public final boolean end() {
        return this.stop || !pre.test(starterCounter);
    }
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        return result;
    }
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeScrollingImpl other = (TimeScrollingImpl) obj;
        if (eventName == null) {
            if (other.eventName != null) {
                return false;
            }
        } else if (!eventName.equals(other.eventName)) {
            return false;
        }
        if (start == null) {
            if (other.start != null) {
                return false;
            }
        } else if (!start.equals(other.start)) {
            return false;
        }
        return true;
    }
}
