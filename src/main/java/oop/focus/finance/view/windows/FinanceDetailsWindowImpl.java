package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Class that implements the view of an element detail view window.
 * In addition to viewing the details, actions on the element are also generally allowed.
 */
public abstract class FinanceDetailsWindowImpl extends FinanceWindowImpl implements FinanceDetailsWindow {

    @FXML
    private Button closeButton, deleteButton;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.populateStaticLabels();
        this.populateDynamicLabels();
        this.populateButtons();
    }

    /**
     * Populates static labels of fxml file.
     */
    protected void populateStaticLabels() { }

    /**
     * Populates buttons of fxml file.
     */
    protected void populateButtons() {
        this.deleteButton.setText("Elimina");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.save());
        this.closeButton.setOnAction(event -> this.close());
    }
}
