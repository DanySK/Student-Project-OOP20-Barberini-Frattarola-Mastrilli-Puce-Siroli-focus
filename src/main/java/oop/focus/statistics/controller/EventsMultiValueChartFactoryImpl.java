package oop.focus.statistics.controller;

import javafx.util.Pair;
import oop.focus.db.DataSource;
import oop.focus.statistics.model.EventsStatisticFactory;
import oop.focus.statistics.model.EventsStatisticFactoryImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static oop.focus.statistics.model.FinanceStatisticFactoryImpl.collectData;

/**
 * A factory for different {@link ChartController} that uses {@link oop.focus.statistics.view.MultiValueChart}.
 */
public class EventsMultiValueChartFactoryImpl implements EventsMultiValueChartFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final ChartController<EventsInput> eventsTimePerDays(final DataSource dataSource) {
        EventsStatisticFactory factory = new EventsStatisticFactoryImpl(dataSource);
        return new AbstractLineChartController<>() {
            private static final String TITLE = "Minuti giornalieri per evento";

            @Override
            public void updateInput(final EventsInput input) {
                if (!input.getEventNames().isEmpty()) {
                    this.updateWithInput(input);
                    this.getChart().setTitle(TITLE);
                }
            }

            private void updateWithInput(final EventsInput input) {
                final var accountsData = new ArrayList<List<Pair<String, Double>>>(input.getEventNames().size());
                final var names = new ArrayList<String>(input.getEventNames().size());
                for (String e : input.getEventNames()) {
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
