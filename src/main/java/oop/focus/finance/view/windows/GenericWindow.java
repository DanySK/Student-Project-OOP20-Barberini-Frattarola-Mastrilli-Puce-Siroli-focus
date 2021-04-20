package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.bases.GenericView;

import java.util.function.Function;

/**
 * Class that implements the view of a window.
 *
 * @param <X> type of the element passed as a field
 */
public abstract class GenericWindow<X> extends GenericView<X> implements FinanceWindow {

    @FXML
    private Pane mainPane;

    public GenericWindow(final X x, final FXMLPaths path) {
        super(x, path);
    }

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
    public final <Y> StringConverter<Y> createStringConverter(final Function<Y, String> function) {
        return new StringConverter<>() {
            @Override
            public String toString(final Y obj) {
                return obj == null ? "" : function.apply(obj);
            }

            @Override
            public Y fromString(final String s) {
                throw new UnsupportedOperationException();
            }
        };
    }

}
