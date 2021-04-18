package oop.focus.statistics.model;

import javafx.util.Pair;
import oop.focus.common.Repetition;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestPeriodExpenses {

    private final FinanceManager financeManager = new FinanceManagerImpl(new DataSourceImpl());

    @Test
    public void testPeriodExpenses1() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);
        var data = dataSource.periodExpenses(
                new LocalDate(1960, 12, 1), new LocalDate(1960, 12, 5));
        // riferimenti ai conti
        var ac1 = new AccountImpl("AccountTExp1", "ff6666", 150_000);
        var ac2 = new AccountImpl("AccountTExp2", "ff6666", 10_000);
        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        this.financeManager.addTransaction(new TransactionImpl("TransactionT1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(1960, 12, 1, 0, 0, 0),
                ac1, +250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("TransactionT2",
                new CategoryImpl("Taxi", ""),
                new LocalDateTime(1960, 12, 5, 0, 0, 0),
                ac2, -250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("TransactionT3",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(1960, 12, 6, 0, 0, 0),
                ac1, -250, Repetition.ONCE));


        assertEquals(List.of(-250, 0, 0, 0, 250), data.get()
                .stream().map(Pair::getValue).sorted().collect(Collectors.toList()));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
    }

    @Test
    public void testPeriodExpenses2() {
        var dataSource = new FinanceStatisticFactoryImpl(this.financeManager);
        var ac1 = new AccountImpl("AccountTExp1", "ff6666", 150_000);
        var ac2 = new AccountImpl("AccountTExp2", "ff6666", 10_000);
        var data = dataSource.accountPeriodExpenses(ac1,
                new LocalDate(1960, 12, 1), new LocalDate(1960, 12, 5));
        // riferimenti ai conti
        this.financeManager.addAccount(ac1);
        this.financeManager.addAccount(ac2);

        this.financeManager.addTransaction(new TransactionImpl("TransactionT1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(1960, 12, 1, 0, 0, 0),
                ac1, +250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("TransactionT2",
                new CategoryImpl("Taxi", ""),
                new LocalDateTime(1960, 12, 5, 0, 0, 0),
                ac2, -250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("TransactionT3",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(1960, 12, 1, 0, 0, 0),
                ac1, -250, Repetition.ONCE));


        assertEquals(List.of(0, 0, 0, 0, 0), data.get()
                .stream().map(Pair::getValue).collect(Collectors.toList()));

        this.financeManager.removeAccount(ac1);
        this.financeManager.removeAccount(ac2);
    }
}
