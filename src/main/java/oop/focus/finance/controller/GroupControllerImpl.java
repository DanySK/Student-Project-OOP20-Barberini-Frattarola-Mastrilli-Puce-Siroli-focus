package oop.focus.finance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.model.GroupTransactionImpl;
import oop.focus.finance.view.bases.GroupViewImpl;
import oop.focus.homepage.model.Person;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
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
    public final void newGroupTransaction(final String description, final Person madeBy, final Set<Person> forSet, final String amount,
                                          final java.time.LocalDate date, final String hours, final String minutes) {
        LocalDateTime d = new LocalDateTime(date == null ? LocalDate.now().getYear() : date.getYear(),
                date == null ? LocalDate.now().getMonthOfYear() : date.getMonthValue(),
                date == null ? LocalDate.now().getDayOfMonth() : date.getDayOfMonth(),
                hours.isEmpty() ? LocalDateTime.now().getHourOfDay() : Integer.parseInt(hours),
                minutes.isEmpty() ? LocalDateTime.now().getMinuteOfHour() : Integer.parseInt(minutes), 0);
        this.manager.getGroupManager().addTransaction(new GroupTransactionImpl(description, madeBy,
                new ArrayList<>(forSet), (int) (Double.parseDouble(amount) * 100), d));
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
    public final String getCredit(final Person person) {
        return this.format(this.manager.getGroupManager().getCredit(person));
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
    public final List<GroupTransaction> getSortedGroupTransactions() {
        return this.groupTransactions.stream()
                .sorted(Comparator.comparing(GroupTransaction::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public final List<Person> getSortedGroup() {
        return this.getGroup().stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }

    @Override
    public final List<GroupTransaction> getResolvingTransactions() {
        return this.manager.getGroupManager().resolve();
    }

    @Override
    public final ObservableList<Person> getPersonsToAdd() {
        return this.manager.getGroupManager().getPersons().stream()
                .filter(p -> !this.getGroup().contains(p))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.manager.getGroupManager().getGroup();
    }

    @Override
    public final ObservableSet<Person> getPersons() {
        return this.manager.getGroupManager().getPersons();
    }

    @Override
    public final FinanceManager getManager() {
        return this.manager;
    }

    @Override
    public final ObservableList<Person> getGroupList() {
        ObservableList<Person> list = FXCollections.observableArrayList();
        Linker.setToList(this.getGroup(), list);
        return list;
    }

    private String format(final int amount) {
        final DecimalFormat f = new DecimalFormat("#0.00");
        return f.format((double) amount / 100);
    }
}
