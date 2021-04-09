package oop.focus.finance.view.windows;

public interface FinanceDetailsWindow<X> {

    /**
     * Method that allows you to modify the populate.
     */
    void edits();

    /**
     * @return the window controller
     */
    X getController();
}
