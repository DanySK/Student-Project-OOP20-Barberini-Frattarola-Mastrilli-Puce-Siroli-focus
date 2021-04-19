package oop.focus.statistics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.common.View;

import java.util.List;

/**
 * The interface View factory defines methods to create views or to combine multiple views into one in different ways.
 */
public interface ViewFactory {
    /**
     * The dimensions of the primary screen.
     */
    Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();

    /**
     * Creates an empty {@link Pane} where the elements are positioned with a distance
     * from the edges and a distance between them according to the screen with the given ratio values.
     *
     * @param spacingRatio the spacing ratio between the elements.
     * @param xRatio       the left and right borders distance ratio
     * @param yRatio       the top and bottom borders distance ratio
     * @return the pane
     */
    static Pane verticalWithPadding(final double spacingRatio, final double xRatio, final double yRatio) {
        final VBox vBox = new VBox(spacingRatio * ViewFactoryImpl.SCREEN_BOUNDS.getHeight());
        vBox.setAlignment(Pos.TOP_CENTER);
        final var px = xRatio * ViewFactory.SCREEN_BOUNDS.getWidth();
        final var py = yRatio * ViewFactory.SCREEN_BOUNDS.getWidth();
        final var i = new Insets(py, px, py, px);
        vBox.setPadding(i);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        return vBox;
    }

    /**
     * Creates an auto-resizing {@link View}, where the elements are combined vertically.
     *
     * @param input the input views to combine
     * @return the view
     */
    View createVerticalAutoResizing(List<View> input);

    /**
     * Creates a {@link View}, where the elements are combined horizontally.
     *
     * @param input the input views to combine
     * @return the view
     */
    View createHorizontal(List<View> input);

    /**
     * Creates a {@link View}, where the elements are combined vertically.
     *
     * @param input the input nodes to combine
     * @return the view
     */
    View createVertical(List<Node> input);

    /**
     * Creates an auto-resizing {@link View}, where the elements are combined vertically.
     *
     * @param input the input nodes to combine
     * @return the view
     */
    View createVerticalAutoResizingWithNodes(List<Node> input);
}
