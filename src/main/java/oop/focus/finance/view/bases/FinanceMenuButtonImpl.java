package oop.focus.finance.view.bases;

import javafx.scene.control.Button;

import java.util.function.Consumer;

public class FinanceMenuButtonImpl<X> implements FinanceMenuButton<X> {

    private final Button button;
    private final Consumer<X> action;

    public FinanceMenuButtonImpl(final String string, final Consumer<X> action) {
        this.button = new Button(string);
        this.action = action;
    }

    @Override
    public final Button getButton() {
        return this.button;
    }

    @Override
    public final void getAction(final X controller) {
        this.action.accept(controller);
    }
}
