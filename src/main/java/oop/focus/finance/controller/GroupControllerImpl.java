package oop.focus.finance.controller;

import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.GroupViewImpl;
import oop.focus.finance.view.View;
import oop.focus.homepage.model.Person;

public class GroupControllerImpl implements GroupController {

    private final GroupViewImpl view;
    private final FinanceManager manager;

    public GroupControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new GroupViewImpl(this);
        this.showPeople();
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
}
