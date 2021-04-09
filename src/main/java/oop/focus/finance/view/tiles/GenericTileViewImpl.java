package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.bases.GenericView;

public class GenericTileViewImpl<X> extends GenericView<X> implements GenericTileView<X> {

    @FXML
    private Label descriptionLabel, amountLabel;

    public GenericTileViewImpl(final X x, final String description, final String amount) {
        super(x, FXMLPaths.GENERICTILE);
        this.setLabels(description, amount);
    }

    @Override
    public final void setLabels(final String description, final String amount) {
        this.descriptionLabel.setText(description);
        this.amountLabel.setText(amount);
    }

    @Override
    public void populate() { }

    @Override
    public final X getElement() {
        return super.getX();
    }
}
