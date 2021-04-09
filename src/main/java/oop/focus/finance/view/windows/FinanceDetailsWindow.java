package oop.focus.finance.view.windows;

public interface FinanceDetailsWindow<X> {

    /**
     * Populates static labels of fxml file.
     */
    void populateStaticLabels();

    /**
     * Populates dynamic labels of fxml file.
     */
    void populateDynamicLabels();

    /**
     * Populates buttons of fxml file.
     */
    void populateButtons();

    /**
     * @return the window controller
     */
    X getController();
}
