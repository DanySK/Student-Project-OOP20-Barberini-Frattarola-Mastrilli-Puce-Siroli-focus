package oop.focus.statistics.controller;

import javafx.util.Pair;
import oop.focus.db.DataSource;
import oop.focus.statistics.model.EventsStatisticFactory;
import oop.focus.statistics.model.EventsStatisticFactoryImpl;

import java.util.stream.Collectors;

/**
 * A factory for different {@link ChartController} that uses {@link oop.focus.statistics.view.SingleValueChart}.
 */
public class EventsSingleValueChartFactoryImpl implements EventsSingleValueChartFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final ChartController<EventsInput> eventsOccurrences(final DataSource dataSource) {
        EventsStatisticFactory factory = new EventsStatisticFactoryImpl(dataSource);
        var data = factory.eventsOccurrences();
        return new AbstractPieChartController<>() {
            private static final String TITLE = "Numero di occorrenze per evento";

            @Override
            public void updateInput(final EventsInput input) {
                this.getChart().updateData(data.get().stream()
                        .map(p -> new Pair<>(p.getKey(), (double) p.getValue()))
                        .collect(Collectors.toList()));
                this.getChart().setTitle(TITLE);
            }
        };
    }
}
