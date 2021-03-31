package oop.focus.homepage.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.homepage.model.HotKeyType;

public class NewHotKeyView implements Initializable {

    @FXML
    private Pane paneNewHotKey;

    @FXML
    private Label newHotKeyName;

    @FXML
    private Label newHotKeyCategory;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveHotKeyButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button goBackButton;

    @FXML
    private ComboBox<String> categoryComboBox;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        for (final HotKeyType hotKey : HotKeyType.values()) {
            categoryComboBox.getItems().add(hotKey.getType());
        }
    }

    @FXML
    public final void delete(final ActionEvent event) {
        this.nameTextField.setText(" ");
        this.categoryComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("/layouts/homepage/choiceMenu.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
    }

    @FXML
    public final void saveHotKey(final ActionEvent event) throws IOException {
        final String name = nameTextField.getText();
        if (!categoryComboBox.getSelectionModel().isEmpty() && !name.isEmpty()) {
            //final HotKeyType type = HotKeyType.getTypeFrom(categoryComboBox.getSelectionModel().getSelectedItem());
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/homepage/choiceMenu.fxml"));
            final Parent root = (Parent) loader.load();
            //final HotKeyMenuController back = loader.getController();
            final Stage stage = new Stage();
            stage.setScene(new Scene(root));
            final Stage currentStage = (Stage) saveHotKeyButton.getScene().getWindow();
            currentStage.close();
            stage.show();

        } else {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("I campi non sono stati riempiti correttamente!");
            alert.setContentText("Riempire i campi o tornare indietro");
 
            final Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
                alert.close();
            }
        }
    }

}
