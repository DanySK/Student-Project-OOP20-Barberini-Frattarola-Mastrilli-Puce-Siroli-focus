package oop.focus.calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class CalendarLogicTest {

	private CalendarLogic manager;
    private LocalDate today;
	
	@Before
    public void initDay() {

    manager = new CalendarLogicImpl();
    today = new LocalDate();

    }

    @Test
    public void testWeek() {

    	manager.generateWeek(); //genero la settimana corrente

    	//controllo che mi abbia generato la settimana corrente
        assertTrue(manager.getWeek().get(0).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 1))));
        assertTrue(manager.getWeek().get(1).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 2))));
        assertTrue(manager.getWeek().get(2).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 3))));
        assertTrue(manager.getWeek().get(3).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 4))));
        assertTrue(manager.getWeek().get(4).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 5))));
        assertTrue(manager.getWeek().get(5).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 6))));
        assertTrue(manager.getWeek().get(6).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 7))));
        
        //cambio settimini, genero quella precedente
        manager.changeWeek(true);
        
        /*
         * controllo che la nuova settimana generata 
         * sia diversa da quella corrente
         */
        assertFalse(manager.getWeek().get(0).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 1))));
        assertFalse(manager.getWeek().get(1).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 2))));
        assertFalse(manager.getWeek().get(2).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 3))));
        assertFalse(manager.getWeek().get(3).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 4))));
        assertFalse(manager.getWeek().get(4).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 5))));
        assertFalse(manager.getWeek().get(5).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 6))));
        assertFalse(manager.getWeek().get(6).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 7))));
        
        /*
         * controllo che abbia generato
         * la settimana precedente
         */
        
        assertTrue(manager.getWeek().get(0).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 6))));
        assertTrue(manager.getWeek().get(1).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 5))));
        assertTrue(manager.getWeek().get(2).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 4))));
        assertTrue(manager.getWeek().get(3).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 3))));
        assertTrue(manager.getWeek().get(4).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 2))));
        assertTrue(manager.getWeek().get(5).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 1))));
        assertTrue(manager.getWeek().get(6).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 0))));
        
        manager.changeWeek(false); //torno alla settimana attuale
        
        
        //controllo che abbia cambiato la settimana       
        assertTrue(manager.getWeek().get(0).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 1))));
        assertTrue(manager.getWeek().get(1).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 2))));
        assertTrue(manager.getWeek().get(2).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 3))));
        assertTrue(manager.getWeek().get(3).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 4))));
        assertTrue(manager.getWeek().get(4).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 5))));
        assertTrue(manager.getWeek().get(5).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 6))));
        assertTrue(manager.getWeek().get(6).equals(new DayImpl(today.minusDays(today.getDayOfWeek() - 7))));
        
        //controllo che il giorno corrente sia nella settimana attualmente visibile
        assertTrue(manager.getWeek().contains(manager.getDay(today)));
        


    }

}
