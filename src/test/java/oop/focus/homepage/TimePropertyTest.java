package oop.focus.homepage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventImpl;
import oop.focus.event.model.TimeProperty;
import oop.focus.event.model.TimePropertyImpl;
import oop.focus.common.Repetition;

public class TimePropertyTest {

	private final TimeProperty time = new TimePropertyImpl();
    
	/**
	 * This test is use to verify if an event could be added to a specific journey.
	 */
    @Test
    public void compatibleTest() {
    	final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
        final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 26, 8, 30), new LocalDateTime(2021, 9, 26, 9, 0), Repetition.ONCE);

        final List<Event> events = new ArrayList<>();
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
    	final Event second = new EventImpl("Compleanno", new LocalDateTime(2021, 9, 27, 0, 0), new LocalDateTime(2021, 9, 28, 0, 0), Repetition.ONCE);
    	final Event third = new EventImpl("Madrid", new LocalDateTime(2021, 9, 26, 12, 30), new LocalDateTime(2021, 9, 30, 9, 30), Repetition.ONCE);
    	final Event fourth = new EventImpl("Università", new LocalDateTime(2021, 9, 25, 9, 0), new LocalDateTime(2021, 9, 25, 16, 0), Repetition.ONCE);

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
        final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 9, 45), new LocalDateTime(2021, 9, 26, 10, 0), Repetition.ONCE);
        final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.ONCE);

    	assertTrue(this.time.getMinEventTime(first));
    	assertFalse(this.time.getMinEventTime(second));
    	assertFalse(this.time.getMinEventTime(third));
    	assertTrue(this.time.getMinEventTime(four));
    }

	
}
