package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import oop.focus.common.View;
import oop.focus.homepage.controller.GeneralHomePageController;

public class GeneralHomePageView implements View, Initializable {

    private final GeneralHomePageController controller;
    private BorderPane pane;
    private HBox container;

    public GeneralHomePageView(final GeneralHomePageController controller){
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pane = new BorderPane();
        this.container = new HBox();
        this.container.getChildren().add(this.controller.getCalendarHomePage());
        this.container.getChildren().add(this.controller.getFinanaceHomePage());
        this.pane.setCenter(this.container);
    }

    @Override
    public Node getRoot() {
        return this.pane;
    }
}
