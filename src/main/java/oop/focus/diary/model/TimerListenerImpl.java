package oop.focus.diary.model;
import oop.focus.finance.Repetition;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.joda.time.LocalDateTime;
public class TimerListenerImpl implements TimerListener {
    private LocalDateTime start;
    private final String eventName;
    private final ManagerEvent me;
    private Sound sound;
    public TimerListenerImpl(final ManagerEvent me, final String eventName) {
        this.me = me;
        try {
            this.sound = new SoundImpl();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        this.eventName = eventName;
        } 
    @Override
    public final void createEvent(final Integer counter) {
        if (counter.equals(0)) {
            try {
                this.sound.playSound();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        me.addEvent(new EventImpl(this.eventName, this.start, LocalDateTime.now(), Repetition.ONCE));
        }
    @Override
    public final boolean startCounter() {
        if (this.me.canStart(LocalDateTime.now())) {
            this.start = LocalDateTime.now();
            return true;
        } else {
            throw new IllegalStateException();
        }
    }
}

