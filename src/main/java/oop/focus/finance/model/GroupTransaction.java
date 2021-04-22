package oop.focus.finance.model;

import oop.focus.calendar.persons.model.Person;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Interface that models a group transaction.
 * It stores who paid, who paid for and how much they spent.
 */
public interface GroupTransaction {

    /**
     * @return transaction's description
     */
    String getDescription();

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

    /**
     * @return transaction's date
     */
    LocalDateTime getDate();
}
