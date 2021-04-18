package oop.focus.db;

import javafx.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The implementation of {@link DataSourceParser}.
 *
 * @param <X> the type of the element to be parsed.
 */
public class ParserImpl<X> implements DataSourceParser<X> {

    private final List<Pair<String, Function<X, String>>> input;
    private final String tableName;
    private final Function<List<String>, X> builder;

    /**
     * Instantiates a new Parser.
     *
     * @param name    the string representation of the object type
     * @param builder a function that creates a new X object from a list of string.
     * @param values  a list in which the methods for retrieving
     *                the fields useful for creating an element of type x are provided.
     *                The list contains pairs of elements where the first represents the name of the field
     *                while the second provides a function that returns the value of that field given an element of type x
     */
    public ParserImpl(final String name, final Function<List<String>, X> builder,
                      final List<Pair<String, Function<X, String>>> values) {
        this.tableName = name;
        this.input = values;
        this.builder = builder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Optional<X> create(final List<String> rs) {
        try {
            return Optional.of(this.builder.apply(rs));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getTypeName() {
        return this.tableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<String> getFieldNames() {
        this.checkValidity();
        return this.input.stream().map(Pair::getKey).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<String> getValues(final X element) throws IllegalStateException {
        return this.input.stream()
                .map(x -> x.getValue().apply(element))
                .collect(Collectors.toList());
    }

    /**
     * Check if the input is valid and not empty.
     *
     * @throws IllegalStateException if the input is not valid.
     */
    private void checkValidity() throws IllegalStateException {
        if (this.input.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
