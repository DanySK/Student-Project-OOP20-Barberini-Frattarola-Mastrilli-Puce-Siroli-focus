package oop.focus.statistics.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.control.Label;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.finance.model.Account;
import oop.focus.homepage.model.Event;
import oop.focus.statistics.controller.TimePeriodInput;
import oop.focus.statistics.controller.TimePeriodInputBuilderImpl;
import oop.focus.common.UpdatableController;

import java.util.List;
import java.util.function.Function;

/**
 * Implementation of {@link InputViewFactory}.
 */
public class InputViewFactoryImpl implements InputViewFactory {

    private static final String EVENT_LABEL = "Eventi";
    private static final String ACCOUNT_LABEL = "Conti";

    /**
     * {@inheritDoc}
     */
    @Override
    public final View financeInputView(final ObservableSet<Account> accounts,
                                       final UpdatableController<TimePeriodInput<Account>> controller) {
        final MultiSelector<Account> selector = new MultiSelectorView<>(accounts, Account::getName);
        return new AbstractPeriodInputView<>() {
            @Override
            protected View createSelector() {
                return new ViewFactoryImpl().createVerticalAutoResizingWithNodes(List.of(
                        new Label(ACCOUNT_LABEL),
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
                } catch (final IllegalStateException e) {
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
        return new AbstractPeriodInputView<>() {
            private ObservableSet<String> eventNames;
            private MultiSelector<String> selector;

            @Override
            protected View createSelector() {
                this.addListener();
                this.selector = new MultiSelectorView<>(this.eventNames, Function.identity());
                return new ViewFactoryImpl()
                        .createVerticalAutoResizingWithNodes(List.of(new Label(EVENT_LABEL),
                                this.selector.getRoot()));
            }

            private void addListener() {
                this.eventNames = FXCollections.observableSet();
                Linker.setToSet(events, this.eventNames, Event::getName);
            }

            @Override
            protected void save() {
                try {
                    controller.updateInput(new TimePeriodInputBuilderImpl<String>()
                            .from(this.getStartDate())
                            .to(this.getEndDate())
                            .values(this.selector.getSelected())
                            .save());
                } catch (final IllegalStateException e) {
                    this.showError();
                }
            }
        };
    }
}
