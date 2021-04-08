package oop.focus.statistics.controller;

import oop.focus.finance.model.Account;
import org.joda.time.LocalDate;

import java.util.Set;

public class FinanceInputImpl implements FinanceInput {

    private final Set<Account> account;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public FinanceInputImpl(final Set<Account> account, final LocalDate startDate, final LocalDate endDate) {
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public final Set<Account> getAccount() {
        return this.account;
    }

    @Override
    public final LocalDate getStartDate() {
        return this.startDate;
    }

    @Override
    public final LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public final String toString() {
        return "FinanceInputImpl{"
                + "account=" + this.account
                + ", startDate=" + this.startDate
                + ", endDate=" + this.endDate + '}';
    }
}
