package oop.focus.diary.model;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.controller.*;
import oop.focus.diary.view.BaseDiary;

public class LauncherDiary extends Application {
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Dimension2D dim = new Dimension2D(1400, 900);
        final DataSourceImpl dataSource = new DataSourceImpl();
        final ToDoListController controller = new ToDoListControllerImpl(new ToDoListManagerImpl(dataSource));
        final DailyMoodControllerImpl manager = new DailyMoodControllerImpl(new DailyMoodManagerImpl(dataSource));
        final DiaryPagesImpl diaryPages = new DiaryPagesImpl(new DiaryDao());
        final Scene scene = new Scene((Parent) new BaseDiary(controller, diaryPages, manager).getRoot());
        primaryStage.setScene(scene);
        final String css = LauncherDiary.class.getResource(Style.DIARY_STYLE.getPath()).toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setWidth(dim.getWidth());
        primaryStage.setHeight(dim.getHeight());
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
