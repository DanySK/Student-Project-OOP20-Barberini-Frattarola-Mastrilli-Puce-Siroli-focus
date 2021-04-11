package oop.focus.homepage.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.week.controller.PersonsController;
import oop.focus.week.controller.PersonsControllerImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.Person;
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
import oop.focus.common.Repetition;
import oop.focus.homepage.controller.FXMLPaths;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.EventImpl;

public class NewEventViewImpl implements GenericAddView {

    @FXML
    private Pane paneNewEvent;

    @FXML
    private Label newEvent, newEventName, startHour, endHour, repetition, persons;

    @FXML
    private ComboBox<String> startHourChoice, repetitionChoice, endHourChoice, startMinuteChoice, endMinuteChoice;

    @FXML
    private Button back, saveSelection, deleteSelection;

    @FXML
    private ListView<String> listOfPersons;

    private ObservableList<Person> list;
    private Node root;
    private final HomePageController controller;

    public NewEventViewImpl(final HomePageController controller) {
        this.controller = controller;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWEVENT.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final ComboBoxFiller filler = new ComboBoxFiller();

        this.setButtonOnAction();
        this.fillTheList();

        this.endHourChoice.setItems(filler.getHourAndMinute(Constants.HOUR_PER_DAY));
        this.startHourChoice.setItems(filler.getHourAndMinute(Constants.HOUR_PER_DAY));
        this.startMinuteChoice.setItems(filler.getHourAndMinute(Constants.MINUTE_PER_HOUR));
        this.endMinuteChoice.setItems(filler.getHourAndMinute(Constants.MINUTE_PER_HOUR));
        this.repetitionChoice.setItems(filler.getRepetition());

    }

    public final void setButtonOnAction() {
        this.back.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.saveSelection.setOnAction(event -> {
            try {
                this.save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.deleteSelection.setOnAction(event -> this.delete(event));
    }

    public final void fillTheList() {
        final PersonsController persons = new PersonsControllerImpl(new DataSourceImpl());
        final ObservableList<String> listOfString = FXCollections.observableArrayList();

        this.list = persons.getPersons();
        list.forEach(p -> listOfString.add(p.toString()));

        this.listOfPersons.setItems(listOfString);
        this.listOfPersons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.paneNewEvent.getScene().getWindow();
        stage.close();
    }

    @FXML
    public final void delete(final ActionEvent event) {
        this.repetitionChoice.getSelectionModel().clearSelection();
        this.startHourChoice.getSelectionModel().clearSelection();
        this.endHourChoice.getSelectionModel().clearSelection();
        this.startMinuteChoice.getSelectionModel().clearSelection();
        this.endMinuteChoice.getSelectionModel().clearSelection();
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    @FXML
    public final void save(final ActionEvent event) throws IOException {
        if (!this.startHourChoice.getSelectionModel().isEmpty() && !this.endHourChoice.getSelectionModel().isEmpty()
                && !this.startMinuteChoice.getSelectionModel().isEmpty() && !this.endMinuteChoice.getSelectionModel().isEmpty()
                && !this.repetitionChoice.getSelectionModel().isEmpty()) {
            this.saveEvent(event);
            this.goBack(event);
        } else {
            final AllertGenerator allert = new AllertGenerator();
            allert.createAllert(1);
        }
    }

    private void saveEvent(final ActionEvent event) throws IOException {
        final LocalDate date = LocalDate.now();
        final LocalTime start = new LocalTime(Integer.valueOf(this.startHourChoice.getSelectionModel().getSelectedItem()), Integer.valueOf(this.startMinuteChoice.getSelectionModel().getSelectedItem()));
        final LocalTime end = new LocalTime(Integer.valueOf(this.endHourChoice.getSelectionModel().getSelectedItem()), Integer.valueOf(this.endMinuteChoice.getSelectionModel().getSelectedItem()));

        final ObservableList<Integer> indices = this.listOfPersons.getSelectionModel().getSelectedIndices();
        final List<Person> finalList = new ArrayList<>();
        indices.forEach(i -> {
            finalList.add(this.list.get(i));
        });

        final Event eventToSave = new EventImpl(this.newEventName.getText(), date.toLocalDateTime(start), date.toLocalDateTime(end), Repetition.getRepetition(this.repetitionChoice.getSelectionModel().getSelectedItem()), finalList);

        if (this.controller.canBeAdded(eventToSave)) {
            this.controller.saveEvent(eventToSave);
            this.goBack(event);
        } else {
            final AllertGenerator allert = new AllertGenerator();
            allert.createAllert(2);
        }
    }

    public final void setText(final String eventName) {
        newEventName.setText(eventName);
    }

    private static class Constants {
         public static final int HOUR_PER_DAY = 24;
         public static final int MINUTE_PER_HOUR = 60;
    }

}

