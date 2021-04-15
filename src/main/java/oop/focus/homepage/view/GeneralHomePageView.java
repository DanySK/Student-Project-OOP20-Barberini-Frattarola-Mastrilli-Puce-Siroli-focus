package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;
import oop.focus.statistics.view.ViewFactoryImpl;

public class GeneralHomePageView implements View, Initializable {

	@FXML
	private VBox paneHomePage;

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
		this.setProprietis();
    }

	private void setProprietis() {
    	this.paneHomePage.autosize();

    	this.calendarHomePage.prefWidthProperty().bind(this.paneHomePage.widthProperty().divide(2));
    	this.financeHomePage.prefWidthProperty().bind(this.paneHomePage.widthProperty().divide(2));
		this.calendarHomePage.prefHeightProperty().bind(this.paneHomePage.heightProperty());
		this.financeHomePage.prefHeightProperty().bind(this.paneHomePage.heightProperty());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        this.calendarHomePage.getChildren().add(this.controller.getCalendarHomePage());
        this.financeHomePage.getChildren().add(this.controller.getFinanaceHomePage());
    }

	@Override
	public Node getRoot() {
		return this.root;
	}

	
}
