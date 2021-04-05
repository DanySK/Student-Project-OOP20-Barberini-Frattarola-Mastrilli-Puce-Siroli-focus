package oop.focus.common;

/**
 * The interface Controller models any controller that uses a view.
 */
public interface Controller {
    /**
     * Gets the view associated with this controller.
     *
     * @return the view
     */
    View getView();
}
