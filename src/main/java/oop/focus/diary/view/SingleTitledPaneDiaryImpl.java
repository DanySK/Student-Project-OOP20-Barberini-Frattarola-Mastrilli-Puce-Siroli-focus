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
import oop.focus.diary.controller.DiaryPagesController;


public class SingleTitledPaneDiaryImpl implements SingleTitledPaneDiary {
    private final Button modify = new Button("Modify");
    private final BorderPane pane = new BorderPane();
    private final TextArea newContent = new TextArea();
    private TitledPane title;
    private final DiaryPagesController controller;
    public SingleTitledPaneDiaryImpl(final DiaryPagesController controller) {
        this.controller = controller;
        this.newContent.setWrapText(true);
        this.modify.setOnMouseClicked((EventHandler<Event>) event -> {
            controller.updatePage(this.title.getText(), this.newContent.getText());
            this.pane.setTop(this.createBox(this.title.getText()));
            this.modify.setDisable(true);
        });
    }

    /**
     * The method creates a new VBox, which is put as content in each titled pane.
     * This VBox has a label(which represent the content of a diary's page), and a button, which pressed allows to
     * modify the content of that diary's page.
     * @param s the title of page's diary of which save the content
     * @return  the new VBox created
     */
    private VBox createBox(final String s) {
        final VBox box = new VBox();
        final Label label = new Label();
        label.setText(controller.getContentByName(s));
        box.getChildren().add(label);
        return box;
    }

    @Override
    public final TitledPane createTitledPane(final String s) {
        this.title = new TitledPane();
        this.modify.setDisable(true);
        this.title.setText(s);
        this.pane.setTop(this.createBox(s));
        this.pane.setBottom(this.modify);
        BorderPane.setAlignment(this.modify, Pos.TOP_CENTER);
        this.title.setContent(this.pane);
        this.title.getContent().setOnMouseClicked((EventHandler<Event>) event -> {
            this.newContent.setText(controller.getContentByName(s));
            this.pane.setTop(this.newContent);
            this.modify.setDisable(false);
        });
        return this.title;
    }
}
