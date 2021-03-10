package oop.focus.diary.model;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;


import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;


public class ComputeEventTimeTest {
    private final ManagerEvent me = new ManagerEventImpl();
    private final TimerFactory factory = new TimerFactory(me);
    private final ComputeStarterCounter csc = new ComputeStarterCounterImpl(me);
    @Test
    public void test() throws InterruptedException {
        final String str = "studio";
        final TimeScrolling timer  = factory.createTimer(str);
        //il timer relativo all'attività studio è settata a 5 sec
        timer.setStarterValue(5);
        timer.startCounter();
        while(!timer.end()) {
            ;
        } 
        Thread.sleep(1000);
        System.out.println(csc.countSeconds(str).get());
        //verifica che il tempo dedicato ad un'altra attività sia vuoto
        assertEquals(Optional.empty(), csc.countSeconds("correre"));
        final TimeScrolling timer2 = factory.createTimer(str);
        //il timer studio viene fatto ripartire per 3 sec
        timer2.setStarterValue(3);
        timer2.startCounter();
        while(!timer2.end()) {
            ;
        }
        Thread.sleep(2000);
        //verifica che il tempo totale dedicato allo studio sia di 8 sec
        System.out.println(csc.countSeconds(str).get());
        //verifica che il tempo dedicato ad un'altra attività sia vuoto
        assertEquals(Optional.empty(), csc.countSeconds("camminare"));  
        }
    }

