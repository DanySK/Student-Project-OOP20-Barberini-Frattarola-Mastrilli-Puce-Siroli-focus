package oop.focus.calendarhomepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import oop.focus.event.model.Event;
import oop.focus.calendar.persons.model.Person;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.common.Repetition;
import oop.focus.calendarhomepage.controller.FXMLPaths;
import oop.focus.calendarhomepage.controller.HomePageController;
import oop.focus.event.model.EventImpl;
import oop.focus.event.model.TimeProperty;
import oop.focus.event.model.TimePropertyImpl;

public class NewEventViewImpl implements NewEventView {

    @FXML
    private Pane paneNewEvent;

    @FXML
    private Label newEvent, newEventName, startHour, endHour, repetition, persons;

    @FXML
    private ComboBox<String> startHourChoice, endHourChoice, startMinuteChoice, endMinuteChoice;

    @FXML
    private ComboBox<Repetition> repetitionChoice;

    @FXML
    private Button back, saveSelection, deleteSelection;

    @FXML
    private ListView<String> listOfPersons;

    private ObservableList<Person> list;
    private Node root;
    private AlertFactory alert;
    private final HomePageController controller;

    public NewEventViewImpl(final HomePageController controller) {
        this.controller = controller;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWEVENT.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.alert = new AlertFactoryImpl();
        this.list = FXCollections.observableArrayList();
        this.newEventName.setText(this.controller.getText());

        this.setProperty();

        this.setButtonOnAction();
        this.fillTheList();
        this.fillTheComboBox();
    }

    @FXML
    public final void delete(final ActionEvent event) {
        this.repetitionChoice.getSelectionModel().clearSelection();
        this.startHourChoice.getSelectionModel().clearSelection();
        this.endHourChoice.getSelectionModel().clearSelection();
        this.startMinuteChoice.getSelectionModel().clearSelection();
        this.endMinuteChoice.getSelectionModel().clearSelection();
    }

    private void fillTheComboBox() {
        final ComboBoxFiller filler = new ComboBoxFiller();
        this.endHourChoice.setItems(filler.getHourAndMinute(Constants.HOUR_PER_DAY));
        this.startHourChoice.setItems(filler.getHourAndMinute(Constants.HOUR_PER_DAY));
        this.startMinuteChoice.setItems(filler.getHourAndMinute(Constants.MINUTE_PER_HOUR));
        this.endMinuteChoice.setItems(filler.getHourAndMinute(Constants.MINUTE_PER_HOUR));

        this.repetitionChoice.setItems(this.controller.getRep());
        this.repetitionChoice.setConverter(new StringConverter<>() {
            @Override
            public String toString(final Repetition repetition) {
                return repetition == null ? "" : repetition.getName();
            }

            @Override
            public Repetition fromString(final String string) {
                return null;
            }
        });
    }

    public final void fillTheList() {
        final PersonsController persons = new PersonsControllerImpl(this.controller.getDsi());
        final ObservableList<String> listOfString = FXCollections.observableArrayList();

        final List<Person> temp = new ArrayList<>(persons.getPersons());
        this.list.addAll(temp);
        this.list.forEach(p -> listOfString.add(p.toString()));

        this.listOfPersons.setItems(listOfString);
        this.listOfPersons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void goBack(final ActionEvent event) {
        final Stage stage = (Stage) this.paneNewEvent.getScene().getWindow();
        stage.close();
    }

    private void setProperty() {
        this.newEvent.setAlignment(Pos.CENTER);
        this.newEvent.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(Constants.PREF_HEIGHT));
        this.newEvent.prefWidthProperty().bind(this.paneNewEvent.prefWidthProperty().multiply(Constants.PREF_WIDTH));

        this.newEventName.setAlignment(Pos.CENTER);
        this.newEventName.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(Constants.PREF_HEIGHT));
        this.newEventName.prefWidthProperty().bind(this.paneNewEvent.prefWidthProperty().multiply(Constants.PREF_WIDTH));

    }

    @FXML
    public final void save(final ActionEvent event) {
        if (!this.startHourChoice.getSelectionModel().isEmpty() && !this.endHourChoice.getSelectionModel().isEmpty()
                && !this.startMinuteChoice.getSelectionModel().isEmpty() && !this.endMinuteChoice.getSelectionModel().isEmpty()
                && !this.repetitionChoice.getSelectionModel().isEmpty()) {
            this.saveEvent(event);
        } else {
            this.alert.createIncompleteFieldAlert();
        }
    }

    private void saveEvent(final ActionEvent event) {
        final LocalDate date = LocalDate.now();
        final LocalTime start = new LocalTime(Integer.parseInt(this.startHourChoice.getSelectionModel().getSelectedItem()),
                Integer.parseInt(this.startMinuteChoice.getSelectionModel().getSelectedItem()));
        final LocalTime end = new LocalTime(Integer.parseInt(this.endHourChoice.getSelectionModel().getSelectedItem()),
                Integer.parseInt(this.endMinuteChoice.getSelectionModel().getSelectedItem()));

        final ObservableList<Integer> indices = this.listOfPersons.getSelectionModel().getSelectedIndices();
        final List<Person> finalList = new ArrayList<>();
        indices.forEach(i -> finalList.add(this.list.get(i)));

        final Event eventToSave = new EventImpl(this.newEventName.getText(), date.toLocalDateTime(start), date.toLocalDateTime(end), this.repetitionChoice.getSelectionModel().getSelectedItem(), finalList);

        final TimeProperty time = new TimePropertyImpl();

        if (this.validateEvent(eventToSave) && time.getMinEventTime(eventToSave)) {
            try {
                this.controller.saveEvent(eventToSave);
                this.goBack(event);
            } catch (final IllegalStateException e) {
                this.alert.createEventWarning();
            } 
        } else {
            this.alert.createHourOrDateError();
        }
    }

    private boolean validateEvent(final Event eventToSave) {
        return eventToSave.getStartDate().equals(eventToSave.getEndDate()) && eventToSave.getStartHour().isBefore(eventToSave.getEndHour())
            || eventToSave.getStartDate().isBefore(eventToSave.getEndDate());
    }

    public final void setButtonOnAction() {
        this.back.setOnAction(this::goBack);

        this.saveSelection.setOnAction(this::save);

        this.deleteSelection.setOnAction(this::delete);
    }

    public final void setText(final String eventName) {
        this.newEventName.setText(eventName);
    }

    private static class Constants {
         public static final int HOUR_PER_DAY = 24;
         public static final int MINUTE_PER_HOUR = 60;
         private static final double PREF_WIDTH = 0.6;
         private static final double PREF_HEIGHT = 0.1;
    }

}

