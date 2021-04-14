package oop.focus.calendar.week.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.persons.controller.PersonsController;
import oop.focus.calendar.persons.controller.PersonsControllerImpl;
import oop.focus.calendar.week.controller.FXMLPaths;
import oop.focus.calendar.week.controller.NewEventController;
import oop.focus.calendar.week.controller.NewEventControllerImpl;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.common.Repetition;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.view.AllertGenerator;
import oop.focus.homepage.view.ComboBoxFiller;
import oop.focus.homepage.view.GenericAddView;

public class NewEventWeekViewImpl implements GenericAddView {

    @FXML
    private Pane paneNewEvent;

    @FXML
    private Label newEvent;

    @FXML
    private Label name;

    @FXML
    private Label startDate;

    @FXML
    private Label endDate;

    @FXML
    private Label startHour;

    @FXML
    private Label endHour;

    @FXML
    private Label repetition;

    @FXML
    private Button goBack;

    @FXML
    private Button delete;

    @FXML
    private Button add;

    @FXML
    private ComboBox<String> choiceEndHour;

    @FXML
    private ComboBox<String> choiceStartHour;

    @FXML
    private ComboBox<String> choiceStartMinute;

    @FXML
    private ComboBox<String> choiceEndMinute;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private TextField textFieldName;

    @FXML
    private ComboBox<String> repetitionChoice;

    @FXML
    private Label persons;

    @FXML
    private ListView<String> listOfPersons;

    private final NewEventController controller;
    private final WeekController weekController;
    private final CalendarMonthController monthController;

    private Node root;
    private ObservableList<Person> list;

    public NewEventWeekViewImpl(final NewEventController controller, final WeekController weekController, final CalendarMonthController monthController) {
        this.controller = controller;
        this.weekController = weekController;
        this.monthController = monthController;

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ADDNEWEVENT.getPath()));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperty() {
        this.newEvent.setAlignment(Pos.CENTER);
        this.newEvent.prefHeightProperty().bind(this.paneNewEvent.heightProperty().multiply(0.1));
        this.newEvent.prefWidthProperty().bind(this.paneNewEvent.widthProperty().multiply(0.6));

        this.repetition.setAlignment(Pos.CENTER_LEFT);

        this.textFieldName.prefWidthProperty().bind(this.paneNewEvent.prefWidthProperty().multiply(0.3));
        this.textFieldName.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(0.05));
