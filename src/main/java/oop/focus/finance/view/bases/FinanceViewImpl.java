package oop.focus.finance.view.bases;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.finance.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Abstract generic class that implements a view that loads a specific fxml.
 */
public abstract class FinanceViewImpl implements Initializable, FinanceView {

    private Node root;

    @Override
    public final void loadFXML(final FXMLPaths path) {
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }
}
