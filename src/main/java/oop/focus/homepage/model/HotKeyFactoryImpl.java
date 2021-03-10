package oop.focus.homepage.model;

import org.joda.time.LocalDateTime;

/**
 * This class implements the HotKeyFactory interface.
 */
public class HotKeyFactoryImpl implements HotKeyFactory {

	public final HotKey createCounterHotKey(final String name) {
		return new HotKeyImpl(name, HotKeyType.COUNTER) {

			@Override
			public void createEvent() {
				final Event counterEvent = new EventImpl(name, LocalDateTime.now(), LocalDateTime.now(), Repetition.NEVER);
				//salvo l'evento nel database
			}
		};
	}

	public final HotKey createEventHotKey(final String name) {
		return new HotKeyImpl(name, HotKeyType.EVENT) {

			public void createEvent() {
				// creerò un nuovo evento prendendo in input l'orario di inizio e l'orario di fine
			}
		};
	}

	public final HotKey createActivityHotKey(final String name) {
		return new HotKeyImpl(name, HotKeyType.ACTIVITY) {

			private boolean state = false;

			public void createEvent() {
				this.state = !this.state;
				if (this.state) {
					final Event activityEvent = new EventImpl(name, LocalDateTime.now(), LocalDateTime.now(), Repetition.NEVER);
					//poi lo salvo sul db
				}
			}
		};
	}

}
