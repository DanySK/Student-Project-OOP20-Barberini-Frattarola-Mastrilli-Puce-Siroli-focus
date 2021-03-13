package oop.focus.homepage.model;



import java.util.List;

import org.joda.time.Duration;

public class TimeProperty {

    private final Duration minimumEventDuration;
    private final int minimunDuration = 30;
    private final int minuteDistance = 5;

    public TimeProperty() {
        this.minimumEventDuration = Duration.standardMinutes(this.minimunDuration);
    }

    public final boolean getMinEventTime(final Event event) {
        final Duration durationEvent = new Duration(event.getStartHour().toDateTimeToday(), event.getEndHour().toDateTimeToday());
        if (durationEvent.isEqual(this.minimumEventDuration) || durationEvent.isLongerThan(this.minimumEventDuration)) {
            return true;
        }
        return false;
    }	

    public final boolean areCompatible(final Event e1, final List<Event> eventsList) {
    	if (e1.getStartHour().isBefore(eventsList.get(0).getStartHour()) && e1.getEndHour().isBefore(eventsList.get(0).getStartHour())) {
    		return true;
    	} else if (e1.getStartHour().isAfter(eventsList.get(eventsList.size() - 1).getStartHour())) {
    		return true;
    	} else {
    		for (int i = 0; i < eventsList.size(); i++) {
    			if (e1.getStartHour().isAfter(eventsList.get(i).getEndHour()) && e1.getEndHour().isBefore(eventsList.get(i + 1).getStartHour())) {
    				return true;
    			}
    		}
    	}
		return false;
	}
	
    public final int getMinuteDistance() {
		return this.minuteDistance;
	}
}
