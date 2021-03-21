package oop.focus.diary.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;


public class TimeScrollingImpl implements TimeScrolling {
    private boolean stop;
    private int starterCounter;
    private final Function<Integer, Integer> fun;
    private final Predicate<Integer> pre;
    private final TimerListener tl;
    public TimeScrollingImpl(final Function<Integer, Integer> function, final Predicate<Integer> predicate, final TimerListener tl) {
        this.pre = predicate;
        this.stop = false;
        this.fun = function;

        this.tl = tl;
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
        if (this.tl.startCounter()) {
            this.stop = false;
        } else {
            this.stop = true;
        }
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

        });
    }
    @Override
    public final void stopCounter() {
        this.stop = true;
    }

    @Override
    public final boolean end() {
       if (this.stop || !pre.test(starterCounter)) {
            System.out.println("ho finito");
            this.tl.createEvent(this.getCounter());
        }
        return this.stop || !pre.test(starterCounter);
    }
}
