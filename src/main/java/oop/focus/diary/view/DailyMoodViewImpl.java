package oop.focus.diary.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import oop.focus.common.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DailyMoodViewImpl implements View, DailyMoodView {
    private static final double ICON_DIM = 80;
    private static final String SEP = File.separator;
    private final Map<Integer, ImageView> map;
    private final AnchorPane pane;
    private final Integer value;
    public DailyMoodViewImpl(final Integer value) {
        this.pane = new AnchorPane();
        this.value = value;
        this.map = new HashMap<>();
        try {
            int i = 0;
            final Path iconsDir = Path.of(new File(".").getCanonicalPath() + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "icons");
            for (final File elem : Objects.requireNonNull(iconsDir.toFile().listFiles())) {
                this.map.put(i, new ImageView(new Image(new FileInputStream(elem))));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Set<ImageView> getIcons() {
        return new HashSet<>(this.map.values());
    }
    @Override
    public final Node getRoot() {
        this.pane.getChildren().add(this.map.get(value));
        this.map.get(value).setFitWidth(ICON_DIM);
        this.map.get(value).setFitHeight(ICON_DIM);
        return this.pane;
    }
}
