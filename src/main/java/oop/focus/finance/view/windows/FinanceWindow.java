package oop.focus.finance.view.windows;

import javafx.util.StringConverter;
import oop.focus.common.View;

import java.util.function.Function;

/**
 * Interface that implements a finance window.
 */
public interface FinanceWindow extends View {

    /**
     * The window closes.
     */
    void close();

    /**
     * Saves the changes in the database and closes the window.
     */
    void save();

    /**
     * Method that creates an element converter to a string according to the function passed as a parameter.
     * It differs from the toString of an element because we don't want to display all the details
     * but a quick view.
     *
     * @param function that indicates what of the element we want to display
     * @param <Y> type of the element we want to convert
     * @return a converter of elements to string
     */
    <Y> StringConverter<Y> createStringConverter(Function<Y, String> function);

    /**
     * Method that checks if a string is not convertible to a number.
     *
     * @param string to check
     * @return true if the string is not convertible to number
     */
    static boolean isNotNumeric(String string) {
        try {
            Double.parseDouble(string);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
