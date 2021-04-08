package oop.focus.diary.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oop.focus.diary.controller.UseControllerDiary;


public class SingleTitledPaneDiaryImpl implements SingleTitledPaneDiary {
    private final Button modify = new Button("Modify");
    private final BorderPane pane = new BorderPane();
    private final TextArea newContent = new TextArea();

    private TitledPane title;
    public SingleTitledPaneDiaryImpl() {
        this.modify.setOnMouseClicked((EventHandler<Event>) event -> {
            UseControllerDiary.getCF().updatePage(this.title.getText(), this.newContent.getText());
            this.pane.setTop(this.createBox(this.title.getText()));
            this.modify.setDisable(true);
        });
    }
    private VBox createBox(final String s) {
        final VBox box = new VBox();
        final Label label = new Label();
        label.setText(UseControllerDiary.getCF().getContentByName(s));
        box.getChildren().add(label);
        return box;
    }

    @Override
    public TitledPane createTitledPane(final String s) {
        this.title = new TitledPane();
        this.modify.setDisable(true);
        this.title.setText(s);
        this.pane.setTop(this.createBox(s));
        this.pane.setBottom(this.modify);
        BorderPane.setAlignment(this.modify, Pos.TOP_CENTER);
        this.title.setContent(this.pane);
        this.title.getContent().setOnMouseClicked((EventHandler<Event>) event -> {
            this.newContent.setText(UseControllerDiary.getCF().getContentByName(s));
            this.pane.setTop(this.newContent);
            this.modify.setDisable(false);
        });
        return this.title;
    }
}
