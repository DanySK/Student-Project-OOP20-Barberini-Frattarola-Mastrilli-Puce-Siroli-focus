package oop.focus.homepage;

import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.homepage.model.HotKeyImpl;

import org.joda.time.LocalDateTime;
import org.junit.Test;



public class PopulateEventAndHotKey {

    private final DataSource dsi = new DataSourceImpl();
    private final EventManager event = new EventManagerImpl(dsi);
    private final HotKeyManager hotKey = new HotKeyManagerImpl(dsi, event);

    @Test
    public void populateEvent(){
        final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9,26,9,30), new LocalDateTime(2021, 9,26,10,30), Repetition.ONCE);
        final Event second = new EventImpl("Allenamento" , new LocalDateTime(2021, 4, 11, 7,30), new LocalDateTime(2021, 4, 11, 7,30), Repetition.ONCE);
        final Event third =new EventImpl("Gita", new LocalDateTime(2021, 9,26,9,30), new LocalDateTime(2021, 9,27,10,30), Repetition.ONCE);
        final Event fourth = new EventImpl("Bere", new LocalDateTime(2021, 4,10,9,30), new LocalDateTime(2021, 4,10,9,30), Repetition.ONCE);
        final Event fifth = new EventImpl("Bere", new LocalDateTime(2021, 8,10,9,30), new LocalDateTime(2021, 8,10,9,30), Repetition.ONCE);

        this.event.addEvent(first);
        this.event.addEvent(second);
        this.event.addEvent(third);
        this.event.addEvent(fourth);
        this.event.addEvent(fifth);
    }

    @Test
    public void populateHotKey(){
        final HotKey uno = new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY);
        final HotKey due = new HotKeyImpl("Shopping", HotKeyType.EVENT);
        final HotKey tre = new HotKeyImpl("Bere", HotKeyType.COUNTER);
        final HotKey quattro = new HotKeyImpl("Spesa", HotKeyType.ACTIVITY);
        final HotKey cinque = new HotKeyImpl("Addominali", HotKeyType.COUNTER);

        this.hotKey.add(uno);
        this.hotKey.add(due);
        this.hotKey.add(tre);
        this.hotKey.add(quattro);
        this.hotKey.add(cinque);
    }
}
