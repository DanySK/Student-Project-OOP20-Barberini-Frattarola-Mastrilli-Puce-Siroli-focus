package oop.focus.statistics.view;

import javafx.collections.ObservableSet;
import javafx.scene.control.Label;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.homepage.model.Event;
import oop.focus.statistics.controller.TimePeriodInput;
import oop.focus.statistics.controller.TimePeriodInputBuilderImpl;
import oop.focus.statistics.controller.UpdatableController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link InputViewFactory}.
 */
public class InputViewFactoryImpl implements InputViewFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public final View financeInputView(final ObservableSet<Account> accounts,
                                       final UpdatableController<TimePeriodInput<Account>> controller) {
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
                    controller.updateInput(new TimePeriodInputBuilderImpl<Account>()
                            .from(this.getStartDate())
                            .to(this.getEndDate())
                            .values(selector.getSelected())
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
                                      final UpdatableController<TimePeriodInput<String>> controller) {
        final MultiSelector<Event> selector = new MultiSelectorView<>(events, Event::getName);
        return new AbstractPeriodInputView<>() {
            @Override
            View createSelector() {
                return new ViewFactoryImpl()
                        .createVerticalAutoResizingWithNodes(List.of(new Label("Eventi"), selector.getRoot()));
            }

            @Override
            protected void save() {
                try {
                    System.out.println("Selezionati ->" + selector.getSelected());
                    controller.updateInput(new TimePeriodInputBuilderImpl<String>()
                            .from(this.getStartDate())
                            .to(this.getEndDate())
                            .values(selector.getSelected().stream()
                                    .map(Event::getName).collect(Collectors.toSet()))
                            .save());
                } catch (IllegalStateException e) {
                    this.showError();
                }
            }
        };
    }
}
