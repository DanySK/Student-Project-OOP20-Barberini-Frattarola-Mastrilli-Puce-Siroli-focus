package oop.focus.statistics.model;

import javafx.util.Pair;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The interface Data creator represents an object that, given a collection of
 * type X elements can generate a set of Type Y elements.
 *
 * @param <X> the type of input elements
 * @param <Y> the type of output elements
 */
public interface DataCreator<X, Y> {

    /**
     * This method collects a Stream of pair (X, Integer) to a list of pairs (String, Double).
     * The input function is used to transform each {@link Integer} value to a double value.
     * Each X key of the pair is transformed to a String using the toString() method.
     * This method is useful for transforming data so that it can be displayed in a chart.
     *
     * @param data the input stream of {@link Pair}
     * @param fun  the function to map each Integer to a Double
     * @param <X>  the {@link Pair} key type
     * @return the list.
     */
    static <X> List<Pair<String, Double>> collectData(final Stream<Pair<X, Integer>> data,
                                                      final Function<Integer, Double> fun) {
        return data.map(p -> new Pair<>(p.getKey().toString(), fun.apply(p.getValue())))
                .collect(Collectors.toList());
    }

    /**
     * Generate and returns the output {@link Set} of type Y.
     *
     * @return the output set
     */
    Set<Y> get();
}
