package oop.focus.finance.view.tiles;

import oop.focus.common.View;

/**
 * Interface that implements the view of an element, showing the main details.
 *
 * @param <X> type of the item that will be displayed
 */
public interface GenericTileView<X> extends View {

    /**
     * @return the element referenced by the tile
     */
    X getElement();
}
