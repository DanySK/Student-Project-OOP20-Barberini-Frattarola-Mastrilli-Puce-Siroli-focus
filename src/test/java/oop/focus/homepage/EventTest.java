package oop.focus.homepage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Set;

import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.common.Repetition;

public class EventTest {

	private final DataSource dsi = new DataSourceImpl();
    private final EventManager eventi = new EventManagerImpl(dsi);

    private final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
    private final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 9, 00), Repetition.ONCE);
    private final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.ONCE);
    private final Event fourth = new EventImpl("ACQUA", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 9, 45), Repetition.ONCE);
    private final Event fifth = new EventImpl("Ikea", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
    private final Event sixth = new EventImpl("Spesa", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 6, 30), Repetition.ONCE);
    private final Event seventh = new EventImpl("Gita", new LocalDateTime(2021, 9, 23, 9, 00), new LocalDateTime(2021, 9, 24, 9, 30), Repetition.ONCE);
    private final Event eight = new EventImpl("Estetista", new LocalDateTime(2021, 9, 26, 9, 00), new LocalDateTime(2021, 9, 27, 10, 00), Repetition.ONCE);
    private final Event ninth = new EventImpl("Cane", new LocalDateTime(2021, 9, 26, 9, 00), new LocalDateTime(2021, 9, 26, 9, 15), Repetition.ONCE);

    @Test
    public void addingAndRemovingEventTest() {

        for(Event e : this.eventi.getAll()){
            System.out.println(e.getName());
        }
        this.eventi.addEvent(first);
        assertEquals(this.eventi.getEvents(), Set.of(first));

        this.eventi.removeEvent(first);

        this.eventi.addEvent(second);
        assertEquals(this.eventi.getEvents(), Set.of(second));
        this.eventi.removeEvent(second);

        this.eventi.addEvent(third);
        assertEquals(this.eventi.getEvents(), Set.of(third));
        this.eventi.removeEvent(third);

        try{
        	this.eventi.addEvent(fifth);
        } catch (IllegalStateException ignored) {}

        assertEquals(this.eventi.getEvents(), Set.of());

        try{
        	this.eventi.addEvent(sixth);
        } catch (IllegalStateException ignored) {}
        //verifico che gli eventi vengano aggiunti correttamente.
        assertEquals(this.eventi.getEvents(), Set.of());

        this.eventi.addEvent(seventh);
        //controllo che gli eventi vegano correttamente suddivisi tra giornalieri e non.
        assertEquals(this.eventi.getDailyEvents(), Set.of(seventh));
        this.eventi.removeEvent(seventh);
    }

    //prendo solo gli eventi che hanno durata superiore o uguale a 30 minuti.
    @Test
    public void durationInMinutes() {
 
    	this.eventi.addEvent(ninth);
    	assertFalse(this.eventi.getEventsWithDuration().contains(ninth));
    	this.eventi.removeEvent(ninth);
    }

    @Test
    public void equalsEventsTest() {

    	final Event firstCopy = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.DAILY);
    	assertEquals(first, firstCopy);

    	final Event secondCopy = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 9, 00), Repetition.BIMONTHLY);
    	assertEquals(second, secondCopy);
    }

    @Test
    public void findEventsTest() {
        this.eventi.addEvent(eight);
        assertEquals(this.eventi.getDailyEvents(), Set.of(eight));
        this.eventi.removeEvent(eight);
    }

    @Test
    public void findByDateTest() {
        try {
            this.eventi.addEvent(first);
            this.eventi.addEvent(third);
            this.eventi.addEvent(eight);
        }catch(IllegalStateException ignored){}
 
        assertEquals(this.eventi.findByDate(new LocalDate(2021, 9, 26)), List.of(first, eight, third));
        for(Event e : this.eventi.findByDate(new LocalDate(2021, 9, 26))){
            System.out.println(e.getName());
        }
        this.eventi.removeEvent(first);
        this.eventi.removeEvent(third);
        this.eventi.removeEvent(eight);
    }

    @Test
    public void verificationOfTimeAccuracyTest() {

        assertEquals(first.getStartHour(), new LocalTime(9, 30));
        assertEquals(first.getEndHour(), new LocalTime(10, 30));

        assertEquals(second.getStartHour(), new LocalTime(8, 30));
        assertEquals(second.getEndHour(), new LocalTime(9, 00));

        assertEquals(third.getStartHour(), new LocalTime(11, 30));
        assertEquals(third.getEndHour(), new LocalTime(18, 30));
    }

}
