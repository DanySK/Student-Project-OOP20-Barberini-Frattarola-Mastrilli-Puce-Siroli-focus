package oop.focus.finance.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupManager;
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
    private final GroupManager manager;

    public GroupControllerImpl(final FinanceManager manager) {
        this.manager = manager.getGroupManager();
        this.view = new GroupViewImpl(this);
        this.showPeople();
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void showPeople() {
        this.view.showPeople(this.manager.getGroup());
    }

    @Override
    public final void showTansactions() {
        this.view.showTransactions(this.manager.getTransactions());
    }

    @Override
    public final void newGroupTransaction(final String description, final Person madeBy, final Set<Person> forSet,
                                          final double amount) {
        this.manager.addTransaction(new GroupTransactionImpl(description, madeBy,
                new ArrayList<>(forSet), (int) (amount * 100), LocalDate.now()));
    }

    @Override
    public final void addPerson(final Person person) {
        this.manager.addPerson(person);
    }

    @Override
    public final void deletePerson(final Person person) {
        this.manager.removePerson(person);
    }

    @Override
    public final int getCredit(final Person person) {
        return this.manager.getCredit(person);
    }

    @Override
    public final void deleteTransaction(final GroupTransaction transaction) {
        this.manager.removeTransaction(transaction);
    }

    @Override
    public final void resolve() {
        this.manager.resolve().forEach(this.manager::addTransaction);
    }

    @Override
    public final void reset() {
        this.manager.reset();
    }

    @Override
    public final ObservableList<Person> getPersonsToAdd() {
        return new ObservableListWrapper<>(this.manager.getPersons().stream()
                .filter(p -> !this.getGroup().contains(p))
                .collect(Collectors.toList()));
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.manager.getGroup();
    }

    @Override
    public final List<GroupTransaction> getResolvingTransactions() {
        return this.manager.resolve();
    }
}
