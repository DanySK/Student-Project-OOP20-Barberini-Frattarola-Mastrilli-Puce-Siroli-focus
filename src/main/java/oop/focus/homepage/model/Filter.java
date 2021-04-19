package oop.focus.homepage.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public final class Filter {

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

    public static List<Event> takeOnlyHotKeyEvent(final List<Event> eventsList) {
        return eventsList.stream().filter(e -> Filter.isAdequate(e)).collect(Collectors.toList());
    }

    public static List<Event> getEventsWithDuration(final List<Event> listOfEvents) {
        final TimeProperty time = new TimePropertyImpl();
        return listOfEvents.stream().filter(e -> time.getMinEventTime(e)).collect(Collectors.toList());
    }

    public static List<Event> orderByHour(final List<Event> eventsList) {
        eventsList.sort(Comparator.comparing(Event::getEnd));
        return eventsList;
    }
}
