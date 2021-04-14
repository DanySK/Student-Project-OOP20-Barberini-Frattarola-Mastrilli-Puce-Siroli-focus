package oop.focus.homepage.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import oop.focus.common.View;
import oop.focus.db.DataSource;

public class GeneralHomePageView implements View, Initializable {

	@FXML
    private VBox calendarHomePage;

    @FXML
    private VBox financeHomePage;

    private final GeneralHomePageController controller;

    public GeneralHomePageView(final GeneralHomePageController generalHomePageController) {
    	this.controller = generalHomePageController;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@Override
	public Node getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
