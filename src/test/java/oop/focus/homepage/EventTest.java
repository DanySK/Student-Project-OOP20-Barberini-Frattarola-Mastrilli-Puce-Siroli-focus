package oop.focus.homepage;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import oop.focus.finance.CategoryImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerDegreeOfKinship;
import oop.focus.homepage.model.ManagerDegreeOfKinshipImpl;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.finance.Repetition;

public class EventTest {

    private final ManagerEvent eventi = new ManagerEventImpl();
    private final ManagerDegreeOfKinship gradiDiParentela = new ManagerDegreeOfKinshipImpl();

    @Test
    public void addingAndRemovingEventTest() {

        final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 26, 8, 30), new LocalDateTime(2021, 9, 26, 8, 45), Repetition.ONCE);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 9, 45), new LocalDateTime(2021, 9, 26, 10, 00), Repetition.ONCE);
        final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.ONCE);

        eventi.addEvent(first);
        assertEquals(eventi.getEvents(), Set.of(first));
        
        eventi.addEvent(second);
        assertEquals(eventi.getEvents(), Set.of(first, second));

        eventi.addEvent(third);
        assertEquals(eventi.getEvents(), Set.of(first, second));
        /*eventi.addEventsSet(Set.of(second, third, four));
        assertEquals(eventi.getEvents(), Set.of(first, second, third, four));

        eventi.removeEvent(first);
        assertEquals(eventi.getEvents(), Set.of(second, third, four));

        eventi.removeEventsSet(Set.of(second, third, four));
        assertEquals(eventi.getEvents(), Set.of());*/
    }
/*
    @Test
    public void compareTimeTable() {

    	final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.ONCE);
        final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.ONCE);

        assertEquals(first.getStartHour(), new LocalTime(9, 30));
        assertEquals(first.getEndHour(), new LocalTime(10, 30));
        assertEquals(second.getStartHour(), new LocalTime(8, 30));
        assertEquals(second.getEndHour(), new LocalTime(10, 30));
        assertEquals(third.getStartHour(), new LocalTime(11, 30));
        assertEquals(third.getEndHour(), new LocalTime(18, 30));
        assertEquals(four.getStartHour(), new LocalTime(19, 30));
        assertEquals(four.getEndHour(), new LocalTime(22, 45));
    }

    @Test
    public void findEventsByDateTest() {
    	final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.ONCE);

        this.eventi.addEventsSet(Set.of(first, second, third));
        assertEquals(this.eventi.findByDate(new LocalDate(2021, 9, 26)), List.of(third, first));
        this.eventi.removeEventsSet(Set.of(first, second, third));
    }

    @Test
    public void addNewPersonsTest() {
    	final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.ONCE);

        eventi.addEventsSet(Set.of(first, second, third));
        first.addPerson(new PersonImpl("Alessia", "Cugina", this.gradiDiParentela));
        assertEquals(this.gradiDiParentela.getAll(), Set.of("Cugina"));
        first.addPerson(new PersonImpl("Alessia", "Cugina", this.gradiDiParentela));
        assertEquals(this.gradiDiParentela.getAll().size(), 1);
        second.addPerson(new PersonImpl("Andrea", "Cugino", this.gradiDiParentela));
        assertEquals(this.gradiDiParentela.getAll(), Set.of("Cugina", "Cugino"));
        third.addPerson(new PersonImpl("Alice", "Mamma", this.gradiDiParentela));
        assertEquals(this.gradiDiParentela.getAll(), Set.of("Cugina", "Cugino", "Mamma"));
    }
    
    @Test
    public void equalsEventsTest() {
    	
    }
*/
}
