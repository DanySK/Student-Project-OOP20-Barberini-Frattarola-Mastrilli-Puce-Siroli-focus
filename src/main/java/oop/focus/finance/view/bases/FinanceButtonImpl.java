package oop.focus.finance.view.bases;

import javafx.scene.control.Button;

import java.util.function.Consumer;

/**
 * Class that implements a finance button, consisting of a button and an action.
 *
 * @param <X> type of controller in which the method executed in the button action is present
 */
public class FinanceButtonImpl<X> implements FinanceButton<X> {

    private final Button button;
    private final Consumer<X> action;

    public FinanceButtonImpl(final String string, final Consumer<X> action) {
        this.button = new Button(string);
        this.action = action;
    }

    @Override
    public final Button getButton() {
        return this.button;
    }

    @Override
    public final void action(final X controller) {
        this.action.accept(controller);
    }
}
