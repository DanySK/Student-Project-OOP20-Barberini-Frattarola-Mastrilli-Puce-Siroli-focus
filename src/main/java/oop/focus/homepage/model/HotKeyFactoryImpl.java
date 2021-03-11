package oop.focus.homepage.model;

import org.joda.time.LocalDateTime;

/**
 * This class implements the HotKeyFactory interface.
 */
public class HotKeyFactoryImpl implements HotKeyFactory {

    public final HotKey createActivityHotKey(final String name) {
        return new HotKeyImpl(name, HotKeyType.ACTIVITY) {
        //lo devi generare solola prima volta quindi in caso devi generare una eccezione.
        public Event createEvent() {
            return new EventImpl(name, LocalDateTime.now(), LocalDateTime.now(), Repetition.NEVER);
        }

        };
    }

    public final HotKey createCounterHotKey(final String name) {
        return new HotKeyImpl(name, HotKeyType.COUNTER) {

            public Event createEvent() {
                return new EventImpl(name, LocalDateTime.now(), LocalDateTime.now(), Repetition.NEVER);
            }

        };
    }

    public final HotKey createEventHotKey(final String name) {
        return new HotKeyImpl(name, HotKeyType.EVENT) {

        public Event createEvent() {
            // crea un evento che prende in input (attraverso un metodo che verrà creato , l'orario di inizio e quello di fine).
            return null;
        }

        };
    }

}
