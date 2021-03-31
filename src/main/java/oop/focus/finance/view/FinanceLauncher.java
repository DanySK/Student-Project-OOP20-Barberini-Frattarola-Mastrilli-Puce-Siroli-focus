package oop.focus.finance.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.BaseControllerImpl;

public class FinanceLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        BaseController controller = new BaseControllerImpl();
        primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.show();
    }

    public static void main(final String... args) {
        launch(args);
    }
}
