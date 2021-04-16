package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Objects;
import java.util.Set;

/**
 * An immutable implementation of {@link TimePeriodInput} using a set to store selected elements.
 *
 * @param <X> the input type.
 */
public class TimePeriodInputImpl<X> implements TimePeriodInput<X> {
    private final Set<X> values;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Instantiates a new immutable Events input.
     *
     * @param values    the values set
     * @param startDate the start date
     * @param endDate   the end date
     */
    public TimePeriodInputImpl(final Set<X> values, final LocalDate startDate, final LocalDate endDate) {
        this.values = values;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<X> getValues() {
        return this.values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TimePeriodInputImpl<?> that = (TimePeriodInputImpl<?>) o;
        return this.values.equals(that.values) && this.startDate.equals(that.startDate) && this.endDate.equals(that.endDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return Objects.hash(this.values, this.startDate, this.endDate);
    }

    @Override
    public final String toString() {
        return "TimePeriodInputImpl{"
                + "values=" + this.values
                + ", startDate=" + this.startDate
                + ", endDate=" + this.endDate
                + '}';
    }
}
