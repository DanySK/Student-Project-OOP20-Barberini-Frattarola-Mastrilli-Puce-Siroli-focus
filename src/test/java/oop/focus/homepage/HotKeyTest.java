package oop.focus.homepage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.common.Repetition;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.homepage.model.HotKeyType;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class HotKeyTest {

	private final DataSource dsi = new DataSourceImpl();
	private final EventManager manager = new EventManagerImpl(dsi);
	private final HotKeyManager hotKeyTrackers = new HotKeyManagerImpl(dsi, manager);

	/**
	 * This test is used to verify the correctness of adding and removing hot keys.
	 */
	@Test
	public void addingAndRemoveNewHotKeysTest() {
		final HotKey first = new HotKeyImpl("Discoteca", HotKeyType.EVENT);
		final HotKey second = new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY);
		final HotKey third = new HotKeyImpl("Bere", HotKeyType.COUNTER);

		ObservableSet<HotKey> set;
		this.hotKeyTrackers.add(first);
		this.hotKeyTrackers.add(second);
		this.hotKeyTrackers.add(third);

		 set = FXCollections.observableSet(first, second, third);
		assertEquals(this.hotKeyTrackers.getAll(), set);

		this.hotKeyTrackers.remove(first);
		this.hotKeyTrackers.remove(second);
		this.hotKeyTrackers.remove(third);

		set = FXCollections.emptyObservableSet();
		assertEquals(this.hotKeyTrackers.getAll(), set);
	}

	/**
	 * This test is used to verify the correctness in assigning the category.
	 */
	@Test
	public void hotKeyCategoryTest() {
		final HotKey first = new HotKeyImpl("Shopping", HotKeyType.EVENT);
		final HotKey second = new HotKeyImpl("Addominali", HotKeyType.ACTIVITY);
		final HotKey third = new HotKeyImpl("Lavare i denti", HotKeyType.COUNTER);
		
        this.hotKeyTrackers.add(first);
        this.hotKeyTrackers.add(second);
        this.hotKeyTrackers.add(third);

		assertEquals(this.hotKeyTrackers.getCategory(first), HotKeyType.EVENT);
		assertEquals(this.hotKeyTrackers.getCategory(second), HotKeyType.ACTIVITY);
		assertEquals(this.hotKeyTrackers.getCategory(third), HotKeyType.COUNTER);

		this.hotKeyTrackers.remove(first);
		this.hotKeyTrackers.remove(second);
		this.hotKeyTrackers.remove(third);
	}

}
