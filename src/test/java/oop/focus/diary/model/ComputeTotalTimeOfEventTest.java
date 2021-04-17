package oop.focus.diary.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Optional;

import oop.focus.common.Repetition;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import org.h2.util.MathUtils;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ComputeTotalTimeOfEventTest {
    private final EventManager me = new EventManagerImpl(new DataSourceImpl());
    private final TotalTimeEvent csc = new TotalTimeEventImpl(this.me);

    @Test
    public void testEventsSaved() {
        //si creano due eventi : shopping e palestra, da aggiungere al ManagerEvent
        final String str = "test5";
        final String pal = "test6";
        Event first = new EventImpl(str, new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl(pal, new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 00), Repetition.ONCE);
        this.me. saveTimer(first);
        this.me.saveTimer(second);
        assertEquals(this.csc.computePeriod(str).get().getHours(), 1);
        assertEquals(this.csc.computePeriod(str).get().getMinutes(), 0);
        assertEquals(this.csc.computePeriod(pal).get().getHours(), 1);
        assertEquals(this.csc.computePeriod(pal).get().getMinutes(), 30);
        this.me.removeEvent(first);
        this.me.removeEvent(second);
    }

    @Test
    public void testEventsInDifferentsDays() {
        final String prog = "test7";
        //creato evento progetto, dalla durata di 5 ore
        Event third = new EventImpl(prog, new LocalDateTime(2021, 9, 26, 20, 30), new LocalDateTime(2021, 9, 27, 01, 30), Repetition.ONCE);
        this.me.saveTimer(third);
        //verifica la durata dell'evento progetto
        assertEquals(this.csc.computePeriod(prog).get().getHours(), 5);
        assertEquals(this.csc.computePeriod(prog).get().getMinutes(), 0);
        //nuovo evento progetto, dalla durata di 21 ore
        this.me.removeEvent(third);

    }









}