/*
        this.choiceEndHour.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(0.05));
        this.choiceEndHour.prefWidthProperty().bind(this.paneNewEvent.prefWidthProperty().multiply(0.15));
        this.choiceEndMinute.prefWidthProperty().bind(this.paneNewEvent.prefWidthProperty().multiply(0.15));

        this.choiceEndMinute.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(0.05));
        this.choiceStartHour.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(0.05));
        this.choiceStartMinute.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(0.05));
        this.repetitionChoice.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(0.05));
        this.textFieldName.prefHeightProperty().bind(this.paneNewEvent.prefHeightProperty().multiply(0.05));
        this.textFieldName.prefWidthProperty().bind(this.paneNewEvent.prefWidthProperty().multiply(0.6));*/
    }


    public final void initialize(final URL location, final ResourceBundle resources) {
        this.setProperty();
        this.fillComboBoxes();
        this.fillTheList();
        this.setButtonAction();
    }

    public final void setButtonAction() {
        this.add.setOnAction(event -> {
            try {
                save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.delete.setOnAction(event -> this.delete(event));

        this.goBack.setOnAction(event -> {
            try {
                this.goBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public final void save(final ActionEvent event) throws IOException {
        if (!this.textFieldName.getText().isEmpty() && !this.choiceStartHour.getSelectionModel().isEmpty()
                && !this.choiceStartMinute.getSelectionModel().isEmpty() && !this.choiceEndHour.getSelectionModel().isEmpty()
                && !this.choiceEndMinute.getSelectionModel().isEmpty() && !String.valueOf(this.datePickerStart.getValue()).isEmpty()
                && !String.valueOf(this.datePickerEnd.getValue()).isEmpty() && !this.repetitionChoice.getSelectionModel().isEmpty()) {
            this.saveEvent(event);

            this.weekController.getView().setWeekDays();
            this.monthController.updateView();

            this.goBack(event);
        } else {
            final AllertGenerator allert = new AllertGenerator();
            allert.createWarningAllert(1);
        }
    }

    public final void delete(final ActionEvent event) {
        this.textFieldName.setText(" ");

        this.choiceStartHour.getSelectionModel().clearSelection();
        this.choiceStartMinute.getSelectionModel().clearSelection();

        this.choiceEndHour.getSelectionModel().clearSelection();
        this.choiceEndMinute.getSelectionModel().clearSelection();

        this.datePickerStart.setValue(null);
        this.datePickerEnd.setValue(null);

        this.repetitionChoice.getSelectionModel().clearSelection();
    }

    public final void goBack(final ActionEvent event) throws IOException {
        final Stage stage = (Stage) this.paneNewEvent.getScene().getWindow();
        stage.close();
    }

    public final void fillTheList() {
        final PersonsController persons = new PersonsControllerImpl(new DataSourceImpl());
        final ObservableList<String> listOfString = FXCollections.observableArrayList();

        this.list = persons.getPersons();
        list.forEach(p -> listOfString.add(p.toString()));

        this.listOfPersons.setItems(listOfString);
        this.listOfPersons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public final void fillComboBoxes() {
        final ComboBoxFiller fullComboBoxes = new ComboBoxFiller();
	
        this.choiceStartHour.setItems(fullComboBoxes.getHourAndMinute(Constants.HOUR_PER_DAYS));
        this.choiceEndHour.setItems(fullComboBoxes.getHourAndMinute(Constants.HOUR_PER_DAYS));
        this.choiceStartMinute.setItems(fullComboBoxes.getHourAndMinute(Constants.MINUTE_PER_DAY));
        this.choiceEndMinute.setItems(fullComboBoxes.getHourAndMinute(Constants.MINUTE_PER_DAY));
        this.repetitionChoice.setItems(fullComboBoxes.getRepetition());
    }

    public final Node getRoot() {
       return this.root;
    }

    public final void saveEvent(final ActionEvent event) throws IOException {
        final LocalTime startTime = new LocalTime(Integer.valueOf(this.choiceStartHour.getSelectionModel().getSelectedItem()), Integer.valueOf(this.choiceStartMinute.getSelectionModel().getSelectedItem()));
        final LocalTime endTime = new LocalTime(Integer.valueOf(this.choiceEndHour.getSelectionModel().getSelectedItem()), Integer.valueOf(this.choiceEndMinute.getSelectionModel().getSelectedItem()));

        final java.time.LocalDate start = this.datePickerStart.getValue();
        final LocalDate startDate = new LocalDate(start.getYear(), start.getMonthValue(), start.getDayOfMonth());

        final java.time.LocalDate end = this.datePickerEnd.getValue();
        final LocalDate endDate = new LocalDate(end.getYear(), end.getMonthValue(), end.getDayOfMonth());

        final ObservableList<Integer> indices = this.listOfPersons.getSelectionModel().getSelectedIndices();
        final List<Person> finalList = new ArrayList<>();
        indices.forEach(i -> {
            finalList.add(this.list.get(i));
        });

        final Event eventToSave = new EventImpl(this.textFieldName.getText(), startDate.toLocalDateTime(startTime), endDate.toLocalDateTime(endTime), Repetition.getRepetition(this.repetitionChoice.getSelectionModel().getSelectedItem()), finalList);

        if (this.controller.itIsValid(eventToSave)) {
            this.controller.addNewEvent(eventToSave);
            this.goBack(event);
        } else {
            final AllertGenerator allert = new AllertGenerator();
            allert.createWarningAllert(2);
        }
    }

    private static class Constants {
        public static final int HOUR_PER_DAYS = 24;
        public static final int MINUTE_PER_DAY = 60;
    }
}
