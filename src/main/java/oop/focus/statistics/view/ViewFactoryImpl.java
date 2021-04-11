package oop.focus.statistics.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import oop.focus.common.View;

import java.util.List;

/**
 * Implementation of {@link ViewFactory}.
 */
public class ViewFactoryImpl implements ViewFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final View createVerticalAutoResizing(final List<View> input) {
        return () -> {
            var v = new VBox();
            v.setAlignment(Pos.CENTER);
            input.forEach(i -> VBox.setVgrow(i.getRoot(), Priority.ALWAYS));
            input.stream().map(View::getRoot).forEach(e -> v.getChildren().add(e));
            HBox.setHgrow(v, Priority.ALWAYS);
            return v;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View createHorizontal(final List<View> input) {
        return () -> {
            var v = new HBox();
            v.setAlignment(Pos.TOP_CENTER);
            input.forEach(i -> VBox.setVgrow(i.getRoot(), Priority.ALWAYS));
            input.stream().map(View::getRoot).forEach(e -> v.getChildren().add(e));
            return v;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View createVertical(final List<Node> input) {
        return () -> {
            var v = new VBox();
            v.setAlignment(Pos.CENTER);
            input.forEach(i -> VBox.setVgrow(i, Priority.ALWAYS));
            input.forEach(e -> v.getChildren().add(e));
            return v;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View createVerticalAutoResizingWithNodes(final List<Node> input) {
        return () -> {
            var v = new VBox();
            v.setAlignment(Pos.CENTER);
            input.forEach(i -> VBox.setVgrow(i, Priority.ALWAYS));
            input.forEach(e -> v.getChildren().add(e));
            VBox.setVgrow(v, Priority.ALWAYS);
            return v;
        };
    }
}
