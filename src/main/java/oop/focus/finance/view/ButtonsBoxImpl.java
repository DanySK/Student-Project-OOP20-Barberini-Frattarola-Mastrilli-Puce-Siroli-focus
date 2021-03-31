package oop.focus.finance.view;

import javafx.scene.layout.VBox;
import oop.focus.finance.controller.BaseController;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBoxImpl extends VBox {

    public ButtonsBoxImpl(final BaseController controller) {
        List<FinanceMenuButton> menuButtons = new ArrayList<>();
        ButtonFactory factory = new ButtonFactoryImpl();
        menuButtons.add(factory.getTransactions(controller));
        menuButtons.add(factory.getOutings(controller));
        menuButtons.add(factory.getIncomes(controller));
        menuButtons.add(factory.getSubscriptions(controller));
        menuButtons.add(factory.getGroupTransactions(controller));
        menuButtons.forEach(b -> this.getChildren().add(b.getButton()));
        menuButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }
}
