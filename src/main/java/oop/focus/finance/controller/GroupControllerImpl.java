package oop.focus.finance.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.model.GroupTransactionImpl;
import oop.focus.finance.view.bases.GroupViewImpl;
import oop.focus.homepage.model.Person;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        this.group = this.getGroup();
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
    public final void showPeople() {
        this.view.showPeople(this.manager.getGroupManager().getGroup());
    }

    @Override
    public final void showTansactions() {
        this.view.showTransactions(this.manager.getGroupManager().getTransactions());
    }

    @Override
    public final void newGroupTransaction(final String description, final Person madeBy, final Set<Person> forSet,
                                          final double amount) {
        this.manager.getGroupManager().addTransaction(new GroupTransactionImpl(description, madeBy,
                new ArrayList<>(forSet), (int) (amount * 100), LocalDate.now()));
    }

    @Override
    public final void addPerson(final Person person) {
        this.manager.getGroupManager().addPerson(person);
    }

    @Override
    public final void deletePerson(final Person person) {
        this.manager.getGroupManager().removePerson(person);
    }

    @Override
    public final int getCredit(final Person person) {
        return this.manager.getGroupManager().getCredit(person);
    }

    @Override
    public final void deleteTransaction(final GroupTransaction transaction) {
        this.manager.getGroupManager().removeTransaction(transaction);
    }

    @Override
    public final void resolve() {
        this.manager.getGroupManager().resolve().forEach(this.manager.getGroupManager()::addTransaction);
    }

    @Override
    public final void reset() {
        this.manager.getGroupManager().reset();
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }

    @Override
    public final ObservableList<Person> getPersonsToAdd() {
        return new ObservableListWrapper<>(this.manager.getGroupManager().getPersons().stream()
                .filter(p -> !this.getGroup().contains(p))
                .collect(Collectors.toList()));
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.manager.getGroupManager().getGroup();
    }

    @Override
    public final List<GroupTransaction> getResolvingTransactions() {
        return this.manager.getGroupManager().resolve();
    }
}
