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
            this.list.forEach(z -> z.accept(this.starterCounter));
        });
    }
    @Override
    public final void stopCounter() {
        this.stop = true;
    }

    @Override
    public final boolean end() {
        return this.stop || !pre.test(starterCounter);
    }
}
