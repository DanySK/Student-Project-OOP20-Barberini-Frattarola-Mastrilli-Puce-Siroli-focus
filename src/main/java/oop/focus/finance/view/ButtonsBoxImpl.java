package oop.focus.finance.view;

import javafx.scene.layout.VBox;
import oop.focus.db.DataSource;
import oop.focus.finance.controller.BaseController;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBoxImpl extends VBox {

    public ButtonsBoxImpl(final BaseController controller, final DataSource db) {
        List<FinanceMenuButton> menuButtons = new ArrayList<>();
        ButtonFactory factory = new ButtonFactoryImpl();
        menuButtons.add(factory.getTransactions(controller, "Tutte", (t -> true), db));
        menuButtons.add(factory.getTransactions(controller, "Uscite", (t -> t.getAmount() < 0), db));
        menuButtons.add(factory.getTransactions(controller, "Entrate", (t -> t.getAmount() > 0), db));
        menuButtons.add(factory.getSubscriptions(controller, "Abbonamenti", db));
        menuButtons.add(factory.getGroupTransactions(controller, "Gruppo", db));
        menuButtons.forEach(b -> this.getChildren().add(b.getButton()));
        menuButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }
}
