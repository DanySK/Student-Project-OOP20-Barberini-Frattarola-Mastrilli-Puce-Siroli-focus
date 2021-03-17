package oop.focus.db;

import java.util.List;
import java.util.Optional;

/**
 * Defines a parser for elements of type X.
 * it can be used to transform an element into a list of strings or vice versa.
 *
 * @param <X> the type of the elements
 */
public interface DataSourceParser<X> {
    /**
     * Gets the string representation of the type.
     *
     * @return the type name
     */
    String getTypeName();
    /**
     * Gets the {@link List} containing all the string representations of the fields of the type.
     *
     * @return the field names
     */
    List<String> getFieldNames();
    /**
     * Parses the element into a  {@link List} containing
     * all the string representations of the values of the fields.
     *
     * @param element the element to be parsed
     * @return the string representations of the values of the element x collected in a list
     * @throws IllegalStateException if the element type has no fields
     */
    List<String> getValues(X element) throws IllegalStateException;
    /**
     * Builds a type X element from a {@link List} of strings representing the values.
     *
     * @param values the list to be parsed
     * @return An optional containing the generated element or an optional empty
     * if the element cannot be created
     */
    Optional<X> create(List<String> values);
}
