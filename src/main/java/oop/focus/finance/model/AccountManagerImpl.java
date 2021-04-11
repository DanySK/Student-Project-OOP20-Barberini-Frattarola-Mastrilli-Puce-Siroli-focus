package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class AccountManagerImpl implements AccountManager {

    private final Dao<Account> accounts;
    private final Dao<String> colors;

    public AccountManagerImpl(final DataSource db) {
        this.accounts = db.getAccounts();
        this.colors = db.getColors();
    }

    @Override
    public final void add(final Account account) {
        try {
            this.checkColor(account.getColor());
            this.accounts.save(account);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    private void checkColor(final String color) {
        if (!this.colors.getAll().contains(color)) {
            try {
                this.colors.save(color);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
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
    public final ObservableSet<Account> getAccounts() {
        return this.accounts.getAll();
    }
}
