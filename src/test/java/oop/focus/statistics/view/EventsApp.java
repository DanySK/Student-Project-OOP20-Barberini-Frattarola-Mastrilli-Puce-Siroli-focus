package oop.focus.statistics.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.common.Controller;
import oop.focus.common.Repetition;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.statistics.controller.EventsStatistics;
import org.joda.time.LocalDateTime;

public class EventsApp extends Application {

    LocalDateTime today = LocalDateTime.now();
    Event e1 = new EventImpl("Event1", this.today, this.today.plusDays(5), Repetition.BIMONTHLY),
            e2 = new EventImpl("Event2", this.today, this.today.plusDays(5), Repetition.BIMONTHLY),
            e3 = new EventImpl("Event3", this.today, this.today.plusDays(5), Repetition.BIMONTHLY),
            e4 = new EventImpl("Event4", this.today, this.today.plusDays(5), Repetition.BIMONTHLY),
            e5 = new EventImpl("Event5", this.today, this.today.plusDays(5), Repetition.BIMONTHLY);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var db = new DataSourceImpl();
        try {
            db.getEvents().save(this.e1);
            db.getEvents().save(this.e2);
            db.getEvents().save(this.e3);
            db.getEvents().save(this.e4);
            db.getEvents().save(this.e5);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
        Controller controller = new EventsStatistics(db);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot(), 500, 600));
        primaryStage.show();
        /*this.createData(manager::removeAccount, manager);
        try {
            db.getEvents().save(e1);
            db.getEvents().save(e2);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }*/
    }
}
