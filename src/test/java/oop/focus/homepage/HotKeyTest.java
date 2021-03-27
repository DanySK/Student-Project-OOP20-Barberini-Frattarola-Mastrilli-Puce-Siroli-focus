package oop.focus.homepage;

import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.Repetition;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.homepage.model.ManagerEvent;
import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.homepage.model.ManagerHotKey;
import oop.focus.homepage.model.ManagerHotKeyImpl;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class HotKeyTest {

	private final DataSource dsi = new DataSourceImpl();
	private final ManagerEvent manager = new ManagerEventImpl(dsi);
	private final ManagerHotKey hotKeyTrackers = new ManagerHotKeyImpl(dsi, manager);

	/**
	 * This test is used to verify the correctness of adding and removing hot keys.
	 */
	@Test
	public void addingAndRemoveNewHotKeysTest() {
		final HotKey first = new HotKeyImpl("Discoteca", HotKeyType.EVENT);
		final HotKey second = new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY);
		final HotKey third = new HotKeyImpl("Bere", HotKeyType.COUNTER);
		
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
		final HotKey activity = new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY);
		final HotKey counter = new HotKeyImpl("Bere", HotKeyType.COUNTER);
		final HotKey event = new HotKeyImpl("Discoteca", HotKeyType.EVENT);

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
		final HotKey first = new HotKeyImpl("Shopping", HotKeyType.EVENT);
		final HotKey second = new HotKeyImpl("Addominali", HotKeyType.ACTIVITY);
		final HotKey third = new HotKeyImpl("Lavare i denti", HotKeyType.COUNTER);
		

		this.hotKeyTrackers.addAll(Set.of(first, second, third));

		assertEquals(this.hotKeyTrackers.getCategory(first), HotKeyType.EVENT);
		assertEquals(this.hotKeyTrackers.getCategory(second), HotKeyType.ACTIVITY);
		assertEquals(this.hotKeyTrackers.getCategory(third), HotKeyType.COUNTER);

		this.hotKeyTrackers.removeAll(Set.of(first, second, third));
	}
	
}
