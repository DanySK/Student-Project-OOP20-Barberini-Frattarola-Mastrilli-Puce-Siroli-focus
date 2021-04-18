package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of {@link EventsStatisticFactory}.
 */
public class EventsStatisticFactoryImpl implements EventsStatisticFactory {

    private static final int MAX_HOURS = 24 * 60;
    private final DataSource dataSource;

    public EventsStatisticFactoryImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public final DataCreator<Event, Pair<String, Integer>> eventsOccurrences() {
        return new DataCreatorImpl<>(this.dataSource.getEvents().getAll(),
                (s) -> s.collect(Collectors.toMap(Event::getName, e -> 1,
                        Integer::sum)).entrySet().stream()
                        .map((a) -> new Pair<>(a.getKey(), a.getValue())).collect(Collectors.toSet()));
    }

    @Override
    public final DataCreator<Event, Pair<LocalDate, Integer>> eventTimePerDay(final String eventName) {
        var events = this.dataSource.getEvents().getAll();
        return new GeneratedDataCreator<>(() -> events.stream().filter(e -> e.getName().equals(eventName)).collect(Collectors.toSet()),
                s -> s.flatMap(this::getDividedEvents)
                        .collect(Collectors.toMap(Event::getStartDate, this::getDuration,
                                Integer::sum)).entrySet().stream()
                        .map((a) -> new Pair<>(a.getKey(), a.getValue() < MAX_HOURS ? a.getValue() : MAX_HOURS)).collect(Collectors.toSet()));
    }

    @Override
    public final DataCreator<LocalDate, Pair<LocalDate, Integer>> eventTimePerDay(final String eventName,
                                                                                  final LocalDate start,
                                                                                  final LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException();
        }
        var all = this.dataSource.getEvents().getAll();
        return new GeneratedDataCreator<>(
                () -> Stream.iterate(start, d -> d.plusDays(1))
                        .limit(1 + Math.abs(Days.daysBetween(start, end).getDays()))
                        .collect(Collectors.toSet()),
                s -> s.collect(Collectors.toMap(Function.identity(),
                        v -> all.stream()
                                .filter(e -> e.getName().equals(eventName))
                                .flatMap(this::getDividedEvents)
                                .filter(e -> e.getStartDate().equals(v))
                                .mapToInt(this::getDuration).sum()))
                        .entrySet().stream()
                        .map(p -> new Pair<>(p.getKey(), p.getValue())).collect(Collectors.toSet()));
    }

    private Integer getDuration(final Event e) {
        return Math.abs(Minutes.minutesBetween(e.getEnd(), e.getStart()).getMinutes());
    }

    private Stream<Event> getDividedEvents(final Event event) {
        var start = event.getStartDate();
        var end = event.getEndDate();
        if (!start.equals(end)) {
            var newDate = start.plusDays(1);
            var midDate = new LocalDateTime(newDate.getYear(), newDate.getMonthOfYear(), newDate.getDayOfMonth(), 0, 0, 0);
            return Stream.concat(Stream.of(new EventImpl(event.getName(), event.getStart(), midDate, event.getRipetition())),
                    this.getDividedEvents(new EventImpl(event.getName(), midDate, event.getEnd(), event.getRipetition())));
        }
        return Stream.of(event);
    }
}
