package oop.focus.homepage.model;

import org.joda.time.LocalDateTime;

import oop.focus.finance.Repetition;


/**
 * This class implements the HotKeyFactory interface.
 */
public class HotKeyFactoryImpl implements HotKeyFactory {

    /**
     * This method is use for create an activity hotKey.
     * @param name is the name of the hot key to create.
     * @return an hot key of type activity.
     */
    public final HotKey createActivityHotKey(final String name) {
        return new HotKeyImpl(name, HotKeyType.ACTIVITY) {
            private boolean possible = false;
            //un tasto attività può cambiare il suo stato solo una volta quindi nel caso si cercasse di farlo più volte verrebbe lanciata un eccezione.
            public Event createEvent(final LocalDateTime start, final LocalDateTime end) {
                if (!this.possible) {
                    this.possible = !this.possible;
                    return new EventImpl(name, start, end, Repetition.ONCE);
                } 
                throw new IllegalStateException();
            }
        };
    }

    /**
     * This method is use for create a counter hotKey. 
     * @param name is the name of the counter hot key to create.
     * @return an hot key of type counter.
     */
    public final HotKey createCounterHotKey(final String name) {
        return new HotKeyImpl(name, HotKeyType.COUNTER) {

            public Event createEvent(final LocalDateTime start, final LocalDateTime end) {
                return new EventImpl(name, start, end, Repetition.ONCE);
            }
        };
    }

    /**
     * This method is use for create an event hotKey.
     * @param name is the name of the hot key to create.
     * @return an hot key of type event.
     */
    public final HotKey createEventHotKey(final String name) {
        return new HotKeyImpl(name, HotKeyType.EVENT) {
 
            public Event createEvent(final LocalDateTime start, final LocalDateTime end) {
                return new EventImpl(name, start, end, Repetition.ONCE);
            }
        };
    }

    /**
     * This method is used to create a keyboard shortcut of a specific type based on the type id.
     * @param name is the name of the hot key to create.
     * @param id is the id of the hot key type.
     * @return an hot key of type x (where x is the type of hot key specified by the id).
     */
    public final HotKey createFromType(final String name, final int id) {
        final HotKeyType type = HotKeyType.values()[id];
        switch (type) {
            case ACTIVITY :
                return this.createActivityHotKey(name);
            case COUNTER :
                return this.createCounterHotKey(name);
            case EVENT :
                return this.createEventHotKey(name);
            default :
                return null;
        }
    }

}
