package oop.focus.statistics.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.common.Repetition;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.finance.model.*;
import oop.focus.common.Controller;
import oop.focus.statistics.controller.FinanceStatistics;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.*;
import java.util.function.Consumer;


public class LineChartApp extends Application {

    String color1 = "123455";
    String color2 = "234562";
    String color3 = "567893";
    String color4 = "000000";
    Category c1 = new CategoryImpl("c1", this.color1);
    Category c2 = new CategoryImpl("c2", this.color2);
    Category c3 = new CategoryImpl("c3", this.color3);
    Category c4 = new CategoryImpl("c4", this.color4);


    @Override
    public final void start(final Stage primaryStage) {
        //LineChartView lineChartView = new LineChartView(new Dimension2D(500, 300));
        var db = new DataSourceImpl();
        try {
            db.getColors().save(this.color1);
            db.getColors().save(this.color2);
            db.getColors().save(this.color3);
            db.getColors().save(this.color4);
            db.getCategories().save(this.c1);
            db.getCategories().save(this.c2);
            db.getCategories().save(this.c3);
            db.getCategories().save(this.c4);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
        FinanceManager manager = new FinanceManagerImpl(db);
        this.createData(a -> {
            manager.addAccount(a);
            this.createTransaction(manager, a);
        }, manager);

        Controller controller = new FinanceStatistics(manager);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
        /*this.createData(manager::removeAccount, manager);
        try {
            db.getCategories().delete(c1);
            db.getCategories().delete(c2);
            db.getCategories().delete(c3);
            db.getCategories().delete(c4);
            db.getColors().delete(this.color1);
            db.getColors().delete(this.color2);
            db.getColors().delete(this.color3);
            db.getColors().delete(this.color4);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }*/
    }

    private void createData(Consumer<Account> action, FinanceManager manager) {
        action.accept(new AccountImpl("AccountT1", "ff6666", 150_000));
        action.accept(new AccountImpl("AccountT2", "8cff66", 10_000));
    }

    private void createTransaction(FinanceManager manager, Account ac1) {


        var r = new Random();
        manager.addTransaction(new TransactionImpl("TransactionT11",
                this.c1,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, r.nextInt(100000), Repetition.BIMONTHLY));

        manager.addTransaction(new TransactionImpl("TransactionT12",
                this.c2,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, -r.nextInt(100000), Repetition.BIMONTHLY));

        manager.addTransaction(new TransactionImpl("Transaction21",
                this.c3,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, r.nextInt(100000), Repetition.BIMONTHLY));

        manager.addTransaction(new TransactionImpl("Transaction22",
                this.c4,
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                ac1, r.nextInt(100000), Repetition.MONTHLY));

       /* manager.getTransactionManager().getTransactions().stream().map(Transaction::getCategory)
                .map(Category::getColor).forEach(System.out::println);

        */
        manager.generateRepeatedTransactions(new LocalDate(2021, 1, 1));
    }

    public static void main(final String... args) {
        launch(args);
    }
}
