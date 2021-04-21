package oop.focus.diary.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Implementation of {@link TimeScrolling}.
 */
public class TimeScrollingImpl implements TimeScrolling {
    private boolean stop;
    private int starterCounter;
    private final Function<Integer, Integer> fun;
    private final Predicate<Integer> pre;
    private final List<Consumer<Integer>> onFinishListener;
    private final List<Consumer<Integer>> onChangeListener;

    /**
     * Instantiates a new time scrolling. It uses a {@link Function} to set counter's behaviour and a
     * {@link Predicate} to set when a counter has to ends.
     * @param function  defines how a counter changes
     * @param predicate defines when a counter ends.
     */
    public TimeScrollingImpl(final Function<Integer, Integer> function, final Predicate<Integer> predicate) {
        this.pre = predicate;
        this.stop = false;
        this.fun = function;
        this.onFinishListener = new ArrayList<>();
        this.onChangeListener = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFinishListener(final Consumer<Integer> consumer) {
        this.onFinishListener.add(consumer);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addChangeListener(final Consumer<Integer> consumer) {
        this.onChangeListener.add(consumer);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getCounter() {
        return this.starterCounter;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setStarterValue(final int starterCounter) {
        this.starterCounter = starterCounter;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void startCounter() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            while (!this.isOver()) {
                this.onChangeListener.forEach(s -> s.accept(this.starterCounter));
                this.starterCounter = this.fun.apply(this.starterCounter);
                 try { 
                     Thread.sleep(1000);
                 } catch (final InterruptedException e) {
                     e.printStackTrace();
                 }
            }
            this.onFinishListener.forEach(s -> s.accept(this.starterCounter));
        });
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stopCounter() {
        this.stop = true;
    }

    /**
     * Checks if a counter could finish or not.
     * @return  true if a counter is over, false otherwise.
     */
    private boolean isOver() {
        return this.stop || !this.pre.test(this.starterCounter);
    }
}
