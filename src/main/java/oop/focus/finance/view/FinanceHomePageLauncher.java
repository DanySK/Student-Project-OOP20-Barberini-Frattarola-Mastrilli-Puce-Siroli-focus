package oop.focus.finance.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.controller.FinanceHomePageControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;

public class FinanceHomePageLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final FinanceManager manager = new FinanceManagerImpl(new DataSourceImpl());
        final FinanceHomePageController controller = new FinanceHomePageControllerImpl(manager);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
    }

    public static void main(final String... args) {
        launch(args);
    }
}
