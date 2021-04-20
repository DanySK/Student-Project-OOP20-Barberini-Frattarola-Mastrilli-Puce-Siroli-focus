package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;

public class HomePageLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) {

        final DataSource dsi = new DataSourceImpl();
        final FinanceManager financeManager = new FinanceManagerImpl(dsi);
        final GeneralHomePageController controller = new GeneralHomePageControllerImpl(dsi, financeManager);
        final Scene scene = new Scene((Parent) controller.getView().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }

    public static void main(final String... args) {
        launch(args);
    }

}
