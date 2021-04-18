package oop.focus.finance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.windows.AddPersonViewImpl;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.homepage.model.Person;

import java.util.stream.Collectors;

/**
 * Immutable implementation of an add person controller.
 */
public class AddPersonControllerImpl implements AddPersonController {

    private final GenericWindow<AddPersonController> view;
    private final FinanceManager manager;

    public AddPersonControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new AddPersonViewImpl(this);
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
    public final void addPerson(final Person person) {
        this.manager.getGroupManager().addPerson(person);
    }

    @Override
    public final ObservableList<Person> getPersonsToAdd() {
        return this.manager.getGroupManager().getPersons().stream()
                .filter(p -> !this.manager.getGroupManager().getGroup().contains(p))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public final DataSource getDb() {
        return this.manager.getDb();
    }
}
