package oop.focus.homepage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.common.Repetition;

public class EventMainTest {

        public static void main(final String... args) {

                final DataSource dsi = new DataSourceImpl();
                final EventManager eventi = new EventManagerImpl(dsi);

                Event repeated = new EventImpl("Repeat", new LocalDateTime(2021, 4, 13, 00, 00), new LocalDateTime(2021, 4, 13, 2, 00), Repetition.DAILY);
                try{
                        eventi.addEvent(repeated);
                } catch (IllegalStateException ignored){}
/*
                for(Event e : eventi.generateNext(repeated, LocalDate.now())){
                        System.out.println(e.getName());
                }
                for (Event e : eventi.generateListOfNextEvent(LocalDate.now())){
                        System.out.println(e.getName());
                        System.out.println(e.isRepeated());
                }
                */
                eventi.generateRepeatedEvents(LocalDate.now());;
                for (Event e : eventi.getAll()){
                        System.out.println(e.getName());
                        System.out.println(e.isRepeated());
                }
	}
}