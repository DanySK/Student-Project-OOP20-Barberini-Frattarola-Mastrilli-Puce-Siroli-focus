package oop.focus.statistics.controller;

import oop.focus.finance.model.Account;
import org.joda.time.LocalDate;

import java.util.Objects;
import java.util.Set;

/**
 * An immutable implementation of {@link FinanceInput}.
 */
public class FinanceInputImpl implements FinanceInput {

    private final Set<Account> account;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Instantiates a new immutable Finance input.
     *
     * @param account   the account
     * @param startDate the start date
     * @param endDate   the end date
     */
    public FinanceInputImpl(final Set<Account> account, final LocalDate startDate, final LocalDate endDate) {
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Account> getAccount() {
        return this.account;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "FinanceInputImpl{"
                + "account=" + this.account
                + ", startDate=" + this.startDate
                + ", endDate=" + this.endDate + '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        FinanceInputImpl that = (FinanceInputImpl) o;
        return this.account.equals(that.account) && this.startDate.equals(that.startDate) && this.endDate.equals(that.endDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return Objects.hash(this.account, this.startDate, this.endDate);
    }
}
