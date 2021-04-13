package oop.focus.finance.controller;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.bases.GroupViewImpl;
import oop.focus.homepage.model.Person;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupControllerImpl implements GroupController {

    private final GroupViewImpl view;
    private final FinanceManager manager;

    private final ObservableSet<Person> group;
    private final ObservableSet<GroupTransaction> groupTransactions;

    public GroupControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new GroupViewImpl(this);
        this.showPeople();
        this.group = this.manager.getGroupManager().getGroup();
        this.groupTransactions = this.manager.getGroupManager().getTransactions();
        this.addListeners();
    }

    private void addListeners() {
        this.group.addListener((SetChangeListener<Person>) change -> this.showPeople());
        this.groupTransactions.addListener((SetChangeListener<GroupTransaction>) change -> this.showTansactions());
    }

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

    @Override
    public final void showTansactions() {
        this.view.showTransactions(this.manager.getGroupManager().getTransactions());
    }

    @Override
    public final void showPeople() {
        this.view.showPeople(this.manager.getGroupManager().getGroup());
    }

    @Override
    public final double getCredit(final Person person) {
        return (double) this.manager.getGroupManager().getCredit(person) / 100;
    }

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
        return this.manager.getGroupManager().getGroup().stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }
}
