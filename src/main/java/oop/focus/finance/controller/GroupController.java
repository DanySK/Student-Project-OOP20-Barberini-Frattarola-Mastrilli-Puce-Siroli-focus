package oop.focus.finance.controller;

import oop.focus.common.Controller;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

import java.util.List;

public interface GroupController extends Controller {

    /**
     * Show group transactions in view.
     */
    void showTansactions();

    /**
     * Show people of group in view.
     */
    void showPeople();

    /**
     * @param person whose credits I want to know
     * @return returns person's credits in euro
     */
    double getCredit(Person person);

    /**
     * If there are no more debts, delete the group and all transactions.
     */
    void reset();

    /**
     * @return the list of all group transactions saved in the database, sorted by time
     */
    List<GroupTransaction> getSortedGroupTransactions();

    /**
     * @return the list of all persons added to the froup saved in the database, sorted by name
     */
    List<Person> getSortedGroup();

    /**
     * @return finance manager
     */
    FinanceManager getManager();
}
