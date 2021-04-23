package oop.focus.statistics.model;

import javafx.collections.ObservableSet;
import javafx.util.Pair;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventImpl;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of {@link EventsStatisticFactory}.
 */
public class EventsStatisticFactoryImpl implements EventsStatisticFactory {

    private static final int MAX_MINUTES = 24 * 60;
    private final ObservableSet<Event> events;

    public EventsStatisticFactoryImpl(final ObservableSet<Event> events) {
        this.events = events;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final DataCreator<Event, Pair<String, Integer>> eventsOccurrences() {
        return new DataCreatorImpl<>(this.events,
                (s) -> s.collect(Collectors.toMap(Event::getName, e -> 1,
                        Integer::sum)).entrySet().stream()
                        .map((a) -> new Pair<>(a.getKey(), a.getValue())).collect(Collectors.toSet()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final DataCreator<Event, Pair<LocalDate, Integer>> eventTimePerDay(final String eventName) {
        final var events = this.events;
        return new GeneratedDataCreator<>(() -> events.stream().filter(e -> e.getName().equals(eventName)).collect(Collectors.toSet()),
                s -> s.flatMap(this::getDividedEvents)
                        .collect(Collectors.toMap(Event::getStartDate, this::getDuration,
                                Integer::sum)).entrySet().stream()
                        .map((a) -> new Pair<>(a.getKey(), a.getValue() < MAX_MINUTES ? a.getValue() : MAX_MINUTES)).collect(Collectors.toSet()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final DataCreator<LocalDate, Pair<LocalDate, Integer>> eventTimePerDay(final String eventName,
                                                                                  final LocalDate start,
                                                                                  final LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException();
        }
        // avoid eclipse error by creating a variable : cannot infer type argument
        final Supplier<Set<LocalDate>> supplier = () -> Stream.iterate(start, d -> d.plusDays(1))
                .limit(1 + Math.abs(Days.daysBetween(start, end).getDays()))
                .collect(Collectors.toSet());
        final Function<Stream<LocalDate>, Set<Pair<LocalDate, Integer>>> function =
                s -> s.collect(Collectors.toMap(Function.identity(),
                        v -> this.events.stream()
                                .filter(e -> e.getName().equals(eventName))
                                .flatMap(this::getDividedEvents)
                                .filter(e -> e.getStartDate().equals(v))
                                .mapToInt(this::getDuration).sum()))
                        .entrySet().stream()
                        .map(p -> new Pair<>(p.getKey(), p.getValue())).collect(Collectors.toSet());
        return new GeneratedDataCreator<>(supplier, function);
    }

    private Integer getDuration(final Event e) {
        return Math.abs(Minutes.minutesBetween(e.getEnd(), e.getStart()).getMinutes());
    }

    /**
     * a recursive method useful for dividing an event that
     * occurs on different days into several under events each of which occurs within a day.
     *
     * @param event the event to be divided
     * @return the list of sub-events
     */
    private Stream<Event> getDividedEvents(final Event event) {
        final var start = event.getStartDate();
        final var end = event.getEndDate();
        if (!start.equals(end)) {
            final var newDate = start.plusDays(1);
            final var midDate = new LocalDateTime(newDate.getYear(), newDate.getMonthOfYear(), newDate.getDayOfMonth(), 0, 0, 0);
            return Stream.concat(Stream.of(new EventImpl(event.getName(), event.getStart(), midDate, event.getRepetition())),
                    this.getDividedEvents(new EventImpl(event.getName(), midDate, event.getEnd(), event.getRepetition())));
        }
        return Stream.of(event);
    }
}
