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

	private DataSource dataSource;
	private CalendarLogic manager;
    private LocalDate today;
	
	@Before
    public void initDay() {
    this.dataSource = new DataSourceImpl();
    this.manager = new CalendarLogicImpl(dataSource);
    this.today = new LocalDate();

    }

    @Test
    public void testWeek() {

    	this.manager.generateWeek(); //genero la settimana corrente

    	//controllo che mi abbia generato la settimana corrente
        assertEquals(this.manager.getWeek().get(0), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 1), this.dataSource));
        assertEquals(this.manager.getWeek().get(1), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 2), this.dataSource));
        assertEquals(this.manager.getWeek().get(2), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 3), this.dataSource));
        assertEquals(this.manager.getWeek().get(3), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 4), this.dataSource));
        assertEquals(this.manager.getWeek().get(4), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 5), this.dataSource));
        assertEquals(this.manager.getWeek().get(5), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 6), this.dataSource));
        assertEquals(this.manager.getWeek().get(6), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 7), this.dataSource));
        
        //cambio settimana, genero quella precedente
        this.manager.changeWeek(true);
             
        // controllo che abbia generato la settimana precedente
        assertEquals(this.manager.getWeek().get(0), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() + 6), this.dataSource));
        assertEquals(this.manager.getWeek().get(1), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() + 5), this.dataSource));
        assertEquals(this.manager.getWeek().get(2), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() + 4), this.dataSource));
        assertEquals(this.manager.getWeek().get(3), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() + 3), this.dataSource));
        assertEquals(this.manager.getWeek().get(4), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() + 2), this.dataSource));
        assertEquals(this.manager.getWeek().get(5), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() + 1), this.dataSource));
        assertEquals(this.manager.getWeek().get(6), new DayImpl(this.today.minusDays(this.today.getDayOfWeek()), this.dataSource));
        
        //controllo che il giorno corrente non ci sia nella settimana precedente
        assertFalse(this.manager.getWeek().contains(this.manager.getDay(this.today)));
        
        //torno alla settimana attuale
        this.manager.changeWeek(false); 
          
        //controllo che abbia cambiato la settimana       
        assertEquals(this.manager.getWeek().get(0), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 1), this.dataSource));
        assertEquals(this.manager.getWeek().get(1), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 2), this.dataSource));
        assertEquals(this.manager.getWeek().get(2), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 3), this.dataSource));
        assertEquals(this.manager.getWeek().get(3), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 4), this.dataSource));
        assertEquals(this.manager.getWeek().get(4), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 5), this.dataSource));
        assertEquals(this.manager.getWeek().get(5), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 6), this.dataSource));
        assertEquals(this.manager.getWeek().get(6), new DayImpl(this.today.minusDays(this.today.getDayOfWeek() - 7), this.dataSource));
        
        //controllo che il giorno corrente sia nella settimana attualmente visibile
        assertTrue(this.manager.getWeek().contains(this.manager.getDay(this.today)));
        


    }
    @Test
    public void testMonth() {

    	manager.generateMonth(); //genero il mese corrente

    	//controllo che mi abbia generato il mese corrente
    	for(int i=0; i < this.today.dayOfMonth().getMaximumValue(); i++) {
            assertEquals(this.manager.getMonth().get(i), new DayImpl(this.today.minusDays(this.today.getDayOfMonth() - (i + 1)), this.dataSource));
    	}
        
    	//cambio mese, genero quello precedente
        manager.changeMonth(true);
        
    	//controllo che mi abbia generato il mese precedente
    	for(int i=0; i < this.today.minusMonths(1).dayOfMonth().getMaximumValue(); i++) {
            assertEquals(this.manager.getMonth().get(i), new DayImpl(this.today.minusDays(this.today.getDayOfMonth() + this.today.minusMonths(1).dayOfMonth().getMaximumValue() - (1 + i)), dataSource));
    	}
    	
        //controllo che il giorno corrente non ci sia nel mese precedente
        assertFalse(this.manager.getMonth().contains(this.manager.getDay(this.today)));
    	
    	//torno al mese corrente
        this.manager.changeMonth(false);
    	
        //controllo che il giorno corrente sia nel mese attualmente visibile
        assertTrue(this.manager.getMonth().contains(this.manager.getDay(this.today)));
        
    }
    
    @Test
    public void testYear() {

    	this.manager.generateYear(); //genero l'anno corrente

    	//controllo che mi abbia generato l'hanno corrente
    	for(int i=0; i < this.today.dayOfYear().getMaximumValue(); i++) {
            assertEquals(this.manager.getYear().get(i), new DayImpl(this.today.minusDays(this.today.getDayOfYear() - (i + 1)), dataSource));
    	}
        
    	//cambio anno, genero quello precedente
    	this.manager.changeYear(true);
        
        
    	//controllo che mi abbia generato l'anno precedente
    	for(int i=0; i < (today.minusYears(1).dayOfYear().getMaximumValue()) ; i++) {
            assertEquals(this.manager.getYear().get(i), new DayImpl(this.today.minusDays(this.today.getDayOfYear() + this.today.minusYears(1).dayOfYear().getMaximumValue() - (1 + i)), this.dataSource));
    	}
    	
    	
        
        //controllo che il giorno corrente non ci sia nel mese precedente
        assertFalse(this.manager.getYear().contains(this.manager.getDay(this.today)));
        
    	//torno al mese corrente
        this.manager.changeYear(false);
    	
        //controllo che il giorno corrente sia nel mese attualmente visibile
        assertTrue(this.manager.getYear().contains(this.manager.getDay(this.today)));
        
    }

}
