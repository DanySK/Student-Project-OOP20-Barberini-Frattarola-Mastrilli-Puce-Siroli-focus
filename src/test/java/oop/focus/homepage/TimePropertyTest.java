package oop.focus.homepage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.Filter;
import oop.focus.homepage.model.TimeProperty;
import oop.focus.homepage.model.TimePropertyImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.common.Repetition;

public class TimePropertyTest {

	private final TimeProperty time = new TimePropertyImpl();
	private final DataSource dsi = new DataSourceImpl();
	private final EventManager manager = new EventManagerImpl(dsi);
    
	/**
	 * This test is use to verify if an event could be added to a specific journey.
	 */
    @Test
    public void compatibleTest() {
    	final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 26, 8, 30), new LocalDateTime(2021, 9, 26, 9, 00), Repetition.ONCE);

        List<Event> events = new ArrayList<>();
        assertTrue(this.time.areCompatibleEquals(first, events));
        events.add(first);

        assertTrue(this.time.areCompatibleEquals(second, events));
        events.add(second);
    }

    /**
     * This test is use to verify that a specific event have a duration under 24 hours.
     */
    @Test
    public void durationHourTest() {
    	final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 11, 30), Repetition.ONCE);
    	final Event second = new EventImpl("Compleanno", new LocalDateTime(2021, 9, 27, 00, 00), new LocalDateTime(2021, 9, 28, 00, 00), Repetition.ONCE);
    	final Event third = new EventImpl("Madrid", new LocalDateTime(2021, 9, 26, 12, 30), new LocalDateTime(2021, 9, 30, 9, 30), Repetition.ONCE);
    	final Event fourth = new EventImpl("Università", new LocalDateTime(2021, 9, 25, 9, 00), new LocalDateTime(2021, 9, 25, 16, 0), Repetition.ONCE);

    	assertTrue(this.time.getHourDuration(first));
    	assertFalse(this.time.getHourDuration(second));
    	assertFalse(this.time.getHourDuration(third));
    	assertTrue(this.time.getHourDuration(fourth));
    }

	@Test
    public void minuteDistanceTest() {
    	final int minuteDistance = 5;
    	assertEquals(this.time.getMinuteDistance(), minuteDistance);
    }

	/**
	 * This test is use to verify if an event has a duration higher or equal than 30 minutes.
	 */
	@Test
    public void respectMinimumDurationTest() {
    	final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 26, 8, 30), new LocalDateTime(2021, 9, 26, 8, 45), Repetition.ONCE);
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 9, 45), new LocalDateTime(2021, 9, 26, 10, 00), Repetition.ONCE);
        final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.ONCE);

    	assertTrue(this.time.getMinEventTime(first));
    	assertFalse(this.time.getMinEventTime(second));
    	assertFalse(this.time.getMinEventTime(third));
    	assertTrue(this.time.getMinEventTime(four));
    }

    private List<Event> refreshList(final Event event, final List<Event> events) {
		events.add(event);
		return events;
	}

	
}
