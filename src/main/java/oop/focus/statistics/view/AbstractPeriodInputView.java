package oop.focus.statistics.view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import oop.focus.common.View;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * A View that allows the user to insert input data of type X.
 * This implementation uses two {@link DatePicker} to allow the user
 * to define a period of time for which to view the data.
 *
 * @param <X> the input type
 */
public abstract class AbstractPeriodInputView<X> implements View {

    private static final double SPACING_RATIO = 0.02;
    private static final double X_RATIO = 0.02;
    private static final double Y_RATIO = 0.02;
    private static final String START_TEXT = "Data iniziale";
    private static final String END_TEXT = "Data finale";
    private static final String SAVE_TEXT = "Salva";
    private static final String ERROR_TITLE = "Errore";
    private static final String ERROR_HEADER = "Errore inserimento dati";
    private static final String ERROR_MESSAGE = "I dati inseriti non sono corretti";
    private static final String STYLE_CLASS = "input-container";
    private final Pane root;
    private final DatePicker startDate;
    private final DatePicker endDate;
    private final Button save;

    /**
     * Instantiates a new Abstract input view.
     */
    public AbstractPeriodInputView() {
        this.root = this.createRoot();
        final View selector = this.createSelector();
        this.startDate = new DatePicker();
        this.endDate = new DatePicker();
        final var f = new ViewFactoryImpl();
        final var s = f.createVertical(List.of(new Label(START_TEXT), this.startDate));
        final var e = f.createVertical(List.of(new Label(END_TEXT), this.endDate));
        this.save = new Button(SAVE_TEXT);
        this.root.getChildren().addAll(selector.getRoot(), s.getRoot(), e.getRoot(), this.save);
        this.root.getStyleClass().add(STYLE_CLASS);
        this.setProperties();
        this.checkAndSave(null);
    }

    private void checkAndSave(final ActionEvent event) {
        if (this.inputNotValid()) {
            this.showError();
        } else {
            this.save();
        }
    }

    /**
     * Creates a view component that can allow the user to enter other data
     * in addition to time period.
     *
     * @return the view.
     */
    protected abstract View createSelector();

    /**
     * The action to be performed when the user insert the data.
     */
    protected abstract void save();

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.root;
    }

    /**
     * Create the root container that contains all the graphics elements.
     *
     * @return the pane
     */
    protected Pane createRoot() {
        return ViewFactory.verticalWithPadding(SPACING_RATIO, X_RATIO, Y_RATIO);
    }

    /**
     * Displays an input error.
     */
    protected final void showError() {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setHeaderText(ERROR_HEADER);
        alert.setContentText(ERROR_MESSAGE);
        alert.showAndWait();
    }

    /**
     * Gets the start date as {@link LocalDate} entered by the user.
     *
     * @return the start date, null if the date is not valid.
     */
    protected final LocalDate getStartDate() {
        try {
            return this.convertData(this.startDate.getValue());
        } catch (final IllegalFieldValueException e) {
            return null;
        }
    }

    /**
     * Gets the end date as {@link LocalDate} entered by the user.
     *
     * @return the end date, null if the date is not valid.
     */
    protected final LocalDate getEndDate() {
        try {
            return this.convertData(this.endDate.getValue());
        } catch (final IllegalFieldValueException e) {
            return null;
        }
    }

    private boolean inputNotValid() {
        final var start = this.getStartDate();
        final var end = this.getEndDate();
        if (start == null || end == null || start.isAfter(end)) {
            this.showError();
            return true;
        }
        return false;
    }

    private void setProperties() {
        this.save.setOnAction(this::checkAndSave);
        final var today = java.time.LocalDate.now();
        final var monthAgo = today.minusMonths(1);
        this.startDate.setValue(monthAgo);
        this.endDate.setValue(today);
    }

    private LocalDate convertData(final java.time.LocalDate value) throws IllegalFieldValueException {
        return new LocalDate(value.getYear(), value.getMonthValue(), value.getDayOfMonth());
    }
}
