package oop.focus.finance.view.tiles;

import oop.focus.common.View;

public interface GenericTileView<X> extends View {

    /**
     * Set the labels description and amount.
     *
     * @param color to be set as circle color
     * @param first to be set in the first description label
     * @param second to be set in the second description label
     * @param amount to be set in the amount label
     */
    void setLabels(String color, String first, String second, double amount);

    /**
     * @return the element referenced by the tile
     */
    X getElement();
}
