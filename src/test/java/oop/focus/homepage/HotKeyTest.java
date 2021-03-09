package oop.focus.homepage;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import org.junit.Test;

import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.homepage.model.ManagerActivity;
import oop.focus.homepage.model.ManagerCounter;
import oop.focus.homepage.model.ManagerEventHotKey;
import oop.focus.homepage.model.ManagerHotKey;
import oop.focus.homepage.model.ManagerHotKeyImpl;


public class HotKeyTest {

    private final ManagerCounter counterHotKey = new ManagerCounter();
    private final ManagerActivity activityHotKey = new ManagerActivity();
    private final ManagerEventHotKey eventHotKey = new ManagerEventHotKey();
    private final ManagerHotKey hotKeyTrack = new ManagerHotKeyImpl();

    @Test
    public void addedHotKeysTest() {

        final HotKey event = new HotKeyImpl("Shopping", HotKeyType.EVENT);
        final HotKey counter = new HotKeyImpl("Bere", HotKeyType.COUNTER);
        final HotKey activity = new HotKeyImpl("Palestra", HotKeyType.ACTIVITY);

        this.hotKeyTrack.addAll(Set.of(activity, counter, event));
        assertEquals(this.hotKeyTrack.getAll(), Set.of(activity, counter, event));

        this.hotKeyTrack.moveToCategory(event, counterHotKey, activityHotKey, eventHotKey);
        this.hotKeyTrack.moveToCategory(activity, counterHotKey, activityHotKey, eventHotKey);
        this.hotKeyTrack.moveToCategory(counter, counterHotKey, activityHotKey, eventHotKey);

        this.hotKeyTrack.action(activity, counterHotKey, activityHotKey, eventHotKey);
        assertEquals(activityHotKey.getStatus(activity), true);
        this.hotKeyTrack.action(counter, counterHotKey, activityHotKey, eventHotKey);
        this.hotKeyTrack.action(counter, counterHotKey, activityHotKey, eventHotKey);
        this.hotKeyTrack.action(counter, counterHotKey, activityHotKey, eventHotKey);
        assertEquals(counterHotKey.getValue(counter), Integer.valueOf(3));
        this.hotKeyTrack.action(event, counterHotKey, activityHotKey, eventHotKey);
        assertEquals(this.eventHotKey.getHotKeys(), Set.of(event));
    }

    @Test
    public void recognitionOfTheTypeOfAnHotKey() {
        final HotKey event = new HotKeyImpl("Shopping", HotKeyType.EVENT);
        final HotKey counter = new HotKeyImpl("Bere", HotKeyType.COUNTER);
        final HotKey activity = new HotKeyImpl("Palestra", HotKeyType.ACTIVITY);

        this.hotKeyTrack.removeFromCategory(activity, counterHotKey, activityHotKey, eventHotKey);
        assertEquals(this.activityHotKey.getHotKeys(), Set.of());
        this.hotKeyTrack.removeFromCategory(counter, counterHotKey, activityHotKey, eventHotKey);
        assertEquals(this.counterHotKey.getHotKeys(), Set.of());
        this.hotKeyTrack.removeFromCategory(event, counterHotKey, activityHotKey, eventHotKey);
        assertEquals(this.eventHotKey.getHotKeys(), Set.of());
    }
}
