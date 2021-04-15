package oop.focus.diary.model;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.controller.DiarySectionsControllerImpl;
import oop.focus.diary.view.GeneralDiaryView;

public class GeneralLauncher extends Application {
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Dimension2D dim = new Dimension2D(1400, 900);
        final DataSourceImpl dataSource = new DataSourceImpl();
        final Scene scene = new Scene((Parent) new DiarySectionsControllerImpl(dataSource).getView().getRoot());
        primaryStage.setScene(scene);
        primaryStage.setWidth(dim.getWidth());
        primaryStage.setHeight(dim.getHeight());
        primaryStage.show();
    }
        public static void main(final String[] args){launch(args);}
}
