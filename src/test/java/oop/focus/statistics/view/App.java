package oop.focus.statistics.view;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.*;
import oop.focus.statistics.model.FinanceStatisticFactoryImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;


import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class App extends Application {

    @Override
    public final void start(final Stage primaryStage) {
        LineChartView lineChartView = new LineChartView(new Dimension2D(500, 300));
        FinanceManager manager = new FinanceManagerImpl(new DataSourceImpl());
        this.createData(a -> {
            manager.addAccount(a);
            this.createTransaction(manager, a);
        }, manager);
        var factory = new FinanceStatisticFactoryImpl(manager);
        var list = new ArrayList<Set<Pair<String, Double>>>();
        var names = new ArrayList<String>();
        var creator = factory.dailyExpenses();
        manager.getAccountManager().getAccounts().forEach(a ->
        {
            names.add(a.getName());
            list.add(factory.dailyAccountExpenses(a).get()
                    .stream()
                    .map(p -> new Pair<>(p.getKey(), (double) p.getValue() / 100))
                    .collect(Collectors.toSet()));
        });

        primaryStage.setScene(new Scene(lineChartView.getChart()));
        primaryStage.show();
        lineChartView.updateData(list, names);
        lineChartView.setTitle("Title test");
        lineChartView.setXAxisName("");
        this.createData(manager::removeAccount, manager);
    }

    private void createData(Consumer<Account> action, FinanceManager manager) {
        action.accept(new AccountImpl("AccountT1", "ff6666", 150_000));
        action.accept(new AccountImpl("AccountT2", "ff6666", 10_000));
    }

    private void createTransaction(FinanceManager manager, Account ac1) {
        var r = new Random();
        manager.addTransaction(new TransactionImpl("TransactionT11",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, r.nextInt(100000), Repetition.MONTHLY));

        manager.addTransaction(new TransactionImpl("TransactionT12",
                new CategoryImpl("Farmacia", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, -r.nextInt(100000), Repetition.MONTHLY));

        manager.addTransaction(new TransactionImpl("Transaction21",
                new CategoryImpl("Taxi", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, r.nextInt(100000), Repetition.MONTHLY));

        manager.addTransaction(new TransactionImpl("Transaction22",
                new CategoryImpl("Regali", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, r.nextInt(100000), Repetition.MONTHLY));


        manager.generateRepeatedTransactions(new LocalDate(2021, 1, 1));
    }

    public static void main(final String... args) {
        launch(args);
    }
}
