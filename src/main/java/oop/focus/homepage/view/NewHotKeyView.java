package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import oop.focus.finance.model.Repetition;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class NewHotKeyView implements Initializable, View {

    @FXML
    private Pane paneNewHotKey;

    @FXML
    private Label newHotKeyName, newHotKeyCategory;
    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveHotKeyButton, deleteButton, goBackButton;

    @FXML
    private ComboBox<String> categoryComboBox;

    private final HomePageController controller;
    private Parent root;

    public NewHotKeyView(final HomePageController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layouts/homepage/addNewHotKey.fxml"));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final ComboBoxFiller comboBox = new ComboBoxFiller();
        this.categoryComboBox.setItems(comboBox.getHotKey().getItems());
    }

    @FXML
    public final void delete(final ActionEvent event) {
        this.nameTextField.setText(" ");
        this.categoryComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final HotKeyMenuView menu = new HotKeyMenuView(this.controller);
        this.paneNewHotKey.getChildren().clear();
        this.paneNewHotKey.getChildren().add(menu.getRoot());
    }

    @FXML
    public final void saveHotKey(final ActionEvent event) throws IOException {
        final String name = nameTextField.getText();
        if (!categoryComboBox.getSelectionModel().isEmpty() && !name.isEmpty()) {
            this.controller.saveHotKey(new HotKeyImpl(name, HotKeyType.getTypeFrom(categoryComboBox.getSelectionModel().getSelectedItem())));
            this.goBack(event);
        } else {
            this.controller.showAllert();
        }
    }

    public final Parent getRoot() {
        return this.root;
    }
}
