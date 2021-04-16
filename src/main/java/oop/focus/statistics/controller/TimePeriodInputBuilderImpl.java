package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link TimePeriodInputBuilder} using a set to store selected elements.
 *
 * @param <X> the input type.
 */
public class TimePeriodInputBuilderImpl<X> implements TimePeriodInputBuilder<X> {

    private Set<X> values;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public final TimePeriodInputBuilder<X> values(final Set<X> values) {
        this.values = values.stream().filter(Objects::nonNull).collect(Collectors.toSet());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final TimePeriodInputBuilder<X> from(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final TimePeriodInputBuilder<X> to(final LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final TimePeriodInput<X> save() throws IllegalStateException {
        if (this.startDate == null || this.endDate == null || this.values == null) {
            throw new IllegalStateException();
        }
        return new TimePeriodInputImpl<>(this.values, this.startDate, this.endDate);
    }
}
