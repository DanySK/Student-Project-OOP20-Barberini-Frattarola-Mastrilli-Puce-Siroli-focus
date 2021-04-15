package oop.focus.diary.view;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import oop.focus.common.View;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.diary.controller.DailyMoodControllerImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DailyMoodSection implements View {
    private static final double NUM_ICONS = 5;
    private static final double DARK_ICONS = -0.4;
    private static final double LIGHT_ICONS = 0.0;
    private static final double BUTTON_WIDTH = 0.3;
    private static final double BUTTON_HEIGHT = 0.2;
    private static final int GRID_GAP = 5;
    private final BorderPane pane;
    private final Button button;
    private final Map<Button, Integer> map;
    private final DailyMoodControllerImpl controller;
    public DailyMoodSection(final DailyMoodControllerImpl controller) {
        this.map = new HashMap<>();
        this.controller = controller;
        this.pane = new BorderPane();
        this.button = new Button("Modifica");
        try {
            this.setGrid();
        } catch (IOException e) {
            e.printStackTrace();
        }
         this.setBrightness();
        this.map.keySet().forEach(s -> s.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(final MouseEvent event) {
                try {
                    DailyMoodSection.this.controller.addDailyMood(DailyMoodSection.this.map.get(s));
                    DailyMoodSection.this.setBrightness();
                } catch (DaoAccessException e) {
                    e.printStackTrace();
                }
            }
        }));
        this.button.setOnMouseClicked(event -> {
            try {
                this.controller.removeChoice();
                this.setBrightness();
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        });

    }
    private void setGrid() throws IOException {
        int index = 0;
        final GridPane gridPane = new GridPane();
        gridPane.setVgap(GRID_GAP);
        gridPane.setHgap(GRID_GAP);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < NUM_ICONS; i++) {
            final DailyMoodViewImpl view = new DailyMoodViewImpl(i);
            final Button b = new Button();
            this.map.put(b, i);
            b.setGraphic(view.getRoot());
            b.setStyle("-fx-border-color: transparent ; -fx-background-color: transparent");
            gridPane.add(b, index, 0);
            index++;
        }
        this.pane.setTop(gridPane);
        this.pane.setBottom(this.button);
        this.button.prefWidthProperty().bind(this.pane.widthProperty().multiply(BUTTON_WIDTH));
        this.button.prefHeightProperty().bind(this.pane.heightProperty().multiply(BUTTON_HEIGHT));
        BorderPane.setAlignment(this.button, Pos.CENTER);
    }

    private void setBrightness() {
        for (final var elem : this.map.keySet()) {
            final ColorAdjust blackout = new ColorAdjust();
            if (this.controller.getValueChosen().isPresent()) {
                if (!this.map.get(elem).equals(this.controller.getValueChosen().get())) {
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
    public final Node getRoot() {
        return this.pane;
    }
}
