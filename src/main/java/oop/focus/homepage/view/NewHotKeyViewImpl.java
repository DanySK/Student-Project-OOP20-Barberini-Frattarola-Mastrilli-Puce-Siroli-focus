package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.homepage.controller.FXMLPaths;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.controller.HotKeyController;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class NewHotKeyViewImpl implements  GenericAddView {

    @FXML
    private Pane paneNewHotKey;

    @FXML
    private Label newHotKeyName, newHotKeyCategory, labelAddNew, labelHotKey;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveHotKeyButton, deleteButton, goBackButton;

    @FXML
    private ComboBox<String> categoryComboBox;

    private final HotKeyController controller;
    private final HomePageController homePageController;
    private Node root;

    public NewHotKeyViewImpl(final HotKeyController controller, final HomePageController controllerHomePage) {
        this.controller = controller;
        this.homePageController = controllerHomePage;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWHOTKEY.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setProperty();
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setButtonOnAction();
        final ComboBoxFiller comboBox = new ComboBoxFiller();
        this.categoryComboBox.setItems(comboBox.getHotKey());
    }

    private void setProperty() {
        this.labelAddNew.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(Constants.LABEL_WIDTH));
        this.labelAddNew.prefHeightProperty().bind(this.paneNewHotKey.heightProperty().multiply(Constants.LABEL_HEIGHT));
        this.labelAddNew.setAlignment(Pos.CENTER);

        this.labelHotKey.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(Constants.LABEL_WIDTH));
        this.labelHotKey.prefHeightProperty().bind(this.paneNewHotKey.heightProperty().multiply(Constants.LABEL_HEIGHT));
        this.labelHotKey.setAlignment(Pos.CENTER);

        this.categoryComboBox.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(Constants.PREF_WIDTH));
        this.nameTextField.prefWidthProperty().bind(this.paneNewHotKey.widthProperty().multiply(Constants.PREF_WIDTH));
    }

    @FXML
    public final void delete(final ActionEvent event) {
        this.nameTextField.clear();
        this.categoryComboBox.getSelectionModel().clearSelection();
    }

    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.paneNewHotKey.getScene().getWindow();
        stage.close();
    }

    @FXML
    public final void save(final ActionEvent event) throws IOException {
        final String name = nameTextField.getText();
        if (categoryComboBox.getSelectionModel().isEmpty() || name.isEmpty()) {
            final AlertFactory alertCreator = new AlertFactoryImpl();
            final Alert alert = alertCreator.createWarningAlert();
            alert.setHeaderText("I campi non sono stati riempiti correttamente!");
            alert.show();
        } else {
            try {
                this.controller.saveHotKey(new HotKeyImpl(name, HotKeyType.getTypeFrom(categoryComboBox.getSelectionModel().getSelectedItem())));
                this.controller.getView().populateTableView();
                this.homePageController.getView().fullVBoxHotKey();
                this.goBack(event);
            } catch (IllegalStateException e) {
                final AlertFactory alertCreator = new AlertFactoryImpl();
                final Alert alert = alertCreator.createConfirmationAlert();
                alert.show();
            }
        }
    }


    public final void setButtonOnAction() {
        this.goBackButton.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.deleteButton.setOnAction(event -> this.delete(event));

        this.saveHotKeyButton.setOnAction(event -> {
            try {
                this.save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static class Constants {
        public static final double PREF_WIDTH  = 0.3;
        private static final double LABEL_WIDTH = 0.5;
        private static final double LABEL_HEIGHT = 0.05;
   }
}
