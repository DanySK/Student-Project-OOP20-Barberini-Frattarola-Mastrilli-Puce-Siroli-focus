package oop.focus.event.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to filter events, for take only some events(like daily events or hot key events).
 */
public final class Filter {

    private Filter() {
    }

    public static List<Event> takeOnly(final List<Event> eventsList) {
        final TimeProperty time = new TimePropertyImpl();
        return eventsList.stream().filter(e -> time.getHourDuration(e) && !Filter.isAdequate(e)).collect(Collectors.toList());
    }

    private static boolean isAdequate(final Event event) {
        return event.getStart().isEqual(event.getEnd());
    }

    public static List<Event> takeOnlyDailyEvent(final List<Event> eventsList) {
        final TimeProperty time = new TimePropertyImpl();
        return eventsList.stream().filter(e -> !time.getHourDuration(e) && !Filter.isAdequate(e)).collect(Collectors.toList());
    }

    public static List<Event> getEventsWithDuration(final List<Event> listOfEvents) {
        final TimeProperty time = new TimePropertyImpl();
        return listOfEvents.stream().filter(time::getMinEventTime).collect(Collectors.toList());
    }

    public static List<Event> orderByHour(final List<Event> eventsList) {
        eventsList.sort(Comparator.comparing(Event::getEnd));
        return eventsList;
    }
}
