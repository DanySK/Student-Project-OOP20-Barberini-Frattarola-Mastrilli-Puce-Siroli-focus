package oop.focus.finance.view;

import javafx.scene.layout.VBox;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.model.FinanceManager;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBoxImpl extends VBox {

    public ButtonsBoxImpl(final BaseController controller, final FinanceManager manager) {
        final List<FinanceMenuButton> menuButtons = new ArrayList<>();
        final ButtonFactory factory = new ButtonFactoryImpl();
        menuButtons.add(factory.getTransactions(controller, "Tutte", t -> true, manager));
        menuButtons.add(factory.getTransactions(controller, "Uscite", t -> t.getAmount() < 0, manager));
        menuButtons.add(factory.getTransactions(controller, "Entrate", t -> t.getAmount() > 0, manager));
        menuButtons.add(factory.getSubscriptions(controller, "Abbonamenti", manager));
        menuButtons.add(factory.getGroupTransactions(controller, "Gruppo", manager));
        menuButtons.forEach(b -> this.getChildren().add(b.getButton()));
        menuButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }
}
