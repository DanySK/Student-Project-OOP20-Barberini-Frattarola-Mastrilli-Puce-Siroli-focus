package oop.focus.finance.view;

import oop.focus.common.View;

public interface GenericTileView<X> extends View {

    /**
     * @return the element referenced by the tile
     */
    X getElement();
}
