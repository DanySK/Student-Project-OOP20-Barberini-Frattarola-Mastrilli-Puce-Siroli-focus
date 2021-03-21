package oop.focus.homepage;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyFactory;
import oop.focus.homepage.model.HotKeyFactoryImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.homepage.model.ManagerHotKey;
import oop.focus.homepage.model.ManagerHotKeyImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.Repetition;

public class HotKeyTest {

	private final DataSource dsi = new DataSourceImpl();
	private final ManagerEvent manager = new ManagerEventImpl(dsi);
	private final ManagerHotKey hotKeyTrackers = new ManagerHotKeyImpl(dsi, manager);
	private final HotKeyFactory factory = new HotKeyFactoryImpl();

	/**
	 * This test is used to verify the correctness of adding and removing hot keys.
	 */
	@Test
	public void addingAndRemoveNewHotKeysTest() {
		final HotKey first = factory.createEventHotKey("Shopping");
		final HotKey second = factory.createActivityHotKey("Allenamento");
		final HotKey third = factory.createCounterHotKey("Bere");
		
		this.hotKeyTrackers.add(first);
		assertEquals(this.hotKeyTrackers.getAll(), List.of(first));
		this.hotKeyTrackers.addAll(Set.of(second, third));
		assertEquals(this.hotKeyTrackers.getAll(), List.of(first, second, third));
		this.hotKeyTrackers.remove(first);
		assertEquals(this.hotKeyTrackers.getAll(), List.of(second, third));
		this.hotKeyTrackers.removeAll(Set.of(second, third));
		assertEquals(this.hotKeyTrackers.getAll(), List.of());
	}

	/**
	 * Each hot key when clicked creates a new event that must be stored in the database. 
	 * This test verifies that the event is created correctly.
	 */
	@Test
	public void createEventTest() {
		final HotKey activity = factory.createFromType("Allenamento", 2);
		final HotKey counter = factory.createFromType("Acqua", 3);
		final HotKey event = factory.createFromType("Discoteca", 1);

		final Event activityEvent = new EventImpl("Allenamento", LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE);
		final Event counterEvent = new EventImpl("Acqua", LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE);
        final Event eventHotKey = new EventImpl("Discoteca", LocalDate.now().toLocalDateTime(new LocalTime(15, 00)), LocalDate.now().toLocalDateTime(new LocalTime(16, 00)), Repetition.ONCE);

        this.hotKeyTrackers.addAll(Set.of(activity, counter));

        this.hotKeyTrackers.action(event, LocalDate.now().toLocalDateTime(new LocalTime(15, 00)), LocalDate.now().toLocalDateTime(new LocalTime(16, 00)));
		assertEquals(this.hotKeyTrackers.getEventsHotKey(), List.of(eventHotKey));

		this.hotKeyTrackers.action(activity, LocalDateTime.now(), LocalDateTime.now());
		assertEquals(this.hotKeyTrackers.getEventsHotKey(), List.of(eventHotKey, activityEvent));
        //provo a cliccare due volte sullo stesso bottone attività
		try {
			this.hotKeyTrackers.action(activity, LocalDateTime.now(), LocalDateTime.now());
		} catch (IllegalStateException ignored) {}

		this.hotKeyTrackers.action(counter, LocalDateTime.now(), LocalDateTime.now());
		assertEquals(this.hotKeyTrackers.getEventsHotKey(), List.of(eventHotKey, activityEvent, counterEvent));

		this.hotKeyTrackers.action(counter, LocalDateTime.now(), LocalDateTime.now());
		this.hotKeyTrackers.action(counter, LocalDateTime.now(), LocalDateTime.now());
		this.hotKeyTrackers.action(counter, LocalDateTime.now(), LocalDateTime.now());
		assertEquals(this.hotKeyTrackers.getTimes(counter.getName()), 4);

	}

	/**
	 * This test is used to verify the correctness in assigning the category.
	 */
	@Test
	public void hotKeyCategoryTest() {
		final HotKey first = factory.createEventHotKey("Discoteca");
		final HotKey second = factory.createActivityHotKey("Lavare i denti");
		final HotKey third = factory.createCounterHotKey("Acqua");

		this.hotKeyTrackers.addAll(Set.of(first, second, third));

		assertEquals(this.hotKeyTrackers.getCategory(first), HotKeyType.EVENT);
		assertEquals(this.hotKeyTrackers.getCategory(second), HotKeyType.ACTIVITY);
		assertEquals(this.hotKeyTrackers.getCategory(third), HotKeyType.COUNTER);

		this.hotKeyTrackers.removeAll(Set.of(first, second, third));
	}
	
}
