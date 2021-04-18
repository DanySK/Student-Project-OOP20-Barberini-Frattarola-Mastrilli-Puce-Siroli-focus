package oop.focus.statistics.view;

import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.homepage.model.Event;
import oop.focus.statistics.controller.TimePeriodInput;
import oop.focus.common.UpdatableController;

/**
 * The interface Input view factory defines methods to create different input views.
 */
public interface InputViewFactory {
    /**
     * Creates an input view that allows the user to
     * select multiple {@link Account} and a time period, bundled in a {@link TimePeriodInput} of {@link Account}.
     * The input changes will be notified to the given controller.
     *
     * @param accounts   the accounts set
     * @param controller the controller
     * @return the input view
     */
    View financeInputView(ObservableSet<Account> accounts, UpdatableController<TimePeriodInput<Account>> controller);

    /**
     * Creates an input view that allows the user to
     * select multiple {@link Event} names and a time period, bundled in a {@link TimePeriodInput}.
     * The input changes will be notified to the given controller.
     *
     * @param events     the accounts set
     * @param controller the controller
     * @return the input view
     */
    View eventsInputView(ObservableSet<Event> events, UpdatableController<TimePeriodInput<String>> controller);
}
