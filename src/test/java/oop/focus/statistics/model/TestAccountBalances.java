package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestAccountBalances {


    private final DataSource db = new DataSourceImpl();
    private final FinanceManager financeManager = new FinanceManagerImpl(this.db);

    @Test
    public void testAccountBalances1() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);
        var data = dataSource.accountBalances();
        // riferimenti ai conti
        var ac1 = new AccountImpl("AccountT1", "ff6666", 150_000);
        var ac2 = new AccountImpl("AccountT2", "ff6666", 10_000);
        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        assertEquals(Set.of(150_000, 10_000), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toSet()));

        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, -250, Repetition.YEARLY));


        assertEquals(Set.of(149_750, 10_000), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toSet()));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
    }

    @Test
    public void testAccountBalances2() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);
        var data = dataSource.accountBalances();
        // riferimenti ai conti
        var ac1 = new AccountImpl("AccountT1", "ff6666", 150_000);
        var ac2 = new AccountImpl("AccountT2", "ff6666", 10_000);
        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        assertEquals(Set.of(150_000, 10_000), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toSet()));

        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, -250, Repetition.YEARLY));

        this.financeManager.generateRepeatedTransactions(new LocalDate(2021, 1, 1));

        assertEquals(Set.of(149_500, 10_000), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toSet()));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
    }

}
