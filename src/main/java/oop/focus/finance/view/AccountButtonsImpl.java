package oop.focus.finance.view;

import javafx.scene.layout.VBox;
import oop.focus.finance.controller.TransactionsController;

import java.util.ArrayList;
import java.util.List;

public class AccountButtonsImpl extends VBox {

    public AccountButtonsImpl(final TransactionsController controller) {
        final List<AccountsMenuButton> acccountButtons = new ArrayList<>();
        final ButtonFactory factory = new ButtonFactoryImpl();
        acccountButtons.add(factory.getAccountTransactions(controller));
        for (final var acc : controller.getAccounts()) {
            acccountButtons.add(factory.getAccountTransactions(controller, acc));
        }
        acccountButtons.forEach(b -> this.getChildren().add(b.getButton()));
        acccountButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }
}
