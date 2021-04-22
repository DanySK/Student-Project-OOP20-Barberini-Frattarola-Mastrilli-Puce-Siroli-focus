package oop.focus.finance.view.bases;

import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;

/**
 * Interface that implements a generic view of finance.
 */
public interface FinanceView extends View {

    /**
     * Load the FXML file.
     *
     * @param path to FXML file
     */
    void loadFXML(FXMLPaths path);

    /**
     * Populates elements of fxml file.
     */
    void populate();
}
