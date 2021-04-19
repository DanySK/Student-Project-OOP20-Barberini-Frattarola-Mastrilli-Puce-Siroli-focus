package oop.focus.statistics.model;

import javafx.collections.FXCollections;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GeneratedDataCreator<X, Y> extends DataCreatorImpl<X, Y> {

    private final Supplier<Set<X>> generator;

    public GeneratedDataCreator(final Supplier<Set<X>> generator, final Function<Stream<X>, Set<Y>> mapper) {
        super(FXCollections.observableSet(generator.get()), mapper);
        this.generator = generator;
    }

    @Override
    public final Set<Y> get() {
        this.updateDataset();
        return super.get();
    }

    private void updateDataset() {
        final var generated = this.generator.get();
        final var dataset = this.getDataset();
        this.getDataset().removeIf(e -> !generated.contains(e));
        generated.stream().filter(e -> !dataset.contains(e)).forEach(dataset::add);
    }
}
