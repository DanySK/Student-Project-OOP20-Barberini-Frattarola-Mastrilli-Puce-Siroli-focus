package oop.focus.finance.controller;

import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.finance.view.windows.PersonDetailsWindowImpl;
import oop.focus.homepage.model.Person;

public class PersonDetailsControllerImpl implements DetailsController<Person> {

    private final GenericWindow<DetailsController<Person>> view;
    private final Person person;
    private final FinanceManager manager;

    public PersonDetailsControllerImpl(final FinanceManager manager, final Person person) {
        this.manager = manager;
        this.person = person;
        this.view = new PersonDetailsWindowImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final Person getElement() {
        return this.person;
    }

    @Override
    public final void deleteElement(final Person person) {
        this.manager.getGroupManager().removePerson(person);
    }
}
