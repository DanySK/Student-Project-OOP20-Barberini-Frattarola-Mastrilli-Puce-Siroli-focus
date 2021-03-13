package oop.focus.homepage;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import oop.focus.finance.CategoryImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyFactory;
import oop.focus.homepage.model.HotKeyFactoryImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.homepage.model.ManagerHotKey;
import oop.focus.homepage.model.ManagerHotKeyImpl;
import oop.focus.finance.Repetition;

public class HotKeyTest {

	final ManagerHotKey hotKeyTrackers = new ManagerHotKeyImpl();
	final HotKeyFactory factory = new HotKeyFactoryImpl();

	@Test
	public void addingAndRemoveNewHotKeysTest() {
		final HotKey first = factory.createEventHotKey("Shopping");
		final HotKey second = factory.createActivityHotKey("Allenamento");
		final HotKey third = factory.createCounterHotKey("Bere");
		
		this.hotKeyTrackers.add(first);
		assertEquals(this.hotKeyTrackers.getAll(), Set.of(first));
		this.hotKeyTrackers.addAll(Set.of(second, third));
		assertEquals(this.hotKeyTrackers.getAll(), Set.of(first, second, third));
		this.hotKeyTrackers.remove(first);
		assertEquals(this.hotKeyTrackers.getAll(), Set.of(second, third));
		this.hotKeyTrackers.removeAll(Set.of(second, third));
		assertEquals(this.hotKeyTrackers.getAll(), Set.of());
	}

	@Test
	public void hotKeyCategoryTest() {
		final HotKey first = factory.createEventHotKey("Discoteca");
		final HotKey second = factory.createActivityHotKey("Allenamento");
		final HotKey third = factory.createCounterHotKey("Acqua");

		this.hotKeyTrackers.addAll(Set.of(first, second, third));
		assertEquals(this.hotKeyTrackers.getCategory(first), HotKeyType.EVENT);
		assertEquals(this.hotKeyTrackers.getCategory(second), HotKeyType.ACTIVITY);
		assertEquals(this.hotKeyTrackers.getCategory(third), HotKeyType.COUNTER);
		this.hotKeyTrackers.removeAll(Set.of(first, second, third));
	}

	/**
	 * Each shortcut key when clicked creates a new event that must be stored in the database. 
	 */
	@Test
	public void createEventTest() {
		final HotKey first = factory.createActivityHotKey("Allenamento");
		final HotKey second = factory.createCounterHotKey("Acqua");
		final Event activityEvent = new EventImpl("Allenamento", LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE);
		final Event counterEvent = new EventImpl("Acqua", LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE);

		this.hotKeyTrackers.addAll(Set.of(first, second));
		final Event activity = this.hotKeyTrackers.action(first);
		assertEquals(activity, activityEvent);
		final Event counter = this.hotKeyTrackers.action(second);
		assertEquals(counter, counterEvent);

	}
	
}
