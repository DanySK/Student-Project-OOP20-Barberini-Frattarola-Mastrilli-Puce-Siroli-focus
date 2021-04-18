package oop.focus.statistics.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.util.Pair;
import oop.focus.common.Action;
import oop.focus.common.UpdatableController;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.statistics.model.EventsStatisticFactory;
import oop.focus.statistics.model.EventsStatisticFactoryImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static oop.focus.statistics.model.FinanceStatisticFactoryImpl.collectData;

/**
 * Implementation of {@link EventsChartFactory}.
 */
public class EventsChartFactoryImpl implements EventsChartFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final UpdatableController<TimePeriodInput<String>> eventsOccurrences(final DataSource dataSource) {
        EventsStatisticFactory factory = new EventsStatisticFactoryImpl(dataSource);
        final ObservableSet<Event> events = dataSource.getEvents().getAll();
        var data = factory.eventsOccurrences();
        return new AbstractSingleValueChartController<>() {
            private static final String TITLE = "Numero di occorrenze per evento";
            private boolean created = false;

            @Override
            public void updateInput(final TimePeriodInput<String> input) {
                if (!this.created) {
                    this.created = true;
                    Action update = () -> this.getChart().updateData(data.get().stream()
                            .map(p -> new Pair<>(p.getKey(), (double) p.getValue()))
                            .collect(Collectors.toList()));
                    this.getChart().setTitle(TITLE);
                    events.addListener((SetChangeListener<Event>) change -> {
                        try {
                            update.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    try {
                        update.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final UpdatableController<TimePeriodInput<String>> eventsTimePerDays(final DataSource dataSource) {
        EventsStatisticFactory factory = new EventsStatisticFactoryImpl(dataSource);
        return new AbstractMultiValueChartController<>() {
            private static final String TITLE = "Minuti giornalieri per evento";

            @Override
            public void updateInput(final TimePeriodInput<String> input) {
                if (!input.getValues().isEmpty()) {
                    this.updateWithInput(input);
                    this.getChart().setTitle(TITLE);
                }
            }

            private void updateWithInput(final TimePeriodInput<String> input) {
                final var accountsData = new ArrayList<List<Pair<String, Double>>>(input.getValues().size());
                final var names = new ArrayList<String>(input.getValues().size());
                for (var e : input.getValues()) {
                    var data = factory.eventTimePerDay(e,
                            input.getStartDate(), input.getEndDate());
                    accountsData.add(collectData(data.get().stream()
                                    .sorted(Comparator.comparing(Pair::getKey)),
                            x -> (double) x));
                    names.add(e);
                }
                this.getChart().updateData(accountsData);
                this.getChart().setNames(names);
            }
        };
    }
}
