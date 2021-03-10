package oop.focus.homepage;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalDate;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.homepage.model.Repetition;

public class EventMainTest {
	
	public static void main(final String... args) {
	
		final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.NEVER);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.NEVER);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 25, 7, 30), new LocalDateTime(2021, 9, 25, 18, 30), Repetition.NEVER);
        final Event fourth = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.NEVER);
        final Event fifth = new EventImpl("Spesa", new LocalDateTime(2021, 9, 25, 10, 00), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.NEVER);
        final Event sixth = new EventImpl("Passeggiata", new LocalDateTime(2021, 9, 25, 6, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.NEVER);
        final Event seventh = new EventImpl("Acqua", new LocalDateTime(2021, 9, 25, 22, 30), new LocalDateTime(2021, 9, 25, 18, 30), Repetition.NEVER);
        final Event eighth = new EventImpl("Ikea", new LocalDateTime(2021, 9, 25, 19, 30), new LocalDateTime(2021, 9, 25, 22, 45), Repetition.NEVER);

        final ManagerEvent manager = new ManagerEventImpl();

        manager.addEventsSet(Set.of(first, second, third, fourth, fifth, sixth, seventh, eighth));

        final List<Event> trovate = manager.findByDate(new LocalDate(2021, 9, 25));

        final List<Event> ordinata = manager.orderList(trovate);

        for(int i = 0; i < ordinata.size(); i++) {
        	System.out.println(" " + ordinata.get(i).getName());
        }
	}
}