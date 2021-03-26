package oop.focus.finance.model;

import java.util.Objects;

/**
 * Immutable implementation of a quick transaction.
 */
public class QuickTransactionImpl implements QuickTransaction {

    private final String description;
    private final Category category;
    private final Account account;
    private final int amount;

    public QuickTransactionImpl(final int amount, final Category category,
                                final Account account, final String description) {
        this.description = description;
        this.category = category;
        this.account = account;
        this.amount = amount;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final Category getCategory() {
        return this.category;
    }

    @Override
    public final Account getAccount() {
        return this.account;
    }

    @Override
    public final int getAmount() {
        return this.amount;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        QuickTransactionImpl that = (QuickTransactionImpl) o;
        return this.amount == that.amount && Objects.equals(this.description, that.description)
                && Objects.equals(this.category, that.category) && Objects.equals(this.account, that.account);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.description, this.category, this.account, this.amount);
    }
}
