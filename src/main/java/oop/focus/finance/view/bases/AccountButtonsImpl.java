package oop.focus.finance.view.bases;

import javafx.scene.layout.VBox;
import oop.focus.finance.controller.TransactionsController;

import java.util.ArrayList;
import java.util.List;

public class AccountButtonsImpl extends VBox {

    public AccountButtonsImpl(final TransactionsController controller) {
        final List<FinanceMenuButton<TransactionsController>> acccountButtons = new ArrayList<>();
        final ButtonFactory factory = new ButtonFactoryImpl();
        acccountButtons.add(factory.getAccountTransactions(controller));
        controller.getAccounts().forEach(a -> acccountButtons.add(factory.getAccountTransactions(controller, a)));
        acccountButtons.forEach(b -> this.getChildren().add(b.getButton()));
        acccountButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }
}
