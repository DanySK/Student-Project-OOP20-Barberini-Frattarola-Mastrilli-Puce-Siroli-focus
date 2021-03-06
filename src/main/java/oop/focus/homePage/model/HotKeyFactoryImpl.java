package oop.focus.homePage.model;

/**
 * 
 * This class implements the HotKeyFactory interface.
 */
public class HotKeyFactoryImpl implements HotKeyFactory {

   /**
    * 
    * This method is use for create a counter hotKey.
    * The counter hotkey has a field that keeps a count of the times it has been clicked.
    */
	@Override
	public final HotKey createCounterHotKey(final String name, final Event event) {
		return new HotKeyImpl(name, HotKeyType.COUNTER, event) {

			private int count = 0; 

			/**
			 * 
			 * This method is use for increment the counter value.
			 */
			@Override
			public void action() {
				this.count++;
			}

			/**
			 * 
			 * This method is use for return the counter value.
			 * @return an int value..
			 */
			public int getCount() {
			    return this.count;
			}

		};
	}

	/**
	 * 
	 * This method is use for create an event hotKey.
	 * The event hotkey is use for add new event faster.
	*/
	@Override
        public final HotKey createEventHotKey(final String name, final Event event) {
		return new HotKeyImpl(name, HotKeyType.EVENT, event) {

			@Override
			public void action() {
			}

		};
	}

	/**
	 * 
         * This method is use for create an activity hotKey.
         * The activity hotkey has a field that is updated when that particular activity has been performed.
        */
	@Override
	public HotKey createActivityHotKey(final String name, final Event event) {
		return new HotKeyImpl(name, HotKeyType.ACTIVITY, event) {

			private boolean state = false;

			/**
			 * 
                         * This method is use for update the state value.
                         */
			@Override
			public void action() {
				this.state = !this.state;
			}

			/**
			 * 
                         * This method is use for return the state value.
                         * @return a boolean value.
                         */
			public boolean getState() {
			    return this.state;
			}

		};
	}

}
