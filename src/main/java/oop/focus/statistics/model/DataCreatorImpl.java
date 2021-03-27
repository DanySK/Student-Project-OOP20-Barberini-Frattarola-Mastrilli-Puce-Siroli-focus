package oop.focus.statistics.model;

import javafx.collections.ObservableList;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
/**
 * This implementation of {@link DataCreator} interface maps elements from a list<X> to a Set<Y>.
 * changes on the input list will affect the next get method call, but not the old sets already returned
 * by the get method.
 * @param <X> the input type
 * @param <Y> the output type
 */
public class DataCreatorImpl<X, Y> implements DataCreator<X, Y> {

    private final ObservableList<X> dataset;
    private final Function<Stream<X>, Set<Y>> mapper;

    public DataCreatorImpl(final ObservableList<X> input, final Function<Stream<X>, Set<Y>> mapper) {
        this.dataset = input;
        this.mapper = mapper;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Y> get() {
        return this.mapper.apply(this.dataset.stream());
    }
}
