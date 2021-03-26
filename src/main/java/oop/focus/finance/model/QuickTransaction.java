package oop.focus.finance.model;

/**
 * interface that models a quick transaction.
 * A quick transaction when executed creates a transaction with the quick transaction data,
 * and using the current date as the date.
 */
public interface QuickTransaction {

    /**
     * @return the quick transaction's description
     */
    String getDescription();

    /**
     * @return the quick transaction's category
     */
    Category getCategory();

    /**
     * @return the quick transaction's account
     */
    Account getAccount();

    /**
     * @return the quick transaction's amount
     */
    int getAmount();
}
