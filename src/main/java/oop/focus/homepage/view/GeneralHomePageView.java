package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import oop.focus.common.View;
import oop.focus.homepage.controller.GeneralHomePageController;

public class GeneralHomePageView implements View, Initializable {

    @FXML
    private VBox paneHomePage;

    @FXML
    private HBox container;

    @FXML
    private VBox calendarHomePage;

    @FXML
    private VBox financeHomePage;

    private final GeneralHomePageController controller;
    private Node root;

    public GeneralHomePageView(final GeneralHomePageController generalHomePageController) {
        this.controller = generalHomePageController;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/homePageGenerale.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperties();
    }

    private void setProperties() {
        VBox.setVgrow(this.paneHomePage, Priority.ALWAYS);
        HBox.setHgrow(this.paneHomePage, Priority.ALWAYS);

        VBox.setVgrow(this.container, Priority.ALWAYS);
        HBox.setHgrow(this.container, Priority.ALWAYS);

        VBox.setVgrow(this.financeHomePage, Priority.ALWAYS);
        HBox.setHgrow(this.financeHomePage, Priority.ALWAYS);

        VBox.setVgrow(this.calendarHomePage, Priority.ALWAYS);
        HBox.setHgrow(this.calendarHomePage, Priority.ALWAYS);
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.paneHomePage.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        this.calendarHomePage.getChildren().add(this.controller.getCalendarHomePage());
        this.financeHomePage.getChildren().add(this.controller.getFinanaceHomePage());
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }
}
