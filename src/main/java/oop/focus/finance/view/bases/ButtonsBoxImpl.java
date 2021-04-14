package oop.focus.finance.view.bases;

import javafx.scene.layout.VBox;
import oop.focus.finance.controller.BaseController;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBoxImpl extends VBox {

    public ButtonsBoxImpl(final BaseController controller) {
        final List<FinanceMenuButton<BaseController>> menuButtons = new ArrayList<>();
        final ButtonFactory factory = new ButtonFactoryImpl();
        menuButtons.add(factory.getTransactions(controller, "Tutte", t -> true, controller.getManager()));
        menuButtons.add(factory.getTransactions(controller, "Uscite", t -> t.getAmount() < 0, controller.getManager()));
        menuButtons.add(factory.getTransactions(controller, "Entrate", t -> t.getAmount() > 0, controller.getManager()));
        menuButtons.add(factory.getStatistics(controller, "Statistiche", controller.getManager()));
        menuButtons.add(factory.getSubscriptions(controller, "Abbonamenti", controller.getManager()));
        menuButtons.add(factory.getGroupTransactions(controller, "Gruppo", controller.getManager()));
        menuButtons.forEach(b -> b.getButton().setPrefWidth(130));
        menuButtons.forEach(b -> this.getChildren().add(b.getButton()));
        menuButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }
}
