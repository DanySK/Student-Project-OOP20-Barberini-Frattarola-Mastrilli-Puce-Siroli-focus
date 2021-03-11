package oop.focus.db;

import java.util.Optional;

/**
 *This interface adds methods to the {@link Dao} interface to retrieve a single element from the source.
 * @param <X> the type of the data
 */
public interface SingleDao<X> extends Dao<X> {
    /**
     * Get a specific element from the source from his id.
     *
     * @param id the id of the source element
     * @return An {@link Optional} containing the element if it is present in the source otherwise an empty optional
     */
    Optional<X> getValue(int id);
    /**
     * Get the id of the element x from the source.
     *
     * @param x the elem of the source element
     * @return An {@link Optional} containing the id if the element is present in the source
     * otherwise an empty optional
     */
    Optional<Integer> getId(X x);
}
