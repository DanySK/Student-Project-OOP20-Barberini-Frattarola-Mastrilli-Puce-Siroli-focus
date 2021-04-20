package oop.focus.calendar;


import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CalendarLogicTest {

	private DataSource datasource;
	private CalendarLogic manager;
    private LocalDate today;
	
	@Before
    public void initDay() {
    this.datasource = new DataSourceImpl();
    manager = new CalendarLogicImpl(datasource);
    today = new LocalDate();

    }

    @Test
    public void testWeek() {

    	manager.generateWeek(); //genero la settimana corrente

    	//controllo che mi abbia generato la settimana corrente
        assertEquals(manager.getWeek().get(0), new DayImpl(today.minusDays(today.getDayOfWeek() - 1), datasource));
        assertEquals(manager.getWeek().get(1), new DayImpl(today.minusDays(today.getDayOfWeek() - 2), datasource));
        assertEquals(manager.getWeek().get(2), new DayImpl(today.minusDays(today.getDayOfWeek() - 3), datasource));
        assertEquals(manager.getWeek().get(3), new DayImpl(today.minusDays(today.getDayOfWeek() - 4), datasource));
        assertEquals(manager.getWeek().get(4), new DayImpl(today.minusDays(today.getDayOfWeek() - 5), datasource));
        assertEquals(manager.getWeek().get(5), new DayImpl(today.minusDays(today.getDayOfWeek() - 6), datasource));
        assertEquals(manager.getWeek().get(6), new DayImpl(today.minusDays(today.getDayOfWeek() - 7), datasource));
        
        //cambio settimana, genero quella precedente
        manager.changeWeek(true);
             
        // controllo che abbia generato la settimana precedente
        assertEquals(manager.getWeek().get(0), new DayImpl(today.minusDays(today.getDayOfWeek() + 6), datasource));
        assertEquals(manager.getWeek().get(1), new DayImpl(today.minusDays(today.getDayOfWeek() + 5), datasource));
        assertEquals(manager.getWeek().get(2), new DayImpl(today.minusDays(today.getDayOfWeek() + 4), datasource));
        assertEquals(manager.getWeek().get(3), new DayImpl(today.minusDays(today.getDayOfWeek() + 3), datasource));
        assertEquals(manager.getWeek().get(4), new DayImpl(today.minusDays(today.getDayOfWeek() + 2), datasource));
        assertEquals(manager.getWeek().get(5), new DayImpl(today.minusDays(today.getDayOfWeek() + 1), datasource));
        assertEquals(manager.getWeek().get(6), new DayImpl(today.minusDays(today.getDayOfWeek()), datasource));
        
        //controllo che il giorno corrente non ci sia nella settimana precedente
        assertFalse(manager.getWeek().contains(manager.getDay(today)));
        
        //torno alla settimana attuale
        manager.changeWeek(false); 
          
        //controllo che abbia cambiato la settimana       
        assertEquals(manager.getWeek().get(0), new DayImpl(today.minusDays(today.getDayOfWeek() - 1), datasource));
        assertEquals(manager.getWeek().get(1), new DayImpl(today.minusDays(today.getDayOfWeek() - 2), datasource));
        assertEquals(manager.getWeek().get(2), new DayImpl(today.minusDays(today.getDayOfWeek() - 3), datasource));
        assertEquals(manager.getWeek().get(3), new DayImpl(today.minusDays(today.getDayOfWeek() - 4), datasource));
        assertEquals(manager.getWeek().get(4), new DayImpl(today.minusDays(today.getDayOfWeek() - 5), datasource));
        assertEquals(manager.getWeek().get(5), new DayImpl(today.minusDays(today.getDayOfWeek() - 6), datasource));
        assertEquals(manager.getWeek().get(6), new DayImpl(today.minusDays(today.getDayOfWeek() - 7), datasource));
        
        //controllo che il giorno corrente sia nella settimana attualmente visibile
        assertTrue(manager.getWeek().contains(manager.getDay(today)));
        


    }
    @Test
    public void testMonth() {

    	manager.generateMonth(); //genero il mese corrente

    	//controllo che mi abbia generato il mese corrente
    	for(int i=0; i < today.dayOfMonth().getMaximumValue(); i++) {
            assertEquals(manager.getMonth().get(i), new DayImpl(today.minusDays(today.getDayOfMonth() - (i + 1)), datasource));
    	}
        
    	//cambio mese, genero quello precedente
        manager.changeMonth(true);
        
    	//controllo che mi abbia generato il mese precedente
    	for(int i=0; i < today.minusMonths(1).dayOfMonth().getMaximumValue(); i++) {
            assertEquals(manager.getMonth().get(i), new DayImpl(today.minusDays(today.getDayOfMonth() + today.minusMonths(1).dayOfMonth().getMaximumValue() - (1 + i)), datasource));
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
            assertEquals(manager.getYear().get(i), new DayImpl(today.minusDays(today.getDayOfYear() - (i + 1)), datasource));
    	}
        
    	//cambio anno, genero quello precedente
        manager.changeYear(true);
        
        
    	//controllo che mi abbia generato l'anno precedente
    	for(int i=0; i < (today.minusYears(1).dayOfYear().getMaximumValue()) ; i++) {
            assertEquals(manager.getYear().get(i), new DayImpl(today.minusDays(today.getDayOfYear() + today.minusYears(1).dayOfYear().getMaximumValue() - (1 + i)), datasource));
    	}
    	
    	
        
        //controllo che il giorno corrente non ci sia nel mese precedente
        assertFalse(manager.getYear().contains(manager.getDay(today)));
        
    	//torno al mese corrente
    	manager.changeYear(false);
    	
        //controllo che il giorno corrente sia nel mese attualmente visibile
        assertTrue(manager.getYear().contains(manager.getDay(today)));
        
    }

}
