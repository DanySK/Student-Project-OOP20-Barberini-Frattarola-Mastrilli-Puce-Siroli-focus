package oop.focus.finance.view.bases;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class GenericView<X> implements Initializable, View {

    private final X x;
    private Parent root;

    public GenericView(final X x, final FXMLPaths path) {
        this.x = x;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    protected final X getX() {
        return this.x;
    }

    protected abstract void populate();
}
