package oop.focus.diary.model;

import oop.focus.common.Repetition;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalDateTime;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public class CounterManagerImpl implements CounterManager {
    private final CounterFactory tf;
    private TimeScrolling counter;
    private LocalDateTime start;
    private Sound sound;
    private Optional<Integer> finalCounter;
    private final EventManager me;
    private String eventName;
    private boolean isSet;
    private final boolean isTimer;

    public CounterManagerImpl(final EventManager me, final boolean isTimer) {
        this.me = me;
        this.isTimer = isTimer;
        this.tf = new CounterFactory(me);
        this.isSet = false;
        this.finalCounter = Optional.empty();

    }
    @Override
    public final void createCounter(final String event) {
        if (this.isTimer) {
            this.counter = this.tf.createTimer();
        } else {
            this.counter = this.tf.createStopwatch();
        }
        this.eventName = event;
        try {
            this.sound = new SoundImpl();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        this.counter.addFinishListener(integer -> {
           this.finalCounter = Optional.of(integer);
           this.createEvent();
        });

    }
    @Override
    public final void setFinishListener(final Consumer<Integer> consumer) {
        this.counter.addFinishListener(consumer);
    }
    @Override
    public final void setChangeListener(final Consumer<Integer> consumer) {
        this.counter.addChangeListener(consumer);
    }
    @Override
    public final void startCounter() {
        if (this.isSet && this.me.timerCanStart(LocalDateTime.now())) {
            this.counter.startCounter();
            this.start = LocalDateTime.now();
        } else {
            throw new IllegalStateException();
        }
    }
    @Override
    public final void stopCounter() {
        this.counter.stopCounter();
        this.createEvent();
    }
    @Override
    public final void setStarterValue(final Integer value) {
        this.counter.setStarterValue(value);
        this.isSet = true;
    }
    @Override
    public final void stopSound() {
        this.sound.stopSound();
    }
    @Override
    public final void createEvent() {
        if (this.finalCounter.isPresent() && this.finalCounter.get().equals(0)) {
            this.sound.playSound();
            this.finalCounter = Optional.empty();
        }
        this.me.saveTimer(new EventImpl(this.eventName, this.start, LocalDateTime.now(), Repetition.ONCE));
    }
    @Override
    public final boolean isPlaying() {
        return this.sound.isPlaying();
    }

}
