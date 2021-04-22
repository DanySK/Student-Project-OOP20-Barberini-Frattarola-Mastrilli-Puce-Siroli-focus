package oop.focus.homepage;


import oop.focus.event.model.EventManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventImpl;
import oop.focus.event.model.EventManagerImpl;
import oop.focus.event.model.Filter;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.common.Repetition;


public class EventTest {

	private final DataSource dsi = new DataSourceImpl();
    private final EventManager eventi = new EventManagerImpl(this.dsi);

    private final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
    private final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 9, 0), Repetition.ONCE);
    private final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.ONCE);
    private final Event fourth = new EventImpl("Ikea", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
    private final Event fifth = new EventImpl("Spesa", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 6, 30), Repetition.ONCE);
    private final Event sixth = new EventImpl("Estetista", new LocalDateTime(2021, 9, 26, 9, 0), new LocalDateTime(2021, 9, 27, 10, 0), Repetition.ONCE);
    
    @Test
    public void addingAndRemovingEventTest() {
        try {
            this.eventi.addEvent(this.first);
            this.eventi.addEvent(this.third);
            this.eventi.addEvent(this.fourth);
            this.eventi.addEvent(this.fifth);
        } catch(IllegalStateException ignored) {}

        this.eventi.addEvent(this.second);

        assertTrue(this.eventi.getAll().contains(this.first));
        assertTrue(this.eventi.getAll().contains(this.second));
        assertTrue(this.eventi.getAll().contains(this.third));

        this.eventi.removeEvent(this.first);
        this.eventi.removeEvent(this.second);
        this.eventi.removeEvent(this.third);
    }

    @Test
    public void equalsEventsTest() {

    	final Event firstCopy = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.DAILY);
    	assertEquals(this.first, firstCopy);

    	final Event secondCopy = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 9, 0), Repetition.BIMONTHLY);
    	assertEquals(this.second, secondCopy);
    }

    @Test
    public void findEventsTest() {
        this.eventi.addEvent(this.sixth);
        assertTrue(Filter.takeOnlyDailyEvent(new ArrayList<>(this.eventi.getAll())).contains(this.sixth));
        this.eventi.removeEvent(this.sixth);
    }

    @Test
    public void findByDateTest() {
        try {
            this.eventi.addEvent(this.first);
            this.eventi.addEvent(this.third);
            this.eventi.addEvent(this.sixth);
        }catch(IllegalStateException ignored){}
 
        assertTrue(this.eventi.findByDate(new LocalDate(2021, 9, 26)).contains(this.first));
        assertTrue(this.eventi.findByDate(new LocalDate(2021, 9, 26)).contains(this.third));
        assertTrue(this.eventi.findByDate(new LocalDate(2021, 9, 26)).contains(this.sixth));

        this.eventi.removeEvent(this.first);
        this.eventi.removeEvent(this.third);
        this.eventi.removeEvent(this.sixth);
    }

    @Test
    public void verificationOfTimeAccuracyTest() {

        assertEquals(this.first.getStartHour(), new LocalTime(9, 30));
        assertEquals(this.first.getEndHour(), new LocalTime(10, 30));

        assertEquals(this.second.getStartHour(), new LocalTime(8, 30));
        assertEquals(this.second.getEndHour(), new LocalTime(9, 0));

        assertEquals(this.third.getStartHour(), new LocalTime(11, 30));
        assertEquals(this.third.getEndHour(), new LocalTime(18, 30));
    }

}
