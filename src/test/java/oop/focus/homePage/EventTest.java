package oop.focus.homePage;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.joda.time.*;
import org.junit.Test;
import oop.focus.homePage.model.Event;
import oop.focus.homePage.model.EventImpl;
import oop.focus.homePage.model.ManagerEvent;
import oop.focus.homePage.model.ManagerEventImpl;
import oop.focus.homePage.model.Ripetitions;

public class EventTest {
	
	private final ManagerEvent eventi = new ManagerEventImpl();
	
	@Test
	public void addingAndRemovingEventTest() {

	    final Event first = new EventImpl("Shopping", new LocalDate(2021, 9, 26), new LocalDate(2021, 9, 26), new LocalTime(9, 30), new LocalTime(10, 30), Ripetitions.NEVER);
	    final Event second = new EventImpl("Palestra", new LocalDate(2021, 9, 25), new LocalDate(2021, 9, 25), new LocalTime(8, 30), new LocalTime(10, 30), Ripetitions.NEVER);
	    final Event third = new EventImpl("Università", new LocalDate(2021, 9, 26), new LocalDate(2021, 9, 26), new LocalTime(11, 30), new LocalTime(18, 30), Ripetitions.NEVER);
	    final Event four = new EventImpl("Cinema", new LocalDate(2021, 9, 26), new LocalDate(2021, 9, 26), new LocalTime(19, 30), new LocalTime(22, 45), Ripetitions.NEVER);

	    eventi.addEvent(first);
	    assertEquals(eventi.getEvents(), Set.of(first));

	    eventi.addEventsSet(Set.of(second, third, four));
	    assertEquals(eventi.getEvents(), Set.of(first, second, third, four));

	    eventi.removeEvent(first);
	    assertEquals(eventi.getEvents(), Set.of(second, third, four));

	    eventi.removeEventsSet(Set.of(second, third, four));
	    assertEquals(eventi.getEvents(), Set.of());
	}
	
	@Test
	public void findEventTest() {
	    final Event first = new EventImpl("Shopping", new LocalDate(2021, 9, 26), new LocalDate(2021, 9, 26), new LocalTime(9, 30), new LocalTime(10, 30), Ripetitions.NEVER);
	    final Event second = new EventImpl("Palestra", new LocalDate(2021, 9, 25), new LocalDate(2021, 9, 25), new LocalTime(8, 30), new LocalTime(10, 30), Ripetitions.NEVER);
	    final Event third = new EventImpl("Università", new LocalDate(2021, 9, 26), new LocalDate(2021, 9, 26), new LocalTime(11, 30), new LocalTime(18, 30), Ripetitions.NEVER);
	    final Event four = new EventImpl("Cinema", new LocalDate(2021, 9, 26), new LocalDate(2021, 9, 26), new LocalTime(19, 30), new LocalTime(22, 45), Ripetitions.NEVER);

	    eventi.addEventsSet(Set.of(first, second, third, four));
	    assertEquals(eventi.findByDate(new LocalDate(2021, 9, 26)), Set.of(first, third, four));
	    assertEquals(eventi.findByDate(new LocalDate(2021, 9, 25)), Set.of(second));
	}
	
}
