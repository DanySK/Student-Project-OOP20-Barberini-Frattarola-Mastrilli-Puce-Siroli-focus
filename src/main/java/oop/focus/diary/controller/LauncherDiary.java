package oop.focus.diary.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.view.BaseDiary;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.BaseControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;

import java.util.zip.DataFormatException;

public class LauncherDiary extends Application {
    private static final String PATH_MAIN_STYLE = "/layouts/diary/diaryStyle.css";
    private static final String PATH_BASE_DIARY = "/layouts/diary/baseDiary.fxml";
    private final Stage firstStage = new Stage();


    @Override
    public final void start(final Stage primaryStage) throws Exception {
    /*    firstStage.setMaximized(true);
    final Parent root = FXMLLoader.load(this.getClass().getResource(PATH_BASE_DIARY));
    final Scene scene = new Scene(root);

    this.firstStage.setResizable(true);
    primaryStage.show();

     */
        //final FinanceManager manager = new FinanceManagerImpl(new DataSourceImpl());
        //final BaseController controller = new BaseControllerImpl(manager);
        Dimension2D dim = new Dimension2D(1400, 900);
        Scene scene = new Scene(new BaseDiary(dim).getRoot());
        primaryStage.setScene(scene);
        final String css = LauncherDiary.class.getResource(PATH_MAIN_STYLE).toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setWidth(dim.getWidth());
        primaryStage.setHeight(dim.getHeight());
        //primaryStage.setResizable(true);
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
