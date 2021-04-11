package oop.focus.statistics.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.control.Label;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.homepage.model.Event;
import oop.focus.statistics.controller.EventsInput;
import oop.focus.statistics.controller.EventsInputBuilderImpl;
import oop.focus.statistics.controller.FinanceInput;
import oop.focus.statistics.controller.FinanceInputBuilderImpl;
import oop.focus.statistics.controller.InputController;

import java.util.List;
import java.util.function.Function;

/**
 * Implementation of {@link InputViewFactory}.
 */
public class InputViewFactoryImpl implements InputViewFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final View financeInputView(final ObservableSet<Account> accounts,
                                       final InputController<FinanceInput> controller) {
        final MultiSelector<Account> selector = new MultiSelectorView<>(accounts, Account::getName);
        return new AbstractPeriodInputView<>() {
            @Override
            View createSelector() {
                return new ViewFactoryImpl().createVerticalAutoResizingWithNodes(List.of(new Label("Conti"),
                        selector.getRoot()));
            }

            @Override
            protected void save() {
                try {
                    controller.updateInput(new FinanceInputBuilderImpl()
                            .from(this.getStartDate())
                            .to(this.getEndDate())
                            .accounts(selector.getSelected())
                            .save());
                } catch (IllegalStateException e) {
                    this.showError();
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View eventsInputView(final ObservableSet<Event> events,
                                      final InputController<EventsInput> controller) {
        final ObservableSet<String> eventNames = FXCollections.observableSet();
        Linker.setToSet(events, eventNames, Event::getName);
        final MultiSelector<String> selector = new MultiSelectorView<>(eventNames, Function.identity());
        return new AbstractPeriodInputView<>() {
            @Override
            View createSelector() {
                return new ViewFactoryImpl()
                        .createVerticalAutoResizingWithNodes(List.of(new Label("Eventi"), selector.getRoot()));
            }

            @Override
            protected void save() {
                try {
                    controller.updateInput(new EventsInputBuilderImpl()
                            .from(this.getStartDate())
                            .to(this.getEndDate())
                            .names(selector.getSelected())
                            .save());
                } catch (IllegalStateException e) {
                    this.showError();
                }
            }
        };
    }
}
