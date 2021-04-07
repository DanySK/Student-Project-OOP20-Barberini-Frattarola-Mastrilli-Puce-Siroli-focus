package oop.focus.statistics.controller;

import oop.focus.db.DataSource;
import oop.focus.finance.model.FinanceManager;

public interface InputFactory {
    AbstractInputController<FinanceInput> financeInputController(StatisticController<FinanceInput> controller,
                                                                 FinanceManager manager);

    AbstractInputController<EventsInput> eventsInputController(StatisticController<EventsInput> controller,
                                                               DataSource dataSource);
}
