package oop.focus.statistics.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.statistics.controller.FinanceInput;
import oop.focus.statistics.controller.FinanceInputBuilder;
import oop.focus.statistics.controller.FinanceInputBuilderImpl;
import oop.focus.statistics.controller.AbstractInputController;
import org.joda.time.LocalDate;

/**
 * A View that allows the user to insert finance data.
 */
public class FinanceInputViewImpl implements View {

    private static final double SPACING = 20;
    private final AbstractInputController<FinanceInput> controller;
    private final Pane root;
    private final ObservableList<Account> accountsList;
    private final MultiSelector<Account> accountsSelector;
    private final DatePicker startDate;
    private final DatePicker endDate;
    private final Button save;

    /**
     * Instantiates a new Finance input view.
     *
     * @param controller the controller used to notify the input change
     * @param accounts   the accounts
     */
    public FinanceInputViewImpl(final AbstractInputController<FinanceInput> controller,
                                final ObservableSet<Account> accounts) {
        this.controller = controller;
        this.root = this.createRoot();
        this.accountsSelector = new MultiSelectorView<>(accounts, Account::getName);
        this.accountsList = FXCollections.observableArrayList(accounts);
        this.startDate = new DatePicker();
        this.endDate = new DatePicker();
        this.save = new Button("Salva");
        this.root.getChildren().addAll(this.accountsSelector.getRoot(), this.startDate, this.endDate, this.save);
        this.setProperties();
        accounts.addListener((SetChangeListener<? super Account>) c -> {
            if (c.wasAdded()) {
                this.accountsList.add(c.getElementAdded());
            } else if (c.wasRemoved()) {
                this.accountsList.remove(c.getElementRemoved());
            }
        });
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    /**
     * Create the root container.
     *
     * @return the pane
     */
    protected Pane createRoot() {
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.setSpacing(SPACING);
        return v;
    }

    private void setProperties() {
        this.save.setOnAction(this::save);
        var today = java.time.LocalDate.now();
        var monthAgo = today.minusMonths(1);
        this.startDate.setValue(monthAgo);
        this.endDate.setValue(today);
    }

    private void save(final ActionEvent actionEvent) {
        var start = this.startDate.getValue();
        var end = this.endDate.getValue();
        if (start == null || end == null || start.isAfter(end)) {
            this.showError();
            return;
        }
        FinanceInputBuilder b = new FinanceInputBuilderImpl();
        var newStart = this.convertData(start);
        var newEnd = this.convertData(end);
        b = b.from(newStart).to(newEnd).accounts(this.accountsSelector.getSelected());
        try {
            var input = b.save();
            this.controller.updateInput(input);
        } catch (IllegalStateException e) {
            this.showError();
            e.printStackTrace();
        }
    }

    private void showError() {
        System.out.println("Input error");
    }

    private LocalDate convertData(final java.time.LocalDate value) {
        return new LocalDate(value.getYear(), value.getMonthValue(), value.getDayOfMonth());
    }
}
