package oop.focus.statistics.controller;

import oop.focus.finance.model.Account;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * The interface Finance input builder models a builder for the {@link FinanceInput} interface.
 */
public interface FinanceInputBuilder {
    /**
     * Insert the list of Accounts.
     *
     * @param account the account list
     * @return the finance input builder
     */
    FinanceInputBuilder accounts(Set<Account> account);

    /**
     * Insert the start date.
     *
     * @param startDate the start date
     * @return the finance input builder
     */
    FinanceInputBuilder from(LocalDate startDate);

    /**
     * Insert the end date.
     *
     * @param endDate the end date
     * @return the finance input builder
     */
    FinanceInputBuilder to(LocalDate endDate);

    /**
     * Creates a Finance input with the given input data.
     *
     * @return the finance input
     * @throws IllegalStateException if the finance input cannot be created.
     */
    FinanceInput save() throws IllegalStateException;
}
