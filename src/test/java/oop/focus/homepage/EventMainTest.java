package oop.focus.homepage;

import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

public class EventMainTest {

        public static void main(final String... args) {

                final DataSource dsi = new DataSourceImpl();
                final EventManager eventi = new EventManagerImpl(dsi);
                final RelationshipsManager rel = new RelationshipsManagerImpl(dsi);
       
                final Event event = new EventImpl("Estetista", new LocalDateTime(2021, 4, 20, 11, 00), new LocalDateTime(2021, 4, 20, 13, 00), Repetition.ONCE);
                eventi.addEvent(event);
                
                rel.add("me");
                final Event eventImpl = new EventImpl("Estetista", new LocalDateTime(2021, 4, 20, 11, 00), new LocalDateTime(2021, 4, 20, 13, 00), Repetition.ONCE, List.of(new PersonImpl("Elisa", "me")));
                if (eventi.getAll().contains(eventImpl)) {
                    throw new IllegalArgumentException();
                } else {
                    try {
                    	eventi.addEvent(eventImpl);
                        eventi.generateRepeatedEvents(LocalDate.now());
                    } catch (final IllegalStateException e) {
                        throw new IllegalStateException();
                    }
                }
                eventi.removeEvent(event);
	}
}