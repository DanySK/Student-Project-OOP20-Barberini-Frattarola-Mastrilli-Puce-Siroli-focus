package oop.focus.diary.model;

import oop.focus.common.Repetition;
import oop.focus.event.model.EventImpl;
import oop.focus.event.model.EventManager;
import org.joda.time.LocalDateTime;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * Implementation of {@link CounterManager}. The class manages the creation of counter,
 * sets his value and sets alarm of end timer.
 */
public class CounterManagerImpl implements CounterManager {
    private final CounterFactoryImpl tf;
    private TimeScrolling counter;
    private LocalDateTime start;
    private Sound sound;
    private Integer finalCounter;
    private final EventManager me;
    private String eventName;
    private boolean isSet;
    private final boolean isTimer;

    /**
     * Instantiates a new Counter Manager.
     * @param me    the eventManager
     * @param isTimer   a boolean which is true if the counter is a timer, false otherwise
     */
    public CounterManagerImpl(final EventManager me, final boolean isTimer) {
        this.me = me;
        this.isTimer = isTimer;
        this.tf = new CounterFactoryImpl(me);
        this.isSet = false;
        this.finalCounter = null;

    }

    /**
     * {@inheritDoc}
     */
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
        } catch (final UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        this.counter.addFinishListener(integer -> {
           this.finalCounter = integer;
           this.createEvent();
        });

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void setFinishListener(final Consumer<Integer> consumer) {
        this.counter.addFinishListener(consumer);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void setChangeListener(final Consumer<Integer> consumer) {
        this.counter.addChangeListener(consumer);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void startCounter() {
        if (this.isSet && this.me.timerCanStart(LocalDateTime.now())) {
            this.counter.startCounter();
            this.start = LocalDateTime.now();
        } else {
            throw new IllegalStateException();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void stopCounter() {
        this.counter.stopCounter();
        this.createEvent();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void setStarterValue(final Integer value) {
        this.counter.setStarterValue(value);
        this.isSet = true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void stopSound() {
        this.sound.stopSound();
    }

    /**
     * The method is called when a counter ends and creates new event.
     */
    private void createEvent() {
        if (this.finalCounter != null && this.finalCounter.equals(0)) {
            this.sound.playSound();
            this.finalCounter = null;
        }
        this.me.saveTimer(new EventImpl(this.eventName, this.start, LocalDateTime.now(), Repetition.ONCE));
    }


}
