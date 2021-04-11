package oop.focus.calendar;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import oop.focus.calendar.model.DayImpl;
import oop.focus.db.DataSourceImpl;


public class DayTest {

    private DayImpl day;
    private DayImpl day2;
    private DayImpl day3;
    private LocalDate date;
    private LocalDate date2;
    private LocalDate date3;
    private static final int YEAR = 2021;
    //private final ManagerEventImpl manager = new ManagerEventImpl();

    @Before
    public void initDay() {
    	final DataSourceImpl datasource = new DataSourceImpl();
        this.date = new LocalDate(YEAR, 3, 26);
        this.date2 = new LocalDate(YEAR, 4, 10);
        this.date3 = new LocalDate(YEAR, 2, 14);
        day = new DayImpl(date, datasource);
        day2 = new DayImpl(date2, datasource);
        day3 = new DayImpl(date3, datasource);

    }

    @Test
    public void testDay() {
        //Test sulla creazione del giorno venerdi 26 marzo 2021 
        assertEquals(day.getNumber(), 26);
        assertEquals(day.getMonth(), "marzo");
        assertEquals(day.getName(), "venerdì");
        assertEquals(day.getYear(), YEAR);
        assertFalse(day.isSunday());

        //Test sulla creazione del giorno sabato 10 aprile 2021 
        assertEquals(day2.getNumber(), 10);
        assertEquals(day2.getMonth(), "aprile");
        assertEquals(day2.getName(), "sabato");
        assertEquals(day2.getYear(), YEAR);
        assertFalse(day2.isSunday());

        //Test sulla creazione del giorno domenica 14 febbraio 2021 
        assertEquals(day3.getNumber(), 14);
        assertEquals(day3.getMonth(), "febbraio");
        assertEquals(day3.getName(), "domenica");
        assertEquals(day3.getYear(), YEAR);
        assertTrue(day3.isSunday());

    }
    
    @Test
    public void testEvent() {

        //Controllo che gli eventi abbiamo la data del giorno
    	day.getEvents().forEach(e -> assertFalse(e.getStartDate().equals(this.date)));

        //Controllo che gli eventi abbiamo la data del giorno
    	day2.getEvents().forEach(e -> assertFalse(e.getStartDate().equals(this.date2)));

        //Controllo che gli eventi abbiamo la data del giorno
    	day3.getEvents().forEach(e -> assertFalse(e.getStartDate().equals(this.date3)));
    }
    

}
