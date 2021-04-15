package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.bases.GenericView;

public class GenericTileViewImpl<X> extends GenericView<X> implements GenericTileView<X> {

    @FXML
    private Label circlelabel, firstLabel, secondLabel, amountLabel;

    public GenericTileViewImpl(final X x, final String color, final String first, final String second, final String amount) {
        super(x, FXMLPaths.GENERICTILE);
        this.setLabels(color, first, second, amount);
    }

    public GenericTileViewImpl(final X x, final String first, final String second, final String amount) {
        this(x, "", first, second, amount);
    }

    public GenericTileViewImpl(final X x, final String description, final String amount) {
        this(x, "", description, "", amount);
    }

    @Override
    public final void setLabels(final String color, final String first, final String second, final String amount) {
        if (!color.isEmpty()) {
            this.circlelabel.setVisible(true);
            this.circlelabel.setTextFill(Color.valueOf(color));
        }
        this.firstLabel.setText(first);
        this.secondLabel.setText(second);
        this.amountLabel.setText(amount);
    }

    @Override
    public void populate() { }

    @Override
    public final X getElement() {
        return super.getX();
    }
}
