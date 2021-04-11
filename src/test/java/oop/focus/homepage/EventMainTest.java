package oop.focus.homepage;
import java.util.List;
import java.util.Set;

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
	    final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 25, 8, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 25, 7, 30), new LocalDateTime(2021, 9, 25, 18, 30), Repetition.ONCE);
        final Event fourth = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.ONCE);
        final Event fifth = new EventImpl("Spesa", new LocalDateTime(2021, 9, 25, 10, 00), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
        final Event sixth = new EventImpl("Passeggiata", new LocalDateTime(2021, 9, 25, 6, 30), new LocalDateTime(2021, 9, 25, 10, 30), Repetition.ONCE);
        final Event seventh = new EventImpl("Acqua", new LocalDateTime(2021, 9, 26, 22, 30), new LocalDateTime(2021, 9, 26, 23, 30), Repetition.ONCE);
        final Event eighth = new EventImpl("Ikea", new LocalDateTime(2021, 9, 25, 19, 30), new LocalDateTime(2021, 9, 25, 22, 45), Repetition.ONCE);
        final Event ninth = new EventImpl("Passeggio", new LocalDateTime(2021, 9, 25, 12, 30), new LocalDateTime(2021, 9, 25, 12, 45), Repetition.ONCE);
        final Event tenth = new EventImpl("Gita", new LocalDateTime(2021, 9, 20, 12, 00), new LocalDateTime(2021, 9, 26, 15, 00), Repetition.ONCE);
        final Event eleventh = new EventImpl("Prova", new LocalDateTime(2021, 9, 26,14, 30 ), new LocalDateTime(2021, 9, 26, 15, 30), Repetition.ONCE);
        final Event twelfth = new EventImpl("Uscita", new LocalDateTime(2021, 9, 26, 7, 30), new LocalDateTime(2021, 9, 26, 8, 30), Repetition.ONCE);
        final Event thirteenth = new EventImpl("Addominali", new LocalDateTime(2021, 9, 26, 17, 15), new LocalDateTime(2021, 9, 26, 18, 30), Repetition.ONCE);
        final Event fourteenth = new EventImpl("Bere", LocalDateTime.now(),  LocalDateTime.now(), Repetition.ONCE);

        final DataSource dsi = new DataSourceImpl();
        final EventManager manager = new EventManagerImpl(dsi);

        //cerco di aggiungere 14 eventi di cui sono validi solo first, second, fourth, eight, ninth , tenth, eleventh, twelve, thirteenth.
        manager.addEvent(first);
        manager.addEvent(second);
        try{
        	manager.addEvent(third);
        } catch (final IllegalStateException ignored) {}
        manager.addEvent(fourth);
        try{
        	manager.addEvent(fifth);
        } catch (final IllegalStateException ignored) {}
        try{
        	manager.addEvent(sixth);
        } catch (final IllegalStateException ignored) {}
        try{
        	manager.addEvent(seventh);
        } catch (final IllegalStateException ignored) {}
        manager.addEvent(eighth);
        manager.addEvent(ninth);
        manager.addEvent(tenth);
        try {
            manager.addEvent(eleventh);
        } catch (final IllegalStateException ignored) {}
        manager.addEvent(twelfth);
        manager.addEvent(thirteenth);
        manager.addEvent(fourteenth);
        //manager.addEvent(provaAli);

        final Set<Event> set = manager.getEvents();

        //mi dovrà stampare tutti gli eventi inseriti tranne quelli giornalieri(durata maggiire di 24 ore ) e quelli che hanno datae ora di inizio e fine equivalenti.
        for(final Event event : set) {
        	System.out.println(" " + event.getName());
        }
        System.out.println(" ");

        //mi deve stampare solo gli eventi previsti nella giornata del 26 settembre
        List<Event> eventsList = manager.findByDate(new LocalDate(2021, 9, 26));
        for(final Event event : eventsList) {
        	System.out.println(" " + event.getName());
        }
        System.out.println(" ");
        
        //deve stampare solo gli eventi con durata inferiore a 24 ore e quelli che hanno datae ora di inizio e fine equivalenti.
        eventsList = manager.takeOnly(eventsList);
        for(final Event event : eventsList) {
        	System.out.println(" " + event.getName());
        }
        System.out.println(" ");

        //deve stampare gli eventi in ordine in basa all'ora di inizio crescente.
        eventsList = manager.orderByHour(eventsList);
        for(final Event event : eventsList) {
        	System.out.println(" " + event.getName());
        }
        System.out.println(" ");

       //mi deve stampare solo gli eventi previsti nella giornata del 25 settembre
        eventsList = manager.findByDate(new LocalDate(2021, 9, 25));
        for(final Event event : eventsList) {
        	System.out.println(" " + event.getName());
        }
        System.out.println(" ");

        //deve stampare solo gli eventi con durata inferiore a 24 ore e quelli che hanno datae ora di inizio e fine equivalenti.
        eventsList = manager.takeOnly(eventsList);
        for(final Event event : eventsList) {
        	System.out.println(" " + event.getName());
        }
        System.out.println(" ");

        //deve stampare gli eventi in ordine in basa all'ora di inizio crescente.
        eventsList = manager.orderByHour(eventsList);
        for(final Event event : eventsList) {
        	System.out.println(" " + event.getName());
        }
        System.out.println(" ");
        
        //verifico il funzionamento del metodo closest events.
        System.out.println(" " + manager.getClosestEvent(new LocalDateTime(2021, 9, 25, 8, 00)));
        System.out.println(" " + manager.getClosestEvent(new LocalDateTime(2021, 9, 26, 11, 00)));
        System.out.println(" ");

        System.out.println(" " + manager.getClosestEvent(new LocalDateTime(2021, 9, 26, 14, 00)));
        System.out.println(" " + manager.getClosestEvent(new LocalDateTime(2021, 9, 26, 7, 00)));
        System.out.println(" " + manager.getClosestEvent(new LocalDateTime(2021, 9, 26, 19, 28)));

        System.out.println(" ");

	}
}