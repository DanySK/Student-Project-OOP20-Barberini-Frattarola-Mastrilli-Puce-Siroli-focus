package oop.focus.finance.view.tiles;

import oop.focus.common.View;

public interface GenericTileView<X> extends View {

    /**
     * Set the labels description and amount.
     *
     * @param description to be set in the description label
     * @param amount to be set in the amount label
     */
    void setLabels(String description, String amount);

    /**
     * @return the element referenced by the tile
     */
    X getElement();
}
