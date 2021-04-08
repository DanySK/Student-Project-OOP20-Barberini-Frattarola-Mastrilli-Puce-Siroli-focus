package oop.focus.diary.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class TimeScrollingImpl implements TimeScrolling {
    private boolean stop;
    private int starterCounter;
    private final Function<Integer, Integer> fun;
    private final Predicate<Integer> pre;
    private final List<Consumer<Integer>> onFinishListener;
    private final List<Consumer<Integer>> onChangeListener;

    public TimeScrollingImpl(final Function<Integer, Integer> function, final Predicate<Integer> predicate) {
        this.pre = predicate;
        this.stop = false;
        this.fun = function;
        this.onFinishListener = new ArrayList<>();
        this.onChangeListener = new ArrayList<>();
    }
    @Override
    public final void addFinishListener(final Consumer<Integer> consumer) {
        this.onFinishListener.add(consumer);
    }
    @Override
    public final void addChangeListener(final Consumer<Integer> consumer) {
        this.onChangeListener.add(consumer);
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
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            while (!this.isOver()) {
                this.onChangeListener.forEach(s -> s.accept(this.starterCounter));
                this.starterCounter = this.fun.apply(this.starterCounter);
                 try { 
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
            }
            this.onFinishListener.forEach(s -> s.accept(this.starterCounter));
        });
    }
    @Override
    public final void stopCounter() {
        this.stop = true;
    }

    private boolean isOver() {
        return this.stop || !this.pre.test(this.starterCounter);
    }
}
