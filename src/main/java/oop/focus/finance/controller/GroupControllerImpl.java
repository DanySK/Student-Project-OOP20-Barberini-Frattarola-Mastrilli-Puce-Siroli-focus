package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.bases.GroupView;
import oop.focus.finance.view.bases.GroupViewImpl;
import oop.focus.calendar.persons.model.Person;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Immutable implementation of a group transactions group controller.
 */
public class GroupControllerImpl implements GroupController {

    private final GroupView view;
    private final FinanceManager manager;

    private final ObservableSet<Person> group;
    private final ObservableSet<GroupTransaction> groupTransactions;

    public GroupControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new GroupViewImpl(this);
        this.group = this.manager.getGroupManager().getGroup();
        this.groupTransactions = this.manager.getGroupManager().getTransactions();
        this.showPeople();
        this.addListeners();
    }

    private void addListeners() {
        this.group.addListener((SetChangeListener<Person>) change -> this.showPeople());
        this.groupTransactions.addListener((SetChangeListener<GroupTransaction>) change -> this.showTansactions());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void deletePerson(final Person person) {
        this.manager.getGroupManager().removePerson(person);
    }

    @Override
    public final void deleteTransaction(final GroupTransaction transaction) {
        this.manager.getGroupManager().removeTransaction(transaction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void showTansactions() {
        this.view.showTransactions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void showPeople() {
        this.view.showPeople();
    }

    @Override
    public final double getCredit(final Person person) {
        return (double) this.manager.getGroupManager().getCredit(person) / 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void reset() {
        this.manager.getGroupManager().reset();
    }

    @Override
    public final List<GroupTransaction> getSortedGroupTransactions() {
        return this.groupTransactions.stream()
                .sorted(Comparator.comparing(GroupTransaction::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public final List<Person> getSortedGroup() {
        return this.group.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }
}
