package oop.focus.diary.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.diary.controller.DailyMoodControllerImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;


public class DailyMoodViewImpl implements DailyMoodView {
    private static final int ICONS_DIMENSION = 85;
    private static final double DARK_ICONS = -0.4;
    private static final double LIGHT_ICONS = 0.0;
    private static final int GRID_GAP = 5;
    private static final String SEP = File.separator;
    private  final Path iconsDir = Path.of(new File(".").getCanonicalPath() + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "icons");
    private final GridPane grid;
    private final Button button;
    private final ObservableList<ImageView> images;
    private final DailyMoodControllerImpl controller;
    public DailyMoodViewImpl() throws IOException {
        this.controller = new DailyMoodControllerImpl();
        this.grid = new GridPane();
        this.button = new Button("modifica");
        this.images = FXCollections.observableArrayList();
        for (final File elem : Objects.requireNonNull(iconsDir.toFile().listFiles())) {
            this.images.add(new ImageView(new Image(new FileInputStream(elem))));
        }
        this.setGrid();
        this.setBrightness();
        this.images.forEach(s -> s.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                try {
                    controller.addDailyMood(images.indexOf(s));
                    System.out.println(images.indexOf(s));
                    setBrightness();
                } catch (DaoAccessException e) {
                    e.printStackTrace();
                }
            }
        }));
        this.button.setOnMouseClicked(event -> {
            try {
                controller.removeChoice();
                setBrightness();
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        });

    }
    private void setGrid() {
        int index = 0;
        getGrid().setVgap(GRID_GAP);
        getGrid().setHgap(GRID_GAP);
        for (final var elem : this.images) {
            elem.setFitHeight(ICONS_DIMENSION);
            elem.setFitWidth(ICONS_DIMENSION);
            elem.setPreserveRatio(true);
            this.grid.add(elem, index, 0);
            index++;
        }
    }

    private void setBrightness() {
        for (final var elem : this.images) {
            final ColorAdjust blackout = new ColorAdjust();
            if (controller.getValueChosen().isPresent()) {
                if (images.indexOf(elem) != controller.getValueChosen().get()) {
                    elem.setDisable(true);
                    blackout.setBrightness(DARK_ICONS);
                }
            } else  {
                elem.setDisable(false);
                blackout.setBrightness(LIGHT_ICONS);
            }
                elem.setEffect(blackout);
            }
        }

    @Override
    public final GridPane getGrid() {
        return this.grid;
    }
    @Override
    public final Button getButton() {
        return this.button;
    }
}
