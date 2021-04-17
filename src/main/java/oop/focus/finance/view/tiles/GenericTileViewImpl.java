package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.bases.GenericView;

public class GenericTileViewImpl<X> extends GenericView<X> implements GenericTileView<X> {

    @FXML
    private Label circlelabel, firstLabel, secondLabel, amountLabel, minusLabel;

    public GenericTileViewImpl(final X x, final String color, final String first, final String second, final double amount) {
        super(x, FXMLPaths.GENERICTILE);
        this.setLabels(color, first, second, amount);
    }

    public GenericTileViewImpl(final X x, final String first, final String second, final double amount) {
        this(x, "", first, second, amount);
    }

    public GenericTileViewImpl(final X x, final String description, final double amount) {
        this(x, "", description, "", amount);
    }

    @Override
    public final void setLabels(final String color, final String first, final String second, final double amount) {
        if (!color.isEmpty()) {
            this.circlelabel.setVisible(true);
            this.circlelabel.setTextFill(Color.valueOf(color));
        }
        this.firstLabel.setText(first);
        this.secondLabel.setText(second);
        this.amountLabel.setText(this.format(Math.abs(amount)));
        this.amountLabel.setTextFill(Color.valueOf(amount > 0 ? "008f39" : "cc0605"));
        this.minusLabel.setVisible(amount < 0);
    }

    @Override
    public void populate() { }

    @Override
    public final X getElement() {
        return super.getX();
    }
}
