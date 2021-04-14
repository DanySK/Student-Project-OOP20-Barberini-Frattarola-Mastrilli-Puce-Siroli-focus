package oop.focus.diary.model;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.controller.ControllersFactory;

import oop.focus.diary.controller.GeneralControllerCounter;
import oop.focus.diary.controller.Style;
import oop.focus.diary.controller.TotalTimeControllerImpl;

import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

public class LauncherTimer extends Application {
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Dimension2D dim = new Dimension2D(1400, 900);
        final DataSourceImpl dataSource = new DataSourceImpl();
        final EventManager manager = new EventManagerImpl(dataSource);
        final Scene scene = new Scene((Parent) new GeneralControllerCounter(manager, true).getView().getRoot());
        //final Scene scene = new Scene((Parent) new TimerView(controller, f.createTimer()).getRoot());
        primaryStage.setScene(scene);
        final String css = LauncherDiary.class.getResource(Style.TIMER_STYLE.getPath()).toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setWidth(dim.getWidth());
        primaryStage.setHeight(dim.getHeight());
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
