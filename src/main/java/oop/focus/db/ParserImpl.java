package oop.focus.db;
import javafx.util.Pair;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
public class ParserImpl<X> implements DataSourceParser<X> {

    private final List<Pair<String, Function<X, String>>> input;
    private final String tableName;
    private final Function<List<String>, X> builder;

    public ParserImpl(final String name, final Function<List<String>, X> builder,
                      final List<Pair<String, Function<X, String>>> values) {
        this.tableName = name;
        this.input = values;
        this.builder = builder;
    }
    @Override
    public final Optional<X> create(final List<String> rs) {
        try {
            return Optional.of(this.builder.apply(rs));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    @Override
    public final String getTypeName() {
        return this.tableName;
    }
    @Override
    public final List<String> getFieldNames() {
        this.checkValidity();
        return this.input.stream().map(Pair::getKey).collect(Collectors.toList());
    }
    @Override
    public final List<String> getValues(final X element) throws IllegalStateException {
        return this.input.stream()
                .map(x -> x.getValue().apply(element))
                .collect(Collectors.toList());
    }
    private void checkValidity() throws IllegalStateException {
        if (this.input.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
