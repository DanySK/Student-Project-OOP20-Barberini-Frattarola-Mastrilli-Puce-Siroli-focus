package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GenericTileViewImpl<X> implements Initializable, GenericTileView<X> {

    @FXML
    private Label descriptionLabel, amountLabel;

    private final X element;
    private final String description;
    private final String amount;
    private Parent root;

    public GenericTileViewImpl(final X element, final String description, final String amount) {
        this.element = element;
        this.description = description;
        this.amount = amount;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.GENERICTILE.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.descriptionLabel.setText(this.description);
        this.amountLabel.setText(this.amount);
    }

    @Override
    public final X getElement() {
        return this.element;
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
