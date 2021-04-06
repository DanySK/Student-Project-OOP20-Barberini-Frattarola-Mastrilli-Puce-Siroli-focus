package oop.focus.finance.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.model.GroupTransactionImpl;
import oop.focus.finance.view.GroupViewImpl;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public final ObservableList<String> getRelationships() {
        return new ObservableListWrapper<>(new ArrayList<>(this.manager.getRelationships()));
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.manager.getGroup();
    }

    @Override
    public final void newPerson(final String name, final String relationship) {
        this.manager.addPerson(new PersonImpl(name, relationship));
    }

    @Override
    public final void newGroupTransaction(final String description, final Person madeBy, final Set<Person> forSet, final int amount) {
        this.manager.addTransaction(new GroupTransactionImpl(description, madeBy,
                new ArrayList<>(forSet), amount * 100, LocalDate.now()));
    }

    @Override
    public final List<GroupTransaction> getResolvingTransactions() {
        return this.manager.resolve();
    }

    @Override
    public final void resolve() {
        this.manager.resolve().forEach(this.manager::addTransaction);
    }
}
