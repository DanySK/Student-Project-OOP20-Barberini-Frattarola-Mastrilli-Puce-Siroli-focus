package oop.focus.statistics.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticFactoryImpl implements StatisticFactory {

    private static final int MAX_HOURS = 24 * 60;
    private final DataSource dataSource;

    public StatisticFactoryImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public final DataCreator<Event, Pair<String, Integer>> getEventsOccurrences() {
        return new DataCreatorImpl<>(this.dataSource.getEvents().getAll(),
                (s) -> s.collect(Collectors.toMap(Event::getName, e -> 1,
                Integer::sum)).entrySet().stream()
                .map((a) -> new Pair<>(a.getKey(), a.getValue())).collect(Collectors.toSet()));
    }
    @Override
    public final DataCreator<Event, Pair<LocalDate, Integer>> getEventTimePerDay(final String eventName) {
        var events = this.dataSource.getEvents().getAll();
        ObservableList<Event> filtered = FXCollections.observableArrayList();
        this.linkList(e -> e.getName().equals(eventName), events, filtered);
        return new DataCreatorImpl<>(filtered,
                s -> s.flatMap(this::getDividedEvents)
                        .collect(Collectors.toMap(Event::getStartDate, this::getDuration,
                        Integer::sum)).entrySet().stream()
                        .map((a) -> new Pair<>(a.getKey(), a.getValue() < MAX_HOURS ? a.getValue() : MAX_HOURS)).collect(Collectors.toSet()));
    }

    private void linkList(final Predicate<Event> condition, final ObservableList<Event> events, final ObservableList<Event> filtered) {
        events.stream().filter(condition).forEach(filtered::add);
        events.addListener((ListChangeListener<Event>) c -> {
            while (c.next()) {
                if (c.wasRemoved()) {
                    filtered.removeAll(c.getRemoved());
                } else if (c.wasAdded()) {
                    c.getAddedSubList().stream().filter(condition)
                            .forEach(filtered::add);
                }
            }
        });
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
