package oop.focus.diary.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import oop.focus.common.View;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.TotalTimeControllerImpl;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StopwatchView implements  Initializable, View {
    private static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private static final double INSETS = 20;
    private static final double H_GAP_PERCENTAGE = 0.07;
    private static final double V_GAP_PERCENTAGE = 0.2;
    private static final double LABEL_HEIGHT_PERCENTAGE = 0.15;
    private static final double COMBO_BOX_HEIGHT = 0.1;
    private static final double COMBO_BOX_WIDTH = 0.15;
    private static final double ADD_EVENT_BUTTON_DIM = 0.05;
    private static final double BUTTON_WIDTH_PERCENTAGE = 0.2;
    @FXML
    private Pane pane;

    @FXML
    private Label nameEventLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private ComboBox<String> chooseEvent;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label counterLabel;

    @FXML
    private Button addNewEvent;
    private Parent root;
    private Dimension2D dim;
    private final TotalTimeControllerImpl totalTimeController;
    private final CounterControllerImpl specificController;
    public StopwatchView(final Dimension2D dim, final TotalTimeControllerImpl totalTimeController,
                         final CounterControllerImpl specificController) {
        this.totalTimeController = totalTimeController;
        this.specificController = specificController;
        this.dim = dim;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.STOPWATCH.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setProperties();

    }
    private void setProperties() {
        GridPane grid = new GridPane();
        grid.addRow(0, this.nameEventLabel, this.chooseEvent, this.addNewEvent, this.timeLabel);
        grid.add(this.counterLabel, (grid.getColumnCount() - 2) / 2, 1, 2, 1);
        grid.add(this.startButton, 1, 2, 2, 1);
        grid.add(this.stopButton, 2, 2, 2, 1);
        CommonView.setGrid(grid, this.pane, H_GAP_PERCENTAGE, V_GAP_PERCENTAGE, this.counterLabel, this.nameEventLabel);
        CommonView.setDimLabel(List.of(this.counterLabel, this.timeLabel, this.nameEventLabel), this.pane,
                LABEL_HEIGHT_PERCENTAGE);
        CommonView.setDimButton(List.of(this.startButton, this.stopButton), this.pane, BUTTON_WIDTH_PERCENTAGE,
                LABEL_HEIGHT_PERCENTAGE);
        this.chooseEvent.prefHeightProperty().bind(this.pane.heightProperty().multiply(COMBO_BOX_HEIGHT));
        this.chooseEvent.prefWidthProperty().bind(this.pane.widthProperty().multiply(COMBO_BOX_WIDTH));
        this.addNewEvent.prefHeightProperty().bind(this.pane.heightProperty().multiply(ADD_EVENT_BUTTON_DIM));
        this.addNewEvent.prefWidthProperty().bind(this.pane.widthProperty().multiply(ADD_EVENT_BUTTON_DIM));
        grid.setPadding(new Insets(INSETS));
    }


    public final void initialize(final URL location, final ResourceBundle resources) {
        final UpdateView connection = new UpdateView(specificController, this.counterLabel);
        this.startButton.setDisable(true);
        this.stopButton.setDisable(true);
        CommonView.setConfig(this.chooseEvent, this.nameEventLabel, this.startButton, this.stopButton, this.addNewEvent,
                this.addNewEvent, this.dim, totalTimeController);
        this.counterLabel.setText(LocalTime.MIDNIGHT.toString(TIME_FORMATTER));

        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.timeLabel.setText(totalTimeController.getTotalTime(newValue).toString(TIME_FORMATTER));
            specificController.setStarter(newValue, LocalTime.MIDNIGHT);
            this.startButton.setDisable(false);
            }
        );
        this.startButton.setOnMouseClicked(event -> {
            CommonView.addStartListener(specificController, connection, this.startButton, this.stopButton,
                    this.chooseEvent);
            this.addNewEvent.setDisable(true);
        });

        this.stopButton.setOnMouseClicked(event -> {
            CommonView.addStopTimer(specificController, this.startButton, this.stopButton, this.timeLabel,
                    this.chooseEvent, TIME_FORMATTER, totalTimeController);
            specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(this.counterLabel.getText(),
                    TIME_FORMATTER));
            this.chooseEvent.setDisable(false);
            this.addNewEvent.setDisable(false);
        });
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}
