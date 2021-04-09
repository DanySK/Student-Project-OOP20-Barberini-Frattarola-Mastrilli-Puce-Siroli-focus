package oop.focus.diary.view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.diary.controller.ControllersFactoryImpl;
import oop.focus.diary.controller.ControllersFactory;
import oop.focus.diary.controller.CounterControllerImpl;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.UseTotalTimeController;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TimerView implements Initializable, View {
    private static final  DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH : mm : ss");
    private static final  DateTimeFormatter TIME_FORMATTER_WITHOUT_HOUR = DateTimeFormat.forPattern("mm : ss");
    private static final int MINUTES_GAP = 15;
    private static final double INSETS = 20;
    private static final double H_GAP_PERCENTAGE = 0.05;
    private static final double V_GAP_PERCENTAGE = 0.15;
    private static final double LABEL_HEIGHT_PERCENTAGE = 0.10;
    private static final double COMBO_BOX_HEIGHT = 0.1;
    private static final double COMBO_BOX_WIDTH = 0.15;
    private static final double ADD_EVENT_BUTTON_DIM = 0.05;
    private static final double BUTTON_WIDTH_PERCENTAGE = 0.15;

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
    private Button timer1;

    @FXML
    private Button timer3;

    @FXML
    private Button timer2;

    @FXML
    private Button otherTime;
    @FXML
    private Button addEventButton;

    private List<Button> buttonList;
    private CounterControllerImpl specificController;
    private Parent root;
    private final Dimension2D dim;
    public TimerView(final Dimension2D dim) {
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.TIMER.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setProperties();
        this.dim = dim;

    }
    private void setTime() {
        final Consumer<String> consumer = integer -> {
            this.otherTime.setText(integer);
            this.specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(this.otherTime.getText(), TIME_FORMATTER));
            this.counterLabel.setText(this.otherTime.getText());
            this.startButton.setDisable(false);
        };
        final Scene scene = new Scene((Parent) new InsertTimeTimerWindow(consumer).getRoot());
        final Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }


    private void setProperties() {
        final GridPane grid = new GridPane();
        grid.addRow(0, this.nameEventLabel, this.chooseEvent, this.addEventButton, this.timeLabel);
        grid.add(this.counterLabel, (grid.getColumnCount() - 2) / 2, 2, 2, 1);
        grid.add(this.startButton, 1, 3, 2, 1);
        grid.add(this.stopButton, 2, 3, 2, 1);
        CommonView.setGrid(grid, this.pane, H_GAP_PERCENTAGE, V_GAP_PERCENTAGE, this.counterLabel, this.nameEventLabel);
        GridPane.setHalignment(this.addEventButton, HPos.CENTER);
        CommonView.setDimLabel(List.of(this.counterLabel, this.timeLabel, this.nameEventLabel), this.pane, LABEL_HEIGHT_PERCENTAGE);
        CommonView.setDimButton(List.of(this.startButton, this.stopButton, this.timer1, this.timer2, this.timer3, this.otherTime),
                this.pane, BUTTON_WIDTH_PERCENTAGE, LABEL_HEIGHT_PERCENTAGE);
        this.chooseEvent.prefHeightProperty().bind(this.pane.heightProperty().multiply(COMBO_BOX_HEIGHT));
        this.chooseEvent.prefWidthProperty().bind(this.pane.widthProperty().multiply(COMBO_BOX_WIDTH));
        this.addEventButton.prefHeightProperty().bind(this.pane.heightProperty().multiply(ADD_EVENT_BUTTON_DIM));
        this.addEventButton.prefWidthProperty().bind(this.pane.widthProperty().multiply(ADD_EVENT_BUTTON_DIM));
        grid.setPadding(new Insets(INSETS));
        grid.addRow(1, this.timer1, this.timer2, this.timer3, this.otherTime);
        GridPane.setHalignment(this.timer1, HPos.RIGHT);
    }


    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final ControllersFactory factory = new ControllersFactoryImpl();
        this.specificController = factory.createTimer();
        final UpdateView connection = new UpdateView(this.specificController, this.counterLabel);
        this.modifyAllButtons(true);
        this.buttonList = List.of(this.timer1, this.timer2, this.timer3);
        CommonView.setConfig(this.chooseEvent, this.nameEventLabel, this.startButton, this.stopButton, this.addEventButton,
                this.addEventButton, this.dim);
        this.otherTime.setText("Scegli");
        this.otherTime.setOnMouseClicked(event -> this.setTime());
        this.setTimeButtons();
        this.chooseEvent.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.timeLabel.setText(UseTotalTimeController.getTotalTimeController().getTotalTime(newValue).toString(TIME_FORMATTER));
            this.modifyAllButtons(false);
        });
        this.startButton.setOnMouseClicked(event -> {
            CommonView.addStartListener(this.specificController, connection, this.startButton, this.stopButton, this.chooseEvent);
            this.modifyAllButtons(true);
            this.addEventButton.setDisable(true);
        });
        this.setStopButton();
    }

    private void disableButton(final Button b) {
        this.buttonList.stream().filter(a -> !a.equals(b)).forEach(s -> s.setDisable(true));
    }
    private void setStopButton() {
        this.stopButton.setOnMouseClicked(event -> {
            if (this.specificController.isPlaying()) {
                this.specificController.stopSound();
            }
            CommonView.addStopTimer(this.specificController, this.startButton, this.stopButton, this.timeLabel, this.chooseEvent, TIME_FORMATTER);
            this.specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(this.counterLabel.getText(), TIME_FORMATTER));
            this.modifyAllButtons(false);
            this.addEventButton.setDisable(false);
            this.chooseEvent.setDisable(false);
        });
    }
    private void modifyAllButtons(final boolean disable) {
        final List<Node> list = List.of(this.timer1, this.timer2, this.timer3, this.otherTime);
        list.forEach(s -> s.setDisable(disable));
    }

    private void setTimeButtons() {
        int min = MINUTES_GAP;
        for (final var elem : this.buttonList) {
            elem.setDisable(true);
            elem.setText(LocalTime.MIDNIGHT.plusMinutes(min).toString(TIME_FORMATTER_WITHOUT_HOUR));
            min += MINUTES_GAP;
            elem.setOnMouseClicked(event -> {
                this.specificController.setStarter(this.chooseEvent.getValue(), LocalTime.parse(elem.getText(), TIME_FORMATTER_WITHOUT_HOUR));
                this.counterLabel.setText(elem.getText());
                this.disableButton(elem);
                this.otherTime.setDisable(true);
                this.startButton.setDisable(false);
            });
        }
    }

    @Override
    public Node getRoot() {
        return this.root;
    }
}

