package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import oop.focus.homepage.controller.FXMLPaths;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.controller.HotKeyController;
import oop.focus.homepage.model.HotKey;

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
        this.populateTableView();
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
        this.controllerHomePage.getView().fullVBoxHotKey();
        this.refreshTableView();
    }

    @FXML
    public final void deletSelectedRowItem(final ActionEvent event) {
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
        this.tableHotKeyList.getItems().clear();
        this.tableHotKeyList.setItems(this.controller.getSortedHotKey());
    }

    private void refreshTableView() {
        this.tableHotKeyList.getItems().removeAll(this.tableHotKeyList.getSelectionModel().getSelectedItems());
    }

    public final void setButtonAction() {
        this.addHotKeyButton.setOnAction(event -> {
            this.addNewHotKey(event);
        });
        this.deleteElement.setOnAction(this::deletSelectedRowItem);
        this.goBackButton.setOnAction(event -> {
            this.goBack(event);
        });

    }

    private static class Constants {
        private static final double TABLE_SIZE = 0.7;
        private static final double PREF_BUTTON_WIDTH = 0.15;
        private static final double PREF_BUTTON_HEIGHT = 0.05;
        private static final double DELETE_BUTTON_WIDTH = 0.5;
    }
}

