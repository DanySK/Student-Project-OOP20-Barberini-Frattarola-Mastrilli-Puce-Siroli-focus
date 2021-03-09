package oop.focus.finance;

import java.util.ArrayList;
import java.util.List;

public class AccountManagerImpl implements AccountManager {

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public final void add(final Account account) {
        this.accounts.add(account);
    }

    @Override
    public final void remove(final Account account) {
        this.accounts.remove(account);
    }

    @Override
    public final List<Account> getAccounts() {
        return this.accounts;
    }
}
