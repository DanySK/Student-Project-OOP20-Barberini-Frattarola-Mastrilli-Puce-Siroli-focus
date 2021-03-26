package oop.focus.finance.model;

/**
 * Interface that models a transaction's category.
 * Each category has a name and a color.
 */
public interface Category {

    /**
     * @return the category's name
     */
    String getName();

    /**
     * @return the category's color
     */
    String getColor();
}
