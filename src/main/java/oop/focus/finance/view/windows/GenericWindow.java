package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.view.bases.GenericView;

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

    public static boolean isNotNumeric(final String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
}
