package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import oop.focus.finance.view.bases.FinanceViewImpl;

import java.util.function.Function;

/**
 * Class that implements the view of a window.
 */
public abstract class FinanceWindowImpl extends FinanceViewImpl implements FinanceWindow {

    @FXML
    private Pane mainPane;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void close() {
        final Stage stage = (Stage) this.mainPane.getScene().getWindow();
        stage.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <X> StringConverter<X> createStringConverter(final Function<X, String> function) {
        return new StringConverter<>() {
            @Override
            public String toString(final X obj) {
                return obj == null ? "" : function.apply(obj);
            }

            @Override
            public X fromString(final String s) {
                throw new UnsupportedOperationException();
            }
        };
    }

}
