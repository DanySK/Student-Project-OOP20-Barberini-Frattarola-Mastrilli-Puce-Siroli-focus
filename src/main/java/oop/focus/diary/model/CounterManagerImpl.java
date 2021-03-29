package oop.focus.diary.model;

import oop.focus.finance.model.Repetition;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;
import org.joda.time.LocalDateTime;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class CounterManagerImpl implements CounterManager {
    private final CounterFactory tf;
    private TimeScrolling counter;
    private LocalDateTime start;
    private Sound sound;
    private Integer finalCounter;
    private final ManagerEvent me;
    private String eventName;
    private boolean isSetted;
    private final boolean isTimer;
    public CounterManagerImpl(final ManagerEvent me, final boolean isTimer) {
        this.me = me;
        this.isTimer = isTimer;
        this.tf = new CounterFactory(me);
        this.isSetted = false;
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
        this.counter.addListener(integer -> {
            System.out.println(integer);
            this.finalCounter = integer;
            this.createEvent();
        });
    }
    @Override
    public final void startCounter() {
        if (this.isSetted) {
            this.counter.startCounter();
            this.start = LocalDateTime.now();
        } else {
            throw new IllegalStateException();
        }
    }
    @Override
    public final void stopCounter() {
        this.counter.stopCounter();
    }
    @Override
    public final void setStarterValue(final Integer value) {
        this.counter.setStarterValue(value);
        this.isSetted = true;
    }
    private void createEvent() {
        if (this.finalCounter.equals(0)) {
            try {
                this.sound.playSound();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        this.me.addEvent(new EventImpl(this.eventName, this.start, LocalDateTime.now(), Repetition.ONCE));
    }


}
