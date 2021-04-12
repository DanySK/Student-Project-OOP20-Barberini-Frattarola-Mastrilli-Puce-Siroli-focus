package oop.focus.finance.view.bases;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import oop.focus.finance.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

public abstract class GenericView<X> implements Initializable, FinanceView<X> {

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

    @Override
    public final X getX() {
        return this.x;
    }

    @Override
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

    @Override
    public final Optional<ButtonType> confirm(final String message) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText(message);
        alert.setContentText("Premi ok per confermare.");
        return alert.showAndWait();
    }

    @Override
    public final String format(final double amount) {
        final DecimalFormat f = new DecimalFormat("#0.00");
        return f.format(amount);
    }

}
