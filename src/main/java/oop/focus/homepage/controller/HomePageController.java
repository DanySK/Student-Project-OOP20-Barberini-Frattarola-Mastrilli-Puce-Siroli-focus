package oop.focus.homepage.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class HomePageController implements Initializable {

    @FXML
    private Pane paneCalendarHomePage;

    @FXML
    private Button modifyButton;

    @FXML
    private VBox vboxTodayEvent;

    @FXML
    private ScrollBar scrollBarTodayEvent;

    @FXML
    private HBox hboxTodayEvent;

    @FXML
    private VBox vboxHourTodayEvent;

    @FXML
    private Pane paneHotKeyView;

    @FXML
    private VBox vboxHotKeyList;

    @FXML
    private Button addHotKeyButton;

    @FXML
    private Button modifyHotKey;

    @FXML
    private Button deleteHotKeyButton;

    @FXML
    private Button goBackButton;

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
    private ComboBox<String> categoryComboBox = new ComboBox<>();

    //private List<HotKey> hotKeyTrack = new ArrayList<>();
    private HotKey lastSaved;

    @FXML
    public final void addNewHotKey(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("addNewHotKey.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

        //deve prendere i dati del database e creare un nuovo tasto rapido se questo è stato inserito.
     }

    @FXML
    public final void deleteEnter(final ActionEvent event) {
        nameTextField.setText(" ");
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("calendarHomePage.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    public final void modifyClicked(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("choiceMenu.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    public final void saveHotKey(final ActionEvent event) throws IOException {
        final String name = nameTextField.getText();
        final HotKeyType type = HotKeyType.getTypeFrom(categoryComboBox.getSelectionModel().getSelectedItem());
        //this.hotKeyTrack.add(new HotKeyImpl(name, type));
        this.lastSaved = new HotKeyImpl(name, type);
        this.refreshAfterSave();
        this.modifyClicked(event);
    }

    private void refreshAfterSave() {
        vboxHotKeyList.getChildren().add(new Label(this.lastSaved.getName()));
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        categoryComboBox.getItems().add("Attività");
        categoryComboBox.getItems().add("Contatore");
        categoryComboBox.getItems().add("Evento");
    }

}
