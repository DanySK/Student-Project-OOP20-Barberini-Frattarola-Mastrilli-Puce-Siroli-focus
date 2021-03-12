package oop.focus.finance;

import org.joda.time.LocalDate;

/**
 * Interface that models a transaction.
 * Each transaction has a description, the category to which it belongs,
 * the date in which it is carried out, the account in which it is carried out,
 * the amount, its repetition and a boolean indicating whether it should be repeated.
 */
public interface Transaction {

    /**
     * @return transaction's description
     */
    String getDesc();

    /**
     * @return transaction's category
     */
    Category getCat();

    /**
     * @return transaction's account
     */
    Account getAccount();

    /**
     * @return transaction's repetition
     */
    Repetition getRep();

    /**
     * @return true if the transaction no longer needs to be repeated 
     */
    Boolean isLast();

    /**
     * @return transaction's date
     */
    LocalDate getDate();


    /**
     * @return transaction's amount
     */
    int getAmount();

    /**
     * @param b boolean with which 'last' attribute must be changed
     */
    void setLast(boolean b);

    /**
     * @return the date of the next renewal
     */
    LocalDate getNextRenewal();
}
