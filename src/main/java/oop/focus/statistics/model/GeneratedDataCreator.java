package oop.focus.statistics.model;

import javafx.collections.FXCollections;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GeneratedDataCreator<X, Y> extends DataCreatorImpl<X, Y> {

    private final Supplier<List<X>> generator;

    public GeneratedDataCreator(final Supplier<List<X>> generator, final Function<Stream<X>, Set<Y>> mapper) {
        super(FXCollections.observableList(generator.get()), mapper);
        this.generator = generator;
    }

    @Override
    public final Set<Y> get() {
        this.updateDataset();
        return super.get();
    }

    private void updateDataset() {
        var generated = this.generator.get();
        var dataset = this.getDataset();
        this.getDataset().removeIf(e -> !generated.contains(e));
        generated.stream().filter(e -> !dataset.contains(e)).forEach(dataset::add);
    }
}
