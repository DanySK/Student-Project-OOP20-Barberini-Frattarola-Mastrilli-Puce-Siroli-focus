package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.StaticFormats;
import oop.focus.finance.view.bases.GenericView;

/**
 * Class that implements the view of an element, showing the main details.
 *
 * @param <X> type of the item that will be displayed
 */
public class GenericTileViewImpl<X> extends GenericView implements GenericTileView<X> {

    @FXML
    private Label circlelabel, firstLabel, secondLabel, amountLabel, minusLabel;

    private final X element;

    public GenericTileViewImpl(final X element, final String color, final String first, final String second, final double amount) {
        this.element = element;
        this.loadFXML(FXMLPaths.GENERICTILE);
        this.getRoot().getStyleClass().add("generic_tile");
        this.setLabels(color, first, second, amount);
    }

    public GenericTileViewImpl(final X element, final String first, final String second, final double amount) {
        this(element, "", first, second, amount);
    }

    public GenericTileViewImpl(final X element, final String description, final double amount) {
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
        final String green = "008f39";
        final String red = "cc0605";
        if (!color.isEmpty()) {
            this.circlelabel.setVisible(true);
            this.circlelabel.setTextFill(Color.valueOf(color));
        }
        this.firstLabel.setText(first);
        this.secondLabel.setText(second);
        this.amountLabel.setText(StaticFormats.formatAmount(Math.abs(amount)));
        this.amountLabel.setTextFill(Color.valueOf(amount > 0 ? green : red));
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
