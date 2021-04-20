package oop.focus.finance.view.windows;

/**
 * Interface that implements a window showing the details of an element.
 *
 * @param <X> type of the controller that manages the item shown in detail
 */
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
