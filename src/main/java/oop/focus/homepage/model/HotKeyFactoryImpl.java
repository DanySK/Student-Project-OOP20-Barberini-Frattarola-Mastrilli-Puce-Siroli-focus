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

            public Event createEvent() {
                return new EventImpl(name, LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE);
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

            public Event createEvent() {
                return new EventImpl(name, LocalDateTime.now(), LocalDateTime.now(), Repetition.ONCE);
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

        public Event createEvent() {
            // crea un evento che prende in input (attraverso un metodo che verrà creato , l'orario di inizio e quello di fine).
            return null;
        }

        };
    }

    /**
     * This method is used to create a keyboard shortcut of a specific type based on the type id.
     * @param name is the name of the hot key to create.
     * @param id is the id of the type of the hot key.
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
