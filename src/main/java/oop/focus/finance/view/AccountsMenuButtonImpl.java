package oop.focus.finance.view;

import javafx.scene.control.Button;
import oop.focus.finance.controller.TransactionsController;

import java.util.function.Consumer;

public class AccountsMenuButtonImpl implements AccountsMenuButton {

    private final Button button;
    private final Consumer<TransactionsController> action;

    public AccountsMenuButtonImpl(final String string, final Consumer<TransactionsController> action) {
        this.button = new Button(string);
        this.action = action;
    }

    @Override
    public final Button getButton() {
        return this.button;
    }

    @Override
    public final void getAction(final TransactionsController controller) {
        this.action.accept(controller);
    }
}
