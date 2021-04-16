package oop.focus.event.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import oop.focus.common.Repetition;
import oop.focus.common.View;
import oop.focus.event.controller.EventInformationController;
import oop.focus.event.controller.FXMLPaths;

public class EventInformationViewImpl implements View, Initializable {

    @FXML
    private AnchorPane paneEventInformation;

    @FXML
    private Label startDate;

    @FXML
    private Label endDate;

    @FXML
    private Label repetition;

    @FXML
    private Label insertStartDate;

    @FXML
    private Label insertEndHour;

    @FXML
    private Label labelRepetition;

    @FXML
    private Label name;

    @FXML
    private Button goBack;

    @FXML
    private Button stopRepetition;

    private Node root;
    private final EventInformationController controller;

    public EventInformationViewImpl(final EventInformationController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.EVENTINFORMATION.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperties();
    }

    private void setProperties() {
        this.name.setAlignment(Pos.CENTER);
        this.name.prefWidthProperty().bind(this.paneEventInformation.widthProperty().multiply(Constants.PREF_WIDTH));
        this.name.prefHeightProperty().bind(this.paneEventInformation.heightProperty().multiply(Constants.PREF_HEIGHT));
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setButtonOnAction();
        this.name.setText(this.controller.getEvent().getName());
        this.insertEndHour.setText(this.controller.getEvent().getEnd().toString());
        this.insertStartDate.setText(this.controller.getEvent().getStart().toString());
        this.labelRepetition.setText(this.controller.getEvent().getRipetition().toString());
    }

    private void setButtonOnAction() {
        this.stopRepetition.setOnAction(event -> this.stopRepeat());
        this.goBack.setOnAction(event -> this.goBack());
    }

    private void goBack() {
    }

    private void stopRepeat() {
        this.controller.stopRepetition();
        this.labelRepetition.setText(Repetition.ONCE.toString());
    }

    private static class Constants {
        private static final double PREF_WIDTH = 0.4;
        private static final double PREF_HEIGHT = 0.05;
    }
}
