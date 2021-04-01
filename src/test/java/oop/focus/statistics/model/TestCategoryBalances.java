package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.*;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestCategoryBalances {

    private final DataSource db = new DataSourceImpl();
    private final FinanceManager financeManager = new FinanceManagerImpl(this.db);

    @Test
    public void testCategoryBalanceWithNoAccount() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);
        var data = dataSource.categoryBalances();
        var c1 = new CategoryImpl("CategoryT1", "ff6666");
        var c2 = new CategoryImpl("CategoryT2", "ff6666");
        var c3 = new CategoryImpl("CategoryT3", "ff6666");
        this.financeManager.addCategory(c1);
        this.financeManager.addCategory(c2);
        this.financeManager.addCategory(c3);

        // riferimenti ai conti
        var ac1 = new AccountImpl("AccountT1", "ff6666", 150_000);
        var ac2 = new AccountImpl("AccountT2", "ff6666", 10_000);

        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        this.financeManager.addTransaction(new TransactionImpl("TransactionT11",
                c1,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, 200, Repetition.ONCE));

        this.financeManager.addTransaction(new TransactionImpl("TransactionT12",
                c3,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, -200, Repetition.ONCE));

        this.financeManager.addTransaction(new TransactionImpl("Transaction21",
                c2,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac2, 7500, Repetition.ONCE));

        this.financeManager.addTransaction(new TransactionImpl("Transaction22",
                c2,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac2, 2000, Repetition.ONCE));


        assertEquals(Set.of(-200, 9500, 200), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toSet()));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
        this.financeManager.removeCategory(c1);
        this.financeManager.removeCategory(c2);
        this.financeManager.removeCategory(c3);
    }

    @Test
    public void testCategoryBalanceWithAccount() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);

        var c1 = new CategoryImpl("CategoryT1", "ff6666");
        var c2 = new CategoryImpl("CategoryT2", "ff6666");
        this.financeManager.addCategory(c1);
        this.financeManager.addCategory(c2);

        // riferimenti ai conti
        var ac1 = new AccountImpl("AccountT1", "ff6666", 150_000);
        var ac2 = new AccountImpl("AccountT2", "ff6666", 10_000);

        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        var data = dataSource.accountCategoryBalances(ac1);
        this.financeManager.addTransaction(new TransactionImpl("TransactionT11",
                c1,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, 200, Repetition.ONCE));

        this.financeManager.addTransaction(new TransactionImpl("TransactionT12",
                c1,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, -200, Repetition.ONCE));

        this.financeManager.addTransaction(new TransactionImpl("Transaction21",
                c2,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac2, 7500, Repetition.ONCE));

        this.financeManager.addTransaction(new TransactionImpl("Transaction22",
                c2,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac2, 2000, Repetition.ONCE));


        assertEquals(Set.of(0), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toSet()));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
    }
}
