package oop.focus.event.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.focus.event.controller.EventInformationController;
import oop.focus.event.controller.EventInformationControllerImpl;
import oop.focus.event.controller.EventMenuController;
import oop.focus.event.controller.FXMLPaths;
import oop.focus.homepage.model.Event;


public class EventMenuViewImpl implements EventMenuView {

    @FXML
    private AnchorPane paneEventView;

    @FXML
    private TableView<Event> tableEvent;

    @FXML
    private TableColumn<Event, String> dayOfStart, startHour, eventName;

    @FXML
    private Button seeInformation, deleteElement;

    private Node root;
    private final EventMenuController controller;

    public EventMenuViewImpl(final EventMenuController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.EVENTMENU.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.setProperties();
    }

    private void setProperties() {
        this.tableEvent.prefWidthProperty().bind(this.paneEventView.widthProperty().multiply(Constants.TABLE_WIDTH));
        this.tableEvent.prefHeightProperty().bind(this.paneEventView.heightProperty().multiply(Constants.TABLE_HEIGHT));

        this.dayOfStart.prefWidthProperty().bind(this.tableEvent.prefWidthProperty().divide(3));
        this.eventName.prefWidthProperty().bind(this.tableEvent.prefWidthProperty().divide(3));
        this.startHour.prefWidthProperty().bind(this.tableEvent.prefWidthProperty().divide(3));
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setTableView();
        this.setButtonOnAction();
    }

    private void setButtonOnAction() {
        this.deleteElement.setOnAction(event -> this.deleteItem());
        this.seeInformation.setOnAction(event -> this.viewInformation());
    }

    public final void viewInformation() {
        if (!this.tableEvent.getSelectionModel().isEmpty()) {
            final EventInformationController controllerEvent = new EventInformationControllerImpl(this.controller.getDsi(), this.tableEvent.getSelectionModel().getSelectedItem(), this.controller);
            final Stage stage = new Stage();
            stage.setScene(new Scene((Parent) controllerEvent.getView().getRoot()));
            stage.show();
        }
    }

    public final void deleteItem() {
        if (!this.tableEvent.getSelectionModel().isEmpty()) {
            final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma eliminazione");
            alert.setHeaderText("Sei sicuro di volere eliminare questo evento?");

            final Optional<ButtonType> result = alert.showAndWait();

            if (!result.isPresent() || result.get() != ButtonType.OK) {
                alert.close();
            } else {
                this.controller.remove(this.tableEvent.getSelectionModel().getSelectedItem());
                this.refreshTable();
                this.controller.getMonth().updateView();
                this.controller.getWeek().getHomePageController().getView().setDay();
                this.controller.getWeek().getView().setWeekDays();
            }
        }
    }

    public final void refreshTable() {
        this.tableEvent.getItems().clear();
        System.out.println("eNTRO");
        this.tableEvent.setItems(this.controller.getEvents());
    }

    private void setTableView() {
        eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        eventName.setCellFactory(TextFieldTableCell.forTableColumn());
        eventName.setOnEditCommit(new EventHandler<CellEditEvent<Event, String>>() {
            @Override
            public void handle(final CellEditEvent<Event, String> event) {
                final Event e = event.getRowValue();
                e.setName(event.getNewValue());
            }
        });

        dayOfStart.setCellValueFactory(new PropertyValueFactory<Event, String>("startDay"));
        dayOfStart.setCellFactory(TextFieldTableCell.forTableColumn());
        dayOfStart.setOnEditCommit(new EventHandler<CellEditEvent<Event, String>>() {
            @Override
            public void handle(final CellEditEvent<Event, String> event) {
                final Event e = event.getRowValue();
                e.setStartDay(event.getNewValue());
            }
        });

        startHour.setCellValueFactory(new PropertyValueFactory<Event, String>("startTime"));
        startHour.setCellFactory(TextFieldTableCell.forTableColumn());
        startHour.setOnEditCommit(new EventHandler<CellEditEvent<Event, String>>() {
            @Override
            public void handle(final CellEditEvent<Event, String> event) {
                final Event e = event.getRowValue();
                e.setStartTime(event.getNewValue());
            }
        });

        this.tableEvent.setItems(this.controller.getEvents());
    }

    @Override
    public final Node getRoot() {
        this.refreshTable();
        System.out.println(" xxx");
        return this.root;
    }

    private static class Constants {
        private static final double TABLE_HEIGHT = 0.8;
        private static final double TABLE_WIDTH = 0.65;
    }
}
