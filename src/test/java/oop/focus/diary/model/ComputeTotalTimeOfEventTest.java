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
import org.joda.time.LocalDateTime;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ComputeTotalTimeOfEventTest {
    private final EventManager me = new EventManagerImpl(new DataSourceImpl());
    private final CounterManager timer = new CounterManagerImpl(this.me, true);
    private final CounterManager stopwatch = new CounterManagerImpl(this.me, false);
    private final TotalTimeEvent csc = new TotalTimeEventImpl(this.me);

    @Test
    public void testTimer() throws InterruptedException {
        final String str = "test1";
        this.timer.createCounter(str);
        this.timer.setChangeListener(System.out::println);
        //il timer relativo all'attività studio è settata a 5 sec
        this.timer.setStarterValue(5);
        //this.timer.setFinishListener(s -> s.timer.createEvent());
        this.timer.startCounter();
        Thread.sleep(6000);

        System.out.println("Secondi = " + this.csc.computePeriod(str).get().getSeconds());
        //verifica che il tempo dedicato ad un'altra attività sia vuoto
        assertEquals(Optional.empty(), this.csc.computePeriod("correre"));
        this.me.removeEvent(this.me.findByName(str).stream().findAny().get());

    }


    @Test
    public void testAlarmSound() throws InterruptedException {
        timer.createCounter("test2");
        timer.setChangeListener(System.out::println);
        timer.setStarterValue(3);
        timer.startCounter();
        Thread.sleep(8000);
        System.out.println("Secondi = " + this.csc.computePeriod("test2").get().getSeconds());
        this.me.removeEvent(this.me.findByName("test2").stream().findAny().get());
    }



    @Test
    public void testStopwatch() throws InterruptedException {
        final String cuc = "test3";
        stopwatch.createCounter(cuc);
        stopwatch.setStarterValue(0);
        stopwatch.startCounter();
        Thread.sleep(6000);
        stopwatch.stopCounter();
        Thread.sleep(1000);
        System.out.println("Sec = " +csc.computePeriod(cuc).get().getSeconds());
        this.me.removeEvent(this.me.findByName("test3").stream().findAny().get());
    }



    @Test
    public void testEventsSaved() {
        //si creano due eventi : shopping e palestra, da aggiungere al ManagerEvent
        final String str = "test5";
        final String pal = "test6";
        Event first = new EventImpl(str, new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl(pal, new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 00), Repetition.ONCE);
        me. saveTimer(first);
        me.saveTimer(second);
        //stampa tempo dedicato allo shopping e alla palestra
        System.out.println(csc.computePeriod(str).get().getHours() +":"+ csc.computePeriod(str).get().getMinutes() +
                ":" +csc.computePeriod(str).get().getSeconds());
        System.out.println(csc.computePeriod(pal).get().getHours() +":"+ csc.computePeriod(pal).get().getMinutes() +
                ":" +csc.computePeriod(pal).get().getSeconds());
        //si crea un'altro evento "shopping", in un altro giorno e dalla durata di 5 ore
        first = new EventImpl(str, new LocalDateTime(2021, 9, 27, 10, 30), new LocalDateTime(2021, 9, 27, 15, 30), Repetition.ONCE);
        me.saveTimer(first);
        //si verifica che, in totale, sono state dedicate 6 ore in totale.
        System.out.println(csc.computePeriod(str).get().getHours()+
                ":"+ csc.computePeriod(str).get().getMinutes() +":" +csc.computePeriod(str).get().getSeconds() );
        this.me.removeEvent(this.me.findByName(str).stream().findAny().get());
        this.me.removeEvent(this.me.findByName(pal).stream().findAny().get());
    }

    @Test
    public void testEventsInDifferentsDays() {
        final String prog = "test7";
        //creato evento progetto, dalla durata di 5 ore
        Event third = new EventImpl(prog, new LocalDateTime(2021, 9, 26, 20, 30), new LocalDateTime(2021, 9, 27, 01, 30), Repetition.ONCE);
        this.me.saveTimer(third);
        //verifica la durata dell'evento progetto
        System.out.println("Test 7 :" + this.csc.computePeriod(prog).get().getHours() + ":"
                +csc.computePeriod(prog).get().getMinutes() + ":"+csc.computePeriod(prog).get().getSeconds());
        //nuovo evento progetto, dalla durata di 21 ore

    }








}

