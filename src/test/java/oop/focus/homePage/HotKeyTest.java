package oop.focus.homePage;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.joda.time.*;
import org.junit.Test;
import oop.focus.homePage.model.Event;
import oop.focus.homePage.model.EventImpl;
import oop.focus.homePage.model.HotKey;
import oop.focus.homePage.model.HotKeyFactory;
import oop.focus.homePage.model.HotKeyFactoryImpl;
import oop.focus.homePage.model.HotKeyImpl;
import oop.focus.homePage.model.HotKeyType;
import oop.focus.homePage.model.ManagerEvent;
import oop.focus.homePage.model.ManagerEventImpl;
import oop.focus.homePage.model.ManagerHotKey;
import oop.focus.homePage.model.ManagerHotKeyImpl;
import oop.focus.homePage.model.Ripetitions;

public class HotKeyTest {
	
	private final ManagerHotKey hotKeys = new ManagerHotKeyImpl();
	private final HotKeyFactory factory = new HotKeyFactoryImpl();
	
	@Test
	public void addedHotKeysTest() {
	   /*final Event first = new EventImpl("Allenamento", LocalDate.now(), LocalDate.now(), LocalTime.now(), LocalTime.now(), Ripetitions.NON_SI_RIPETE);
	    HotKey firstHotKey = this.factory.createActivityHotKey("Allenamento", first);
	    firstHotKey.action();
	    assertEquals(true, firstHotKey.getState());//risolvi il problema*/

	}
	
	
	
}
