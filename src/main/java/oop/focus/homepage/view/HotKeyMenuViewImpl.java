package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.homepage.controller.FXMLPaths;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class HotKeyMenuViewImpl implements  HotKeyMenuView {

    @FXML
    private Pane paneHotKeyView;

    @FXML
    private VBox vboxHotKeyList;

    @FXML
    private Button addHotKeyButton, deleteElement, goBackButton;

    @FXML
    private TableView<HotKey> tableHotKeyList;

    @FXML
    private TableColumn<HotKey, String> nome, tipo;

    private final HotKeyControllerImpl controller;
    private Node root;

    public HotKeyMenuViewImpl(final HotKeyControllerImpl controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.HOTKEYMENU.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setButtonAction();

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

        this.populate();
    }

    public final void populate() {
    	this.populateTableView();
    }

    private void populateTableView() {
		this.tableHotKeyList.setEditable(false);
		this.tableHotKeyList.getItems().clear();
		this.tableHotKeyList.setItems(this.controller.getHotKey());
	}

	@FXML
    public final void addNewHotKey(final ActionEvent event) throws IOException {
        final GenericAddView newHotKey = new NewHotKeyViewImpl(this.controller.getController());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newHotKey.getRoot()));
        stage.show();
    }

    @FXML
    public final void deletSelectedRowItem(final ActionEvent event) {
        final HotKeyType type = tableHotKeyList.getSelectionModel().getSelectedItem().getType();
        final String name = tableHotKeyList.getSelectionModel().getSelectedItem().getName();
        this.controller.deleteHotKey(new HotKeyImpl(name, type));
        this.refreshTableView();
    }

    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final HomePageBaseView base = new HomePageBaseViewImpl(this.controller.getController());
        this.paneHotKeyView.getChildren().clear();
        this.paneHotKeyView.getChildren().add(base.getRoot());
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
}

