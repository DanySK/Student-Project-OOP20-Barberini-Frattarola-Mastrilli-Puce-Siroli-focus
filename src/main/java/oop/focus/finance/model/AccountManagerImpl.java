package oop.focus.finance.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

import java.util.List;

public class AccountManagerImpl implements AccountManager {

    private final Dao<Account> accounts;

    public AccountManagerImpl(final DataSource db) {
        this.accounts = db.getAccounts();
    }

    @Override
    public final void add(final Account account) {
        try {
            this.accounts.save(account);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void remove(final Account account) {
        try {
            this.accounts.delete(account);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final List<Account> getAccounts() {
        return this.accounts.getAll();
    }
}
