package oop.focus.finance.model;

/**
 * Interface that models an account.
 * Each account has a name, a color and its amount.
 * 
 * The amount is changed when a transaction takes
 * place on the account.
 */
public interface Account {

    /**
     * @return the account's name
     */
    String getName();

    /**
     * @return the account's color
     */
    String getColor();

    /**
     * @return the account's amount
     */
    int getInitialAmount();
}
