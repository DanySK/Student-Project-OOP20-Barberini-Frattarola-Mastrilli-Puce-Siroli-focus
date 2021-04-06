package oop.focus.statistics.view;

import oop.focus.common.View;

import java.util.Set;

/**
 * The interface Multi selector defines a graphic component used to select multiple items.
 *
 * @param <X> the type of the elements to be selected
 */
public interface MultiSelector<X> extends View {
    /**
     * Gets the selected items.
     *
     * @return the set of the selected items
     */
    Set<X> getSelected();
}
