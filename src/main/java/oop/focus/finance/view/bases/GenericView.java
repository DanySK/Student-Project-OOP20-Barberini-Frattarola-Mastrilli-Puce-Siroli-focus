package oop.focus.finance.view.bases;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import oop.focus.finance.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Abstract generic class that implements a view that loads a specific fxml.
 * The class contains an element as a field (usually a controller) and already has methods related
 * to displaying recurring dialog windows.
 *
 * @param <X> type of the element passed as a field
 */
public abstract class GenericView<X> implements Initializable, FinanceView<X> {

    private final X x;
    private Node root;

    public GenericView(final X x, final FXMLPaths path) {
        this.x = x;
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

    /**
     * @return the object to which the view refers
     */
    public final X getX() {
        return this.x;
    }

    /**
     * Show a pop-up indicating an error.
     *
     * @param message to diplay
     */
    public final void allert(final String message) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(message);
        alert.setContentText("Premi ok per tornare indietro.");

        final Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    /**
     * Show a pop-up asking for confirmation.
     *
     * @param message to display
     * @return the result
     */
    public final Optional<ButtonType> confirm(final String message) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText(message);
        alert.setContentText("Premi ok per confermare.");
        return alert.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String format(final double amount) {
        final DecimalFormat f = new DecimalFormat("#0.00");
        return f.format(amount);
    }

}
