package oop.focus.finance.view;

import javafx.scene.control.Button;
import oop.focus.finance.controller.BaseController;

import java.util.function.Consumer;

public class FinanceMenuButtonImpl implements FinanceMenuButton {

    private final Button button;
    private final Consumer<BaseController> action;

    public FinanceMenuButtonImpl(final String string, final Consumer<BaseController> action) {
        this.button = new Button(string);
        this.action = action;
    }

    @Override
    public final Button getButton() {
        return this.button;
    }

    @Override
    public final void getAction(final BaseController controller) {
        this.action.accept(controller);
    }
}
