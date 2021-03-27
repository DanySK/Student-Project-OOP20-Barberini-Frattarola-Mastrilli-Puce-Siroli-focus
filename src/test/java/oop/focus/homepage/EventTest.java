package oop.focus.homepage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.Repetition;

public class EventTest {

	private final DataSource dsi = new DataSourceImpl();
    private final ManagerEvent eventi = new ManagerEventImpl(dsi);
    private final Set<Event> set = new HashSet<>();

    private final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
    private final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 9, 00), Repetition.ONCE);
    private final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.ONCE);

    @Test
    public void addingAndRemovingEventTest() {
        //cerco di aggiungere questi eventi , vengono aggiunti tutti tranne fourth, fifth, sixth.
        final Event fourth = new EventImpl("ACQUA", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 9, 45), Repetition.ONCE);
        final Event fifth = new EventImpl("Ikea", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
        final Event sixth = new EventImpl("Spesa", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 6, 30), Repetition.ONCE);
        final Event seventh = new EventImpl("Gita", new LocalDateTime(2021, 9, 23, 9, 00), new LocalDateTime(2021, 9, 24, 9, 30), Repetition.ONCE);
        
        this.eventi.addEvent(first);
        this.set.add(first);
        assertEquals(this.eventi.getEvents(), this.set);

        this.eventi.addEvent(second);
        this.set.add(second);
        assertEquals(this.eventi.getEvents(), this.set);

        this.eventi.addEvent(third);
        this.set.add(third);
        assertEquals(this.eventi.getEvents(), this.set);

        try {
            this.eventi.addEvent(fourth);
        } catch (IllegalStateException ignored) {}

        assertEquals(this.eventi.getEvents(), this.set);

        try{
        	this.eventi.addEvent(fifth);
        } catch (IllegalStateException ignored) {}

        assertEquals(this.eventi.getEvents(), this.set);

        try{
        	this.eventi.addEvent(sixth);
        } catch (IllegalStateException ignored) {}
        //verifico che gli eventi vengano aggiunti correttamente.
        assertEquals(this.eventi.getEvents(), this.set);

        this.eventi.addEvent(seventh);
        this.set.addAll(this.eventi.getDailyEvents());
        //controllo che gli eventi vegano correttamente suddivisi tra giornalieri e non.
        assertTrue(this.eventi.getDailyEvents().contains(seventh));
    }

    //Test per ali, modificato per testare tutti e due i metodi
    @Test
    public void closestEventsTest() {
    	this.eventi.addEvent(first);
    	this.eventi.addEvent(third);
        //verifico che un timer possa iniziare 
    	assertTrue(this.eventi.canStart(new LocalDateTime(2021, 9, 26, 9, 27)));
    	assertFalse(this.eventi.canStart(new LocalDateTime(2021, 9, 26, 9, 45)));
        assertFalse(this.eventi.canStart(new LocalDateTime(2021, 9, 26, 18, 30)));
        //verifico che venga trovato correttamente l'orario più vicino.
        assertEquals(this.eventi.getClosestEvent(new LocalDateTime(2021, 9, 26, 9,27)), Optional.of(new LocalTime(9, 30)));
    }

    //prendo solo gli eventi che hanno durata superiore o uguale a 30 minuti.
    @Test
    public void durationInMinutes() {
    	final Event sixth = new EventImpl("Spesa", new LocalDateTime(2021, 9, 26, 9, 00), new LocalDateTime(2021, 9, 26, 9, 15), Repetition.ONCE);
    	
    	this.eventi.addEvent(first);
    	this.eventi.addEvent(second);
    	this.eventi.addEvent(sixth);

    	assertEquals(this.eventi.getEventsWithDuration(this.eventi.getEvents()), Set.of(first, second));

    	this.eventi.addEvent(third);
    	assertEquals(this.eventi.getEventsWithDuration(this.eventi.getEvents()), Set.of(first, second, third));
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

    	final Event fourth = new EventImpl("Studiare", new LocalDateTime(2021, 9, 25, 9, 30), new LocalDateTime(2021, 9, 26, 8, 00), Repetition.ONCE);
        final Event fifth = new EventImpl("Corsa", new LocalDateTime(2021, 9, 26, 14, 30), new LocalDateTime(2021, 9, 27, 13, 30), Repetition.ONCE);
        final Event sixth = new EventImpl("Estetista", new LocalDateTime(2021, 9, 26, 9, 00), new LocalDateTime(2021, 9, 26, 10, 00), Repetition.ONCE);
        final Event seventh = new EventImpl("Università", new LocalDateTime(2021, 9, 25, 8, 00), new LocalDateTime(2021, 9, 26,9 , 00), Repetition.ONCE);

        this.eventi.addEvent(fourth);
        assertEquals(this.eventi.getEvents(), Set.of(fourth));
        this.eventi.addEvent(fifth);
        assertEquals(this.eventi.getEvents(), Set.of(fourth, fifth));
        this.eventi.addEvent(seventh);
        assertEquals(this.eventi.getDailyEvents(), Set.of(seventh));
        this.eventi.addEvent(sixth);
        assertEquals(this.eventi.getEvents(), Set.of(fourth,fifth,sixth));
    }

    @Test
    public void findByDateTest() {
    	final Event fourth = new EventImpl("Studiare", new LocalDateTime(2021, 9, 25, 9, 30), new LocalDateTime(2021, 9, 26, 8, 00), Repetition.ONCE);
        final Event fifth = new EventImpl("Corsa", new LocalDateTime(2021, 9, 26, 14, 30), new LocalDateTime(2021, 9, 27, 13, 30), Repetition.ONCE);
        final Event sixth = new EventImpl("Spesa", new LocalDateTime(2021, 9, 26, 9, 00), new LocalDateTime(2021, 9, 26, 10, 00), Repetition.ONCE);
        final Event seventh = new EventImpl("Università", new LocalDateTime(2021, 9, 25, 8, 00), new LocalDateTime(2021, 9, 26,9 , 00), Repetition.ONCE);
        
        this.eventi.addEvent(fourth);
        this.eventi.addEvent(fifth);
        this.eventi.addEvent(sixth);
        this.eventi.addEvent(seventh);

        assertEquals(this.eventi.findByDate(new LocalDate(2021, 9, 25)), List.of(fourth, seventh));
        assertEquals(this.eventi.findByDate(new LocalDate(2021, 9, 26)), List.of(fourth, fifth, sixth, seventh));
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
