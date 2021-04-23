package oop.focus.finance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransactionImpl;
import oop.focus.finance.view.windows.FinanceWindowImpl;
import oop.focus.finance.view.windows.NewGroupTransactionViewImplImpl;
import oop.focus.calendar.persons.model.Person;
import org.joda.time.LocalDateTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

/**
 * Immutable implementation of a new group transaction controller.
 */
public class NewGroupTransactionControllerImpl implements NewGroupTransactionController {

    private final FinanceWindowImpl view;
    private final FinanceManager manager;

    private final ObservableSet<Person> group;

    public NewGroupTransactionControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewGroupTransactionViewImplImpl(this);
        this.group = this.manager.getGroupManager().getGroup();
        this.addListeners();
    }

    private void addListeners() {
        this.group.addListener((SetChangeListener<Person>) change -> this.view.populate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void newGroupTransaction(final String description, final Person madeBy, final Set<Person> forSet,
                                          final double amount, final LocalDate date, final int hours, final int minutes) {
        this.manager.getGroupManager().addTransaction(new GroupTransactionImpl(description, madeBy,
                new ArrayList<>(forSet), (int) (amount * 100), new LocalDateTime(date.getYear(), date.getMonthValue(),
                date.getDayOfMonth(), hours, minutes, 0)));
    }

    @Override
    public final ObservableList<Person> getGroupList() {
        final ObservableList<Person> list = FXCollections.observableArrayList();
        Linker.setToList(this.getGroup(), list);
        return list;
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.manager.getGroupManager().getGroup();
    }
}
