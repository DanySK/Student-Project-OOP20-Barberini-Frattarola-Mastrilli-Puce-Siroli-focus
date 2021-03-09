package oop.focus.homepage;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.homepage.model.Repetition;

public class EventTest {

    private final ManagerEvent eventi = new ManagerEventImpl();

    @Test
    public void addingAndRemovingEventTest() {

        final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.NEVER);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.NEVER);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.NEVER);
        final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.NEVER);

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
    	  final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.NEVER);
          final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.NEVER);
          final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.NEVER);
          final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.NEVER);

        eventi.addEventsSet(Set.of(first, second, third, four));
        assertEquals(eventi.findByDate(new LocalDate(2021, 9, 26)), Set.of(first, third, four));
        assertEquals(eventi.findByDate(new LocalDate(2021, 9, 25)), Set.of(second));
    }
}
