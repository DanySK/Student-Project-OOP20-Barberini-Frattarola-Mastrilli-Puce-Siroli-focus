package oop.focus.calendar;

import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

import org.joda.time.LocalDateTime;
import org.junit.Test;

public class PopulateCalendar {
    private final DataSource dsi = new DataSourceImpl();
    private final EventManager eventi = new EventManagerImpl(dsi);

    private final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 4, 10, 9, 30), new LocalDateTime(2021, 4, 10, 10, 30), Repetition.ONCE);
    private final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 4, 10, 8, 30), new LocalDateTime(2021, 4, 11, 9, 00), Repetition.ONCE);
    private final Event third = new EventImpl("Università", new LocalDateTime(2021, 5, 26, 11, 30), new LocalDateTime(2021, 5, 26, 18, 30), Repetition.ONCE);
    private final Event fourth = new EventImpl("ACQUA", new LocalDateTime(2021, 5, 26, 9, 30), new LocalDateTime(2021, 5, 26, 9, 45), Repetition.ONCE);
    private final Event fifth = new EventImpl("Ikea", new LocalDateTime(2021, 3, 26, 9, 30), new LocalDateTime(2021, 3, 25, 10, 30), Repetition.ONCE);
    private final Event sixth = new EventImpl("Spesa", new LocalDateTime(2021, 3, 26, 9, 30), new LocalDateTime(2021, 3, 26, 6, 30), Repetition.ONCE);
    private final Event seventh = new EventImpl("Gita", new LocalDateTime(2021, 2, 14, 9, 00), new LocalDateTime(2021, 2, 14, 9, 30), Repetition.ONCE);
    private final Event eight = new EventImpl("Estetista", new LocalDateTime(2021, 4, 10, 9, 00), new LocalDateTime(2021, 4, 10, 10, 00), Repetition.ONCE);
    private final Event ninth = new EventImpl("Cane", new LocalDateTime(2021, 3, 26, 9, 00), new LocalDateTime(2021, 3, 26, 9, 15), Repetition.ONCE);

    @Test
    public void populateCalendar(){
        try {
            this.eventi.addEvent(first);
            this.eventi.addEvent(second);
            this.eventi.addEvent(third);
            this.eventi.addEvent(fourth);
            this.eventi.addEvent(fifth);
            this.eventi.addEvent(sixth);
            this.eventi.addEvent(seventh);
            this.eventi.addEvent(eight);
            this.eventi.addEvent(ninth);
        } catch (IllegalStateException ignored){}
    }


}
