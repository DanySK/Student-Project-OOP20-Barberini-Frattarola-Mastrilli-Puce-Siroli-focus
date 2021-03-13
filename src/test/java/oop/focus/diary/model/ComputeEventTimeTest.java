package oop.focus.diary.model;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import oop.focus.finance.Repetition;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;




public class ComputeEventTimeTest {
    private final ManagerEvent me = new ManagerEventImpl();
    private final TimerFactory factory = new TimerFactory(me);
    private final ComputeStarterCounter csc = new ComputeStarterCounterImpl(me);
   
    @Test
    public void testTimer() throws InterruptedException {
        final String str = "studio";
        final TimeScrolling timer  = factory.createTimer(str);
        //il timer relativo all'attività studio è settata a 5 sec
        timer.setStarterValue(5);
        timer.startCounter(); 
        Thread.sleep(6000);
        System.out.println("Secondi = " +csc.computePeriod(str).get().getSeconds());
        //verifica che il tempo dedicato ad un'altra attività sia vuoto
        assertEquals(Optional.empty(), csc.computePeriod("correre"));
        final TimeScrolling timer2 = factory.createTimer(str);
        //il timer studio viene fatto ripartire per 3 sec
        timer2.setStarterValue(3);
        timer2.startCounter();
        Thread.sleep(6000);
        //verifica che il tempo totale dedicato allo studio sia di 8 sec
        System.out.println("Secondi = " +csc.computePeriod(str).get().getSeconds());
        //verifica che il tempo dedicato ad un'altra attività sia vuoto
        assertEquals(Optional.empty(), csc.computePeriod("camminare"));  
        }
     
    @Test
    public void testStopwatch() throws InterruptedException {
        final String cuc = "cucinare";
        final TimeScrolling stopw = factory.createStopwatch(cuc);
        stopw.startCounter();
        Thread.sleep(6000);
        stopw.stopCounter();
        Thread.sleep(1000);
        System.out.println("Sec = " +csc.computePeriod(cuc).get().getSeconds());
        assertEquals(Optional.empty(), csc.computePeriod("camminare"));  
    }
    
       @Test
    public void testEventsSaved() {
        //si creano due eventi : shopping e palestra, da aggiungere al ManagerEvent
        final String str = "Shopping";
        final String pal = "Palestra";
        Event first = new EventImpl(str, new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl(pal, new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 00), Repetition.ONCE);
        me.addEvent(first);
        me.addEvent(second);
        //stampa tempo dedicato allo shopping e alla palestra
        System.out.println("Test 2 :"+csc.computePeriod(str).get().getHours() +":"+ csc.computePeriod(str).get().getMinutes() +
                ":" +csc.computePeriod(str).get().getSeconds());
        System.out.println("Test 2 :"+csc.computePeriod(pal).get().getHours() +":"+ csc.computePeriod(pal).get().getMinutes() +
                ":" +csc.computePeriod(pal).get().getSeconds());
        //si crea un'altro evento "shopping", in un altro giorno e dalla durata di 5 ore
        first = new EventImpl(str, new LocalDateTime(2021, 9, 27, 10, 30), new LocalDateTime(2021, 9, 27, 15, 30), Repetition.ONCE);
        me.addEvent(first);
        //si verifica che, in totale, sono state dedicate 6 ore in totale.
        System.out.println("Test 2 :"+csc.computePeriod(str).get().getHours()+
                ":"+ csc.computePeriod(str).get().getMinutes() +":" +csc.computePeriod(str).get().getSeconds() );
    }
    
    
    @Test
    public void testEventsInDifferentsDays() {
        final String prog = "progetto";
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
    
}

