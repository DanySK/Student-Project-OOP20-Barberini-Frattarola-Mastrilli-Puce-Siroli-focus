package oop.focus.statistics.controller;

import oop.focus.finance.model.Account;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * The interface Finance input represents a collection of data that can be used as inputs to filter any
 * data about the finance section.
 */
public interface FinanceInput {
    /**
     * Gets the list of the selected accounts.
     *
     * @return the account list
     */
    Set<Account> getAccount();

    /**
     * Gets the selected start date.
     *
     * @return the start date
     */
    LocalDate getStartDate();

    /**
     * Gets the selected end date.
     *
     * @return the end date
     */
    LocalDate getEndDate();
}
