package oop.focus.calendar;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.homepage.model.Repetition;


public class DayTest {

    private DayImpl day;
    private static final int YEAR = 2021;
    private final ManagerEventImpl manager = new ManagerEventImpl();

    @Before
    public void initDay() {

        final LocalDate date;
        date = new LocalDate(YEAR, 3, 4);
        day = new DayImpl(date);

    }

    @Test
    public void testDay() {

        assertEquals(day.getNumber(), 4);
        assertEquals(day.getMonth(), "marzo");
        assertEquals(day.getName(), "giovedì");
        assertEquals(day.getYear(), YEAR);
        assertFalse(day.isSunday());

    }
    
    @Test
    public void testEvent() {
    	
        final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.NEVER);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.NEVER);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.NEVER);
        final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.NEVER);
        
    	manager.addEvent(first);
    	manager.addEvent(second);
    	manager.addEvent(third);
    	manager.addEvent(four);
      

    }
    

}
