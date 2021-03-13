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
        
        //cambio settimana, genero quella precedente
        manager.changeWeek(true);
             
        // controllo che abbia generato la settimana precedente
        assertTrue(manager.getWeek().get(0).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 6))));
        assertTrue(manager.getWeek().get(1).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 5))));
        assertTrue(manager.getWeek().get(2).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 4))));
        assertTrue(manager.getWeek().get(3).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 3))));
        assertTrue(manager.getWeek().get(4).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 2))));
        assertTrue(manager.getWeek().get(5).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 1))));
        assertTrue(manager.getWeek().get(6).equals(new DayImpl(today.minusDays(today.getDayOfWeek() + 0))));
        
        //controllo che il giorno corrente non ci sia nella settimana precedente
        assertFalse(manager.getWeek().contains(manager.getDay(today)));
        
        //torno alla settimana attuale
        manager.changeWeek(false); 
          
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
    @Test
    public void testMonth() {

    	manager.generateMonth(); //genero il mese corrente

    	//controllo che mi abbia generato il mese corrente
    	for(int i=0; i < today.dayOfMonth().getMaximumValue(); i++) {
    		assertTrue(manager.getMonth().get(i).equals(new DayImpl(today.minusDays(today.getDayOfMonth() - (i + 1)))));
    	}
        
    	//cambio mese, genero quello precedente
        manager.changeMonth(true);
        
    	//controllo che mi abbia generato il mese precedente
    	for(int i=0; i < today.minusMonths(1).dayOfMonth().getMaximumValue(); i++) {
    		assertTrue(manager.getMonth().get(i).equals(new DayImpl(today.minusDays(today.getDayOfMonth() + today.minusMonths(1).dayOfMonth().getMaximumValue() - (1 + i)))));
    	}
    	
        //controllo che il giorno corrente non ci sia nel mese precedente
        assertFalse(manager.getMonth().contains(manager.getDay(today)));
    	
    	//torno al mese corrente
    	manager.changeMonth(false);
    	
        //controllo che il giorno corrente sia nel mese attualmente visibile
        assertTrue(manager.getMonth().contains(manager.getDay(today)));
        
    }
    
    @Test
    public void testYear() {

    	manager.generateYear(); //genero l'anno corrente

    	//controllo che mi abbia generato l'hanno corrente
    	for(int i=0; i < today.dayOfYear().getMaximumValue(); i++) {
    		assertTrue(manager.getYear().get(i).equals(new DayImpl(today.minusDays(today.getDayOfYear() - (i + 1)))));
    	}
        
    	//cambio anno, genero quello precedente
        manager.changeYear(true);
        
        /*
    	//controllo che mi abbia generato l'anno precedente
    	for(int i=0; i < (today.minusYears(1).dayOfYear().getMaximumValue()) ; i++) {
    		assertTrue(manager.getYear().get(i).equals(new DayImpl(today.minusDays(today.getDayOfYear() + today.minusYears(1).dayOfYear().getMaximumValue() - (1 + i)))));
    	}
    	*/
    	
        
        //controllo che il giorno corrente non ci sia nel mese precedente
        assertFalse(manager.getYear().contains(manager.getDay(today)));
        
    	//torno al mese corrente
    	manager.changeYear(false);
    	
        //controllo che il giorno corrente sia nel mese attualmente visibile
        assertTrue(manager.getYear().contains(manager.getDay(today)));
        
    }

}
