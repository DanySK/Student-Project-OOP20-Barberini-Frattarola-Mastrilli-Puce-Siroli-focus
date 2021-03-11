package oop.focus.calendar;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;


public class DayTest {

    private DayImpl day;
    private static final int YEAR = 2021;
    //private final ManagerEventImpl manager = new ManagerEventImpl();

    @Before
    public void initDay() {

        final LocalDate date;
        date = new LocalDate(YEAR, 3, 26);
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

    }
    

}
