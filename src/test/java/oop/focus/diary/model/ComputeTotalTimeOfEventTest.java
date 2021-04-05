package oop.focus.diary.model;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import oop.focus.db.DataSourceImpl;

import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

import org.junit.Test;


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
        this.timer.startCounter();
        Thread.sleep(6000);
        System.out.println("Secondi = " + this.csc.computePeriod(str).get().getSeconds());
        //verifica che il tempo dedicato ad un'altra attività sia vuoto
        assertEquals(Optional.empty(), this.csc.computePeriod("correre"));
    }
/*
/*
    @Test
    public void testAlarmSound() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        timer.createCounter("test2");
        timer.setStarterValue(3);
        timer.startCounter();
        Thread.sleep(8000);
        final Sound sound = new SoundImpl();
        if(sound.isPlaying()){
            sound.stopSound();
        }

    }

 */
/*
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
    }



    @Test
    public void testEventsSaved() {
        //si creano due eventi : shopping e palestra, da aggiungere al ManagerEvent
        final String str = "test5";
        final String pal = "test6";
        Event first = new EventImpl(str, new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl(pal, new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 00), Repetition.ONCE);
        me.addEvent(first);
        me.addEvent(second);
        //stampa tempo dedicato allo shopping e alla palestra
        System.out.println(csc.computePeriod(str).get().getHours() +":"+ csc.computePeriod(str).get().getMinutes() +
                ":" +csc.computePeriod(str).get().getSeconds());
        System.out.println(csc.computePeriod(pal).get().getHours() +":"+ csc.computePeriod(pal).get().getMinutes() +
                ":" +csc.computePeriod(pal).get().getSeconds());
        //si crea un'altro evento "shopping", in un altro giorno e dalla durata di 5 ore
        first = new EventImpl(str, new LocalDateTime(2021, 9, 27, 10, 30), new LocalDateTime(2021, 9, 27, 15, 30), Repetition.ONCE);
        me.addEvent(first);
        //si verifica che, in totale, sono state dedicate 6 ore in totale.
        System.out.println(csc.computePeriod(str).get().getHours()+
                ":"+ csc.computePeriod(str).get().getMinutes() +":" +csc.computePeriod(str).get().getSeconds() );
    }

    @Test
    public void testEventsInDifferentsDays() {
        final String prog = "test7";
        //creato evento progetto, dalla durata di 5 ore
        Event third = new EventImpl(prog, new LocalDateTime(2021, 9, 26, 20, 30), new LocalDateTime(2021, 9, 27, 01, 30), Repetition.ONCE);
        me.addEvent(third);
        //verifica la durata dell'evento progetto
        System.out.println("Test 3 :" +csc.computePeriod(prog).get().getHours() + ":"
                +csc.computePeriod(prog).get().getMinutes() + ":"+csc.computePeriod(prog).get().getSeconds());
        //nuovo evento progetto, dalla durata di 21 ore
        third = new EventImpl("progetto", new LocalDateTime(2021, 9, 26, 21, 30), new LocalDateTime(2021, 9, 27, 18, 30), Repetition.ONCE);
        me.addEvent(third);
        //verifica la durata totale del progetto : 26 ore
        System.out.println("Test 3 :" +csc.computePeriod(prog).get().getHours() + ":"+csc.computePeriod(prog).get().getMinutes() +
                ":"+csc.computePeriod(prog).get().getSeconds());
    }
/*
    @Test
    public void testTimerEnds() throws InterruptedException {
        final String corsa = "test8";
        this.me.addEvent(new EventImpl("test9", new LocalDateTime(2021, 03, 19, 11, 9), new LocalDateTime(2021, 03, 19, 11,20), Repetition.ONCE));
        System.out.println( "test " + new LocalDateTime().toLocalTime());
        timer.createCounter("corsa");
        timer.setStarterValue(50);
        timer.startCounter();
        Thread.sleep(60_000);
        System.out.println("test");
        System.out.println("Test 2 :"+csc.computePeriod(corsa).get().getHours()+
                ":"+ csc.computePeriod(corsa).get().getMinutes() +":" +csc.computePeriod(corsa).get().getSeconds() );


    }



    @Test (expected =  IllegalStateException.class)
    public void testCompatibilityTimerWithEvents()   {
        //creazione dell'evento
        this.me.addEvent(new EventImpl("test10", new LocalDateTime(2021, 03, 19, 22, 34),
                new LocalDateTime(2021, 03, 19, 22, 37), Repetition.ONCE));
        //il timer viene fatto partire alle 22:35(un minuto dopo l'inizio di un altro evento e si verifica l'eccezione)
        timer.createCounter("test11");
        timer.setStarterValue(5);
        timer.startCounter();
    }

    */


}

