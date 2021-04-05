package oop.focus.finance.controller;

import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

public interface GroupController extends FinanceController {

    /**
     * Show people of group in view.
     */
    void showPeople();

    /**
     * Show group transactions in view.
     */
    void showTansactions();

    /**
     * @param person to be deleted
     */
    void deletePerson(Person person);

    /**
     * @param person whose credits I want to know
     * @return returns person's credits
     */
    int getCredit(Person person);

    /**
     * @param transaction to be deleted
     */
    void deleteTransaction(GroupTransaction transaction);
}
