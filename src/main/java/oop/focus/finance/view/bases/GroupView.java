package oop.focus.finance.view.bases;

import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

public interface GroupView extends View {

    /**
     * Show people in the groupMovementsScroll.
     *
     * @param group to be showed
     */
    void showPeople(ObservableSet<Person> group);

    /**
     * Show group transactions in the groupMovementsScroll.
     *
     * @param transactions to be showed
     */
    void showTransactions(ObservableSet<GroupTransaction> transactions);
}
