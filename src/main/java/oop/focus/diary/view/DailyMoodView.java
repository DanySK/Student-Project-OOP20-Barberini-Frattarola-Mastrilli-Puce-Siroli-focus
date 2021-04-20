package oop.focus.diary.view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import oop.focus.common.View;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Daily Mood View implements {@link View} and represents the single daily mood, identified by a value given in input.
 * The relevant icon is loaded from the appropriate directory and is put in a {@link javafx.scene.layout.Pane}, that
 * the class returns.
 */
public class DailyMoodView implements View {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final double ICON_DIM = 0.04;
    private final BorderPane pane;
    private  ImageView imageView;

    /**
     * Instantiates the daily mood whose value is put in input.
     * @param value the value of daily mood of which the class returns its display.
     */
    public DailyMoodView(final Integer value) {
        this.pane = new BorderPane();

        try {
            URI uri = ClassLoader.getSystemResource("icons//").toURI();
            String mainPath = Paths.get(uri).toString();
            Path path = Paths.get(mainPath ,String.valueOf(value).concat(".png"));
            this.imageView = new ImageView(new Image(new FileInputStream( path.toFile())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
       this.imageView.fitWidthProperty().set(SCREEN_BOUNDS.getWidth() * ICON_DIM);
        this.imageView.setPreserveRatio(true);
        this.pane.setCenter(this.imageView);
        return this.pane;
    }
}
