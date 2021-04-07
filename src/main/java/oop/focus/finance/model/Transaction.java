package oop.focus.finance.model;

import oop.focus.common.Repetition;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Interface that models a transaction.
 * Each transaction has a description, the category to which it belongs,
 * the date in which it is carried out, the account in which it is carried out,
 * the amount, its repetition and a boolean indicating whether it should be repeated.
 */
public interface Transaction {

    /**
     * @return the date of the next renewal
     */
    LocalDate getNextRenewal();

    /**
     * Stop to repeat a subscription.
     */
    void stopRepeat();

    /**
     * @return transaction's description
     */
    String getDescription();

    /**
     * @return transaction's category
     */
    Category getCategory();

    /**
     * @return transaction's date
     */
    LocalDateTime getDate();

    /**
     * @return transaction's account
     */
    Account getAccount();

    /**
     * @return transaction's amount
     */
    int getAmount();

    /**
     * @return transaction's repetition
     */
    Repetition getRepetition();

    /**
     * @return true if the transaction no longer needs to be repeated 
     */
    Boolean isToBeRepeated();

    /**
     * @return a readable version of transaction date
     */
    String getDateToString();
}
