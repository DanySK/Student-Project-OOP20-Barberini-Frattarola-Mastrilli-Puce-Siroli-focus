package oop.focus.finance.view.bases;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import oop.focus.common.View;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.statistics.view.ViewFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AccountButtonsImpl implements View {

    private static final double PADDING_RATIO = 0.01;
    private static final double BUTTON_RATIO = 0.05;
    private final Pane pane;

    public AccountButtonsImpl(final TransactionsController controller) {
        this.pane = ViewFactory.verticalWithPadding(PADDING_RATIO, PADDING_RATIO, PADDING_RATIO);
        final List<FinanceMenuButton<TransactionsController>> acccountButtons = new ArrayList<>();
        final ButtonFactory factory = new ButtonFactoryImpl();
        acccountButtons.add(factory.getAccountTransactions(controller));
        controller.getAccounts().stream()
                .sorted(Comparator.comparing(Account::getName))
                .forEach(a -> acccountButtons.add(factory.getAccountTransactions(controller, a)));
        acccountButtons.forEach(b -> b.getButton().setPrefWidth(Screen.getPrimary().getBounds().getWidth() * BUTTON_RATIO));
        acccountButtons.forEach(b -> this.pane.getChildren().add(b.getButton()));
        acccountButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }

    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
