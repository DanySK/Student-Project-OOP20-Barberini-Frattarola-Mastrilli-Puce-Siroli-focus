package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.controller.DiarySections;
import oop.focus.diary.controller.DiarySectionsControllerImpl;
import oop.focus.diary.controller.Style;
import oop.focus.homepage.model.EventManagerImpl;

public class GeneralDiaryView implements View {
    private static final double BUTTON_HEIGHT = 0.3;
    private static final double VBOX_WIDTH = 0.2;
    private static final double VBOX_SPACING = 0.01;
    private static final double INSETS = 20;
    private final BorderPane pane;
    private final VBox vBox;
    private final DiarySectionsControllerImpl controller;
    public GeneralDiaryView(final DataSourceImpl dataSource, final EventManagerImpl eventManager) {
        this.controller = new DiarySectionsControllerImpl(dataSource, eventManager);
        this.pane = new BorderPane();
        this.vBox = new VBox();
        this.setView();
    }
    private void setView() {
        for (final var elem : DiarySections.values()) {
            final Button b = new Button(elem.getName());
            b.setOnMouseClicked(event -> {
                pane.setCenter(getPane(elem));
                setStyle(elem, pane);
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
       }
    private Node getPane(final Enum<DiarySections> elem) {
        if (elem.equals(DiarySections.DIARY)) {
            return this.controller.getDiary();
        } else if (elem.equals(DiarySections.STOPWATCH)) {
            return this.controller.getStopwatch();
        }
        return this.controller.getTimer();
    }

    private void setStyle(final Enum<DiarySections> elem,BorderPane pane) {
        if(elem.equals(DiarySections.DIARY)){
            pane.getStylesheets().clear();
            pane.getStylesheets().add(Style.DIARY_STYLE.getPath());
        } else if (elem.equals(DiarySections.STOPWATCH)) {
            pane.getStylesheets().clear();
            pane.getStylesheets().add(Style.STOPWATCH_STYLE.getPath());
        } else {
            pane.getStylesheets().clear();
            pane.getStylesheets().add(Style.TIMER_STYLE.getPath());
        }
    }
    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
