package oop.focus.diary.model;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeScrollingImpl implements TimeScrolling {
    private boolean stop;
    private int starterCounter;
    private final boolean isDecreasingTime;
    private final Optional<Integer> finalCounter;
    public TimeScrollingImpl(final boolean isDecreasingTime, final Optional<Integer> finalCounter) {
        super();
        this.isDecreasingTime = isDecreasingTime;
        this.finalCounter = finalCounter;
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
        this.stop = false;
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (!stop && (!finalCounter.get().equals(starterCounter) || finalCounter.isEmpty())) {
                    try { 
                        if (isDecreasingTime) {
                            starterCounter--;
                        } else {
                            starterCounter++;
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Override
    public final void stopCounter() {
        this.stop = true;
    }
}
