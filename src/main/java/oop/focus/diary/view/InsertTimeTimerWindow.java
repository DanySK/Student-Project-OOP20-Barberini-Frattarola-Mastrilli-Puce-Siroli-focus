package oop.focus.diary.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;
import oop.focus.common.View;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.InsertTimeTimerController;
import org.joda.time.LocalTime;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Represents a new window that allows the user's choice of the timer time. There are three {@link Button }
 * with a preset time and a quarter that allows the user to enter the time manually.
 */
public class InsertTimeTimerWindow implements Initializable, View {

    @FXML
    private Pane pane;
    @FXML
    private Button save;
    @FXML
    private TextField hours;
    @FXML
    private TextField seconds;
    @FXML
    private TextField minutes;
    @FXML
    private Label separate0;
    @FXML
    private Label separate1;

    private Parent root;

    private final InsertTimeTimerController controller;

    /**
     * Instantiates a new insert time timer window and opens the relative FXML file.
     * @param controller    insert time timer controller
     */
    public InsertTimeTimerWindow(final InsertTimeTimerController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.INSERT_TIMER_TIME.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the format of {@link TextField} of window.
     * @param field the field to of which to set the format
     * @param string    the pattern of formatter
     */
    private void setTimeFormatter(final TextField field, final String string) {
        final SimpleDateFormat format = new SimpleDateFormat(string, Locale.ITALIAN);
        try {
            field.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.separate0.setText(":");
        this.separate1.setText(":");
        this.save.setText("Salva");
        this.setTimeFormatter(this.hours, "HH");
        this.setTimeFormatter(this.minutes, "mm");
        this.setTimeFormatter(this.seconds, "ss");
        this.save.setOnMouseClicked(event -> {
            final LocalTime r = new LocalTime(Integer.parseInt(this.hours.getText()), Integer.parseInt(this.minutes.
                    getText()), Integer.parseInt(this.seconds.getText()));
            this.controller.setNewValue(r);
            final Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.root;
    }
}
