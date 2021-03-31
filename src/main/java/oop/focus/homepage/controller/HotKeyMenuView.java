package oop.focus.homepage.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;

public class HotKeyMenuView implements Initializable {

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

    private ObservableList<HotKey> hotKeyList;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
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
        this.hotKeyList = FXCollections.observableArrayList();
        this.hotKeyList.addAll(new HotKeyImpl("Shopping", HotKeyType.ACTIVITY), new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY), new HotKeyImpl("Bere", HotKeyType.COUNTER));
        tableHotKeyList.setEditable(true);
        tableHotKeyList.setItems(hotKeyList);
    }

    @FXML
    public final void addNewHotKey(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("/layouts/homepage/addNewHotKey.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    public final void deletSelectedRowItem(final ActionEvent event) {
        this.tableHotKeyList.getItems().removeAll(tableHotKeyList.getSelectionModel().getSelectedItems());
    }

    public final ObservableList<HotKey> getElem() {
        return this.hotKeyList;
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("/layouts/homepage/calendarHomePage.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show(); 
    }

    @FXML
    public final void refreshList() {
        this.hotKeyList.add(new HotKeyImpl("Shopping", HotKeyType.EVENT));
        this.hotKeyList.add(new HotKeyImpl("Shopping", HotKeyType.EVENT));
        /*this.hotKeyList.add(hotKey);*/
        tableHotKeyList.setItems(hotKeyList);
    }

}

