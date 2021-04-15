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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.homepage.controller.FXMLPaths;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.controller.HotKeyController;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.statistics.view.ViewFactoryImpl;

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

    public HotKeyMenuViewImpl(final HotKeyController controller, HomePageController homePageController) {
        this.controller = controller;
        this.controllerHomePage = homePageController;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.HOTKEYMENU.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setProperties();
    }

    private void setProperties() {
        this.tableHotKeyList.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(0.7));
        this.tableHotKeyList.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(0.7));

        this.nome.prefWidthProperty().bind(this.tableHotKeyList.widthProperty().divide(2));
        this.tipo.prefWidthProperty().bind(this.tableHotKeyList.widthProperty().divide(2));

        this.deleteElement.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(0.5));
        this.deleteElement.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(0.05));

        this.goBackButton.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(0.15));
        this.goBackButton.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(0.05));

        this.addHotKeyButton.prefWidthProperty().bind(this.paneHotKeyView.widthProperty().multiply(0.15));
        this.addHotKeyButton.prefHeightProperty().bind(this.paneHotKeyView.heightProperty().multiply(0.05));
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setButtonAction();
        this.populateTableView();
    }

    @FXML
    public final void addNewHotKey(final ActionEvent event) throws IOException {
        final GenericAddView newHotKey = new NewHotKeyViewImpl(this.controller, this.controllerHomePage);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newHotKey.getRoot()));
        stage.show();
    }

    private void deleteItem() {
        final HotKeyType type = tableHotKeyList.getSelectionModel().getSelectedItem().getType();
        final String name = tableHotKeyList.getSelectionModel().getSelectedItem().getName();
        this.controller.deleteHotKey(new HotKeyImpl(name, type));
        this.refreshTableView();
    }

    @FXML
    public final void deletSelectedRowItem(final ActionEvent event) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di volere eliminare questo tasto rapido?");

        final Optional<ButtonType> result = alert.showAndWait();

        if (!result.isPresent() || result.get() != ButtonType.OK) {
            alert.close();
        } else {
            this.deleteItem();
        }
    }

    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.root.getScene().getWindow();
        stage.close();
    }

    public final void populateTableView() {
        nome.setCellValueFactory(new PropertyValueFactory<HotKey, String>("name"));
        nome.setCellFactory(TextFieldTableCell.forTableColumn());
        nome.setOnEditCommit(new EventHandler<CellEditEvent<HotKey, String>>() {
            @Override
            public void handle(final CellEditEvent<HotKey, String> event) {
                final HotKey hotKey = event.getRowValue();
                hotKey.setName(event.getNewValue());
            }
        });

        tipo.setCellValueFactory(new PropertyValueFactory<HotKey, String>("typeRepresentation"));
        tipo.setCellFactory(TextFieldTableCell.forTableColumn());
        tipo.setOnEditCommit(new EventHandler<CellEditEvent<HotKey, String>>() {
            @Override
            public void handle(final CellEditEvent<HotKey, String> event) {
                final HotKey hotKey = event.getRowValue();
                hotKey.setType(event.getNewValue());
            }
        });

        this.tableHotKeyList.setEditable(false);
        this.tableHotKeyList.getItems().clear();
        this.tableHotKeyList.setItems(this.controller.getSortedHotKey());
    }

    private void refreshTableView() {
        this.tableHotKeyList.getItems().removeAll(tableHotKeyList.getSelectionModel().getSelectedItems());
    }

    public final void setButtonAction() {
        this.addHotKeyButton.setOnAction(event -> {
            try {
                this.addNewHotKey(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.deleteElement.setOnAction(event -> this.deletSelectedRowItem(event));
        this.goBackButton.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private static class Constants {
        private static final double TABLE_SIZE = 0.7;
        private static final double PREF_BUTTON_WIDTH = 0.15;
        private static final double PREF_BUTTON_HEIGHT = 0.05;
        private static final double DELETE_BUTTON_WIDTH = 0.5;
    }
}

