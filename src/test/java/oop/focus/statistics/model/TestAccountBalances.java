package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAccountBalances {


    private final DataSource db = new DataSourceImpl();
    private final FinanceManager financeManager = new FinanceManagerImpl(this.db);

    @Test
    public void testAccountBalances1() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);
        var data = dataSource.accountBalances();
        // riferimenti ai conti
        var ac1 = new AccountImpl("AccountBalanceT1", "ff6666", 1_500_000);
        var ac2 = new AccountImpl("AccountBalanceT2", "ff6666", 1_000_000);
        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        assertTrue(data.get().stream().map(Pair::getValue).collect(Collectors.toList())
                .containsAll(List.of(1_500_000, 1_000_000)));

        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(1960, 1, 1, 0, 0, 0),
                ac1, -250, Repetition.YEARLY));


        assertTrue(data.get().stream().map(Pair::getValue).collect(Collectors.toList())
                .containsAll(List.of(1_499_750, 1_000_000)));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
    }

    @Test
    public void testAccountBalances2() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);
        var data = dataSource.accountBalances();
        // riferimenti ai conti
        var ac1 = new AccountImpl("AccountBalancesT1", "ff6666", 1_500_000);
        var ac2 = new AccountImpl("AccountBalancesT2", "ff6666", 1_000_000);
        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        assertTrue(data.get().stream().map(Pair::getValue).collect(Collectors.toList())
                .containsAll(List.of(1_500_000, 1_000_000)));

        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(1960, 1, 1, 0, 0, 0),
                ac1, -250, Repetition.YEARLY));

        this.financeManager.generateRepeatedTransactions(new LocalDate(1961, 1, 1));

        assertEquals(Set.of(1_499_500, 1_000_000), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toSet()));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
    }

}
