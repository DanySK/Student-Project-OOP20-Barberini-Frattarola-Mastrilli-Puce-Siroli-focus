package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.StaticFormats;
import oop.focus.finance.view.bases.FinanceViewImpl;

/**
 * Class that implements the view of an element, showing the main details.
 *
 * @param <X> type of the item that will be displayed
 */
public class FinanceTileViewImplImpl<X> extends FinanceViewImpl implements GenericTileView<X> {

    @FXML
    private Label circleLabel, firstLabel, secondLabel, amountLabel, minusLabel;

    private final X element;

    public FinanceTileViewImplImpl(final X element, final String color, final String first, final String second, final double amount) {
        this.element = element;
        this.loadFXML(FXMLPaths.GENERICTILE);
        this.getRoot().getStyleClass().add("generic_tile");
        this.setLabels(color, first, second, amount);
    }

    public FinanceTileViewImplImpl(final X element, final String first, final String second, final double amount) {
        this(element, "", first, second, amount);
    }

    public FinanceTileViewImplImpl(final X element, final String description, final double amount) {
        this(element,  description, "", amount);
    }

    /**
     * Initialize the labels based on the inputs provided.
     * If the amount is positive it will be green, otherwise it will be red.
     *
     * @param color to show
     * @param first to show
     * @param second to show
     * @param amount to show
     */
    private void setLabels(final String color, final String first, final String second, final double amount) {
        if (!color.isEmpty()) {
            this.circleLabel.setVisible(true);
            this.circleLabel.setTextFill(Color.valueOf(color));
        }
        this.firstLabel.setText(first);
        this.secondLabel.setText(second);
        this.amountLabel.setText(StaticFormats.formatAmount(Math.abs(amount)));
        this.amountLabel.setTextFill(amount > 0 ? Color.GREEN : Color.RED);
        this.minusLabel.setVisible(amount < 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void populate() { }

    @Override
    public final X getElement() {
        return this.element;
    }
}
