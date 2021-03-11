package oop.focus.finance;

import oop.focus.homepage.model.Person;

import java.util.List;

/**
 * Interface that models a group transaction.
 * It stores who paid, who paid for and how much they spent.
 */
public interface GroupTransaction {

    /**
     * @return the person who paid
     */
    Person getMadeBy();

    /**
     * @return the list of people they paid for
     */
    List<Person> getForList();

    /**
     * @return the amount spent
     */
    int getAmount();
}
