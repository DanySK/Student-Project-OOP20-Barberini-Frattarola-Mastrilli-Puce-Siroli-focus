package oop.focus.homepage;

import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.homepage.model.HotKeyType;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

		this.hotKeyTrackers.add(first);
		this.hotKeyTrackers.add(second);
		this.hotKeyTrackers.add(third);

		assertTrue(this.hotKeyTrackers.getAll().contains(first));
		assertTrue(this.hotKeyTrackers.getAll().contains(second));
		assertTrue(this.hotKeyTrackers.getAll().contains(third));


		this.hotKeyTrackers.remove(first);
		this.hotKeyTrackers.remove(second);
		this.hotKeyTrackers.remove(third);
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
