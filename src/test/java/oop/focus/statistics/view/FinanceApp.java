package oop.focus.statistics.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.*;
import oop.focus.common.Controller;
import oop.focus.statistics.controller.FinanceStatistics;


public class FinanceApp extends Application {

    @Override
    public final void start(final Stage primaryStage) {
        final var db = new DataSourceImpl();
        final var manager = new FinanceManagerImpl(db);

        final Controller controller = new FinanceStatistics(manager);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
    }

    public static void main(final String... args) {
        launch(args);
    }
}
