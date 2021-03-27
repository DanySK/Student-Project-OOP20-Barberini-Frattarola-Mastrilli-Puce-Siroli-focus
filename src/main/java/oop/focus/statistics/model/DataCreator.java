package oop.focus.statistics.model;
import java.util.Set;
/**
 * The interface Data creator represents an object that, given a collection of
 * type X elements can generate a set of Type Y elements.
 *
 * @param <X> the type of input elements
 * @param <Y> the type of output elements
 */
public interface DataCreator<X, Y> {
    /**
     * Generate and returns the output {@link Set} of type Y.
     *
     * @return the output set
     */
    Set<Y> get();
}
