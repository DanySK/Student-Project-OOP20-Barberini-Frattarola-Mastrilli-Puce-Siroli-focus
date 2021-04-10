package oop.focus.diary.model;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import oop.focus.db.DataSourceImpl;
import oop.focus.diary.view.GeneralDiaryView;
import oop.focus.homepage.model.EventManagerImpl;

public class GeneralLauncher extends Application {
    @Override
    public final void start(final Stage primaryStage) throws Exception {
    Dimension2D dim = new Dimension2D(1400, 900);
        DataSourceImpl dataSource = new DataSourceImpl();
        EventManagerImpl eventManager = new EventManagerImpl(dataSource);

    Scene scene = new Scene((Parent) new GeneralDiaryView(dataSource, eventManager).getRoot());
        primaryStage.setScene(scene);
//    final String css = LauncherDiary.class.getResource(PATH_MAIN_STYLE).toExternalForm();
//        scene.getStylesheets().add(css);
        primaryStage.setWidth(dim.getWidth());
        primaryStage.setHeight(dim.getHeight());
        primaryStage.show();
        }
        public static void main(String[] args){launch(args);}

}
