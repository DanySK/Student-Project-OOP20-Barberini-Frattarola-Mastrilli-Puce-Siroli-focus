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
    private final List<Consumer<Integer>> list;
    private boolean isOver;
    public TimeScrollingImpl(final Function<Integer, Integer> function, final Predicate<Integer> predicate) {
        this.pre = predicate;
        this.stop = false;
        this.fun = function;
        this.list = new ArrayList<>();
    }
    @Override
    public final void addListener(final Consumer<Integer> consumer) {
        list.add(consumer);
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
        this.isOver = false;
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            while (!end()) {
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
    public final boolean isOver() {
        return this.isOver;
    }
    @Override
    public final boolean end() {
        if (this.stop || !pre.test(starterCounter)) {
            this.isOver = true;
        }
        this.list.forEach(z -> z.accept(this.starterCounter));
        return this.stop || !pre.test(starterCounter);
    }
}
