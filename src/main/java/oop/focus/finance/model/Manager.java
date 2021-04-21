package oop.focus.finance.model;

import javafx.collections.ObservableSet;

/**
 * Interface that models a generic manager,
 * working on all elements and managing database operations.
 * @param <X> type of elements
 */
public interface Manager<X> {

    /**
     * Saves the element in the database.
     *
     * @param element that is saved
     */
    void add(X element);

    /**
     * Removes the element from the database.
     *
     * @param element being deleted
     */
    void remove(X element);

    /**
     * @return the list of all elements.
     */
    ObservableSet<X> getElements();
}
