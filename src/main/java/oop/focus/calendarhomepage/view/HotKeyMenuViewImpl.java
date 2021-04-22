package oop.focus.calendarhomepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import oop.focus.common.Linker;
import oop.focus.calendarhomepage.controller.FXMLPaths;
import oop.focus.calendarhomepage.controller.HomePageController;
import oop.focus.calendarhomepage.controller.HotKeyController;
import oop.focus.calendarhomepage.model.HotKey;

public class HotKeyMenuViewImpl implements  HotKeyMenuView {


    @FXML
    private AnchorPane paneHotKeyView;

    @FXML
    private Button addHotKeyButton, deleteElement, goBackButton;

    @FXML
    private TableView<HotKey> tableHotKeyList;

    @FXML
    private TableColumn<HotKey, String> nome, tipo;

    private final HotKeyController controller;
    private final HomePageController controllerHomePage;
    private Node root;

    private final ObservableList<HotKey> list;
    private final ObservableSet<HotKey> set;

    public HotKeyMenuViewImpl(final HotKeyController controller, final HomePageController homePageController) {
        this.controller = controller;
        this.controllerHomePage = homePageController;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.HOTKEYMENU.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperties();

        this.set = this.controller.getSortedHotKey();
        this.list = FXCollections.observableArrayList();
        Linker.setToList(this.set, this.list);
        this.populateTableView();
        this.tableHotKeyList.setItems(this.list);
    }

    private void setProperties() {
        this.tableHotKeyList.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(Constants.TABLE_SIZE));
        this.tableHotKeyList.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(Constants.TABLE_SIZE));

        this.nome.prefWidthProperty().bind(this.tableHotKeyList.widthProperty().divide(2));
        this.tipo.prefWidthProperty().bind(this.tableHotKeyList.widthProperty().divide(2));

        this.deleteElement.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(Constants.DELETE_BUTTON_WIDTH));
        this.deleteElement.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(Constants.PREF_BUTTON_HEIGHT));

        this.goBackButton.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(Constants.PREF_BUTTON_WIDTH));
        this.goBackButton.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(Constants.PREF_BUTTON_HEIGHT));

        this.addHotKeyButton.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(Constants.PREF_BUTTON_WIDTH));
        this.addHotKeyButton.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(Constants.PREF_BUTTON_HEIGHT));
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setButtonAction();
    }

    @FXML
    public final void addNewHotKey(final ActionEvent event) {
        final GenericAddView newHotKey = new NewHotKeyViewImpl(this.controller, this.controllerHomePage);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newHotKey.getRoot()));
        stage.show();
    }

    private void deleteItem() {
        this.controller.deleteHotKey(this.tableHotKeyList.getSelectionModel().getSelectedItem());
        ((HomePageBaseView) this.controllerHomePage.getView()).fullVBoxHotKey();
    }

    @FXML
    public final void deleteSelectedRowItem(final ActionEvent event) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di volere eliminare questo tasto rapido?");

        final Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) {
            alert.close();
        } else {
            this.deleteItem();
        }
    }

    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void goBack(final ActionEvent event) {
        final Stage stage = (Stage) this.root.getScene().getWindow();
        stage.close();
    }

    public final void populateTableView() {
        this.nome.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.nome.setCellFactory(TextFieldTableCell.forTableColumn());
        this.nome.setOnEditCommit(event -> {
            final HotKey hotKey = event.getRowValue();
            hotKey.setName(event.getNewValue());
        });

        this.tipo.setCellValueFactory(new PropertyValueFactory<>("typeRepresentation"));
        this.tipo.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tipo.setOnEditCommit(event -> {
            final HotKey hotKey = event.getRowValue();
            hotKey.setType(event.getNewValue());
        });

        this.tableHotKeyList.setEditable(false);
    }

    public final void setButtonAction() {
        this.addHotKeyButton.setOnAction(this::addNewHotKey);
        this.deleteElement.setOnAction(this::deleteSelectedRowItem);
        this.goBackButton.setOnAction(this::goBack);

    }

    private static class Constants {
        private static final double TABLE_SIZE = 0.7;
        private static final double PREF_BUTTON_WIDTH = 0.15;
        private static final double PREF_BUTTON_HEIGHT = 0.05;
        private static final double DELETE_BUTTON_WIDTH = 0.5;
    }
}

