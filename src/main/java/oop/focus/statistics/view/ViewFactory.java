package oop.focus.statistics.view;

import javafx.scene.layout.VBox;
import oop.focus.common.View;

import java.util.List;

/**
 * A factory used to combine multiple views into one in different ways.
 */
public class ViewFactory {

    /**
     * Create a single view combining vertically the input views.
     *
     * @param input the input views to combine
     * @return the view
     */
    public final View createVertical(final List<View> input) {
        return () -> {
            VBox v = new VBox();
            input.stream().map(View::getRoot).forEach(e -> v.getChildren().add(e));
            return v;
        };
    }
}
