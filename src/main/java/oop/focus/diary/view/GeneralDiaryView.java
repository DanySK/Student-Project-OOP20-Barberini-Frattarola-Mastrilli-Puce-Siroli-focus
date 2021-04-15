package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;
import oop.focus.diary.controller.DiarySections;
import oop.focus.diary.controller.DiarySectionsControllerImpl;
import oop.focus.diary.controller.Style;

public class GeneralDiaryView implements View {
    private static final double BUTTON_HEIGHT = 0.3;
    private static final double VBOX_WIDTH = 0.2;
    private static final double VBOX_SPACING = 0.01;
    private static final double INSETS = 20;
    private final BorderPane pane;
    private final VBox vBox;
    private final DiarySectionsControllerImpl controller;
    public GeneralDiaryView(final DiarySectionsControllerImpl controller) {
        this.controller = controller;
        this.pane = new BorderPane();
        this.vBox = new VBox();
        this.setView();
    }
    private void setView() {
        for (final var elem : DiarySections.values()) {
            final Button b = new Button(elem.getName());
            b.setOnMouseClicked(event -> {
                //setStyle(elem, pane);
                //pane.getChildren().clear();
                pane.setCenter(getPane(elem, b));

            });
           b.prefWidthProperty().bind(this.vBox.widthProperty());
            b.prefHeightProperty().bind(this.vBox.heightProperty().multiply(BUTTON_HEIGHT));
            this.vBox.getChildren().add(b);
            this.vBox.prefWidthProperty().bind(this.pane.widthProperty().multiply(VBOX_WIDTH));
            this.vBox.prefHeightProperty().bind(this.pane.heightProperty());
            this.vBox.setPadding(new Insets(INSETS));
            this.vBox.spacingProperty().bind(this.pane.heightProperty().multiply(VBOX_SPACING));
            this.pane.setLeft(this.vBox);
        }
        pane.setCenter(this.controller.getDiary());
        //pane.getStylesheets().add(Style.DIARY_STYLE.getPath());
        //setVBox();
       }


    private Node getPane(final Enum<DiarySections> elem, final Button b) {
        if (elem.equals(DiarySections.DIARY)) {
            return this.controller.getDiary();
        } else if (elem.equals(DiarySections.STOPWATCH)) {
           // b.setStyle(Style.STOPWATCH_STYLE.getPath());
            return this.controller.getStopwatch();
        } else if (elem.equals(DiarySections.MOOD_CALENDAR)) {
            return this.controller.getMoodCalendar();
        }
        return this.controller.getTimer();
    }

    private void setStyle(final Enum<DiarySections> elem, final BorderPane pane) {
        if (elem.equals(DiarySections.DIARY)) {
            pane.getStylesheets().clear();
            pane.getStylesheets().add(Style.DIARY_STYLE.getPath());
           //setVBox();
        } else if (elem.equals(DiarySections.STOPWATCH)) {
            pane.getStylesheets().clear();
            pane.getStylesheets().add(Style.STOPWATCH_STYLE.getPath());
           // setVBox();
        } else if (elem.equals(DiarySections.MOOD_CALENDAR)) {
            pane.getStylesheets().clear();
            //pane.getStylesheets().add(Style.MOOD_CALENDAR_STYLE.getPath());
            controller.getCalendarMonthController().setFontSize(30);
        } else {
            pane.getStylesheets().clear();
            pane.getStylesheets().add(Style.TIMER_STYLE.getPath());
        //    setVBox();
        }
    }
   // private void setVBox() {
   //     this.vBox.getChildren().forEach(a -> a.setStyle("-fx-background-color: #9efa89 ; -fx-font-size: 23"));
   // }
    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
