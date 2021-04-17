package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.bases.GenericView;

import java.util.function.Function;

public abstract class GenericWindow<X> extends GenericView<X> implements FinanceWindow {

    @FXML
    private Pane mainPane;

    public GenericWindow(final X x, final FXMLPaths path) {
        super(x, path);
    }

    @Override
    public final void close() {
        final Stage stage = (Stage) this.mainPane.getScene().getWindow();
        stage.close();
    }

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
