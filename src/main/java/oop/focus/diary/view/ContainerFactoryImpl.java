package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import oop.focus.common.View;

import java.util.List;

/**
 * Implementation of {@link ContainerFactory}.
 */
public class ContainerFactoryImpl implements ContainerFactory {
    private static final int INSETS = 20;

    /**
     * {@inheritDoc}
     */
    @Override
    public View mergeVertically(final List<Node> list) {
        final VBox vBox = new VBox();
        list.forEach(a -> vBox.getChildren().addAll(a));
        vBox.paddingProperty().set(new Insets(INSETS));
        vBox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
    return () -> vBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View mergeHorizontally(final List<Node> list) {
        final HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        list.forEach(s -> hBox.getChildren().addAll(s));
        hBox.getChildren().forEach(s -> HBox.setHgrow(s, Priority.ALWAYS));
        return () -> hBox;
    }
}
