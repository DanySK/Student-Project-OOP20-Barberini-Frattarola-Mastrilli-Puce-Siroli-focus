package oop.focus.finance.view.bases;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import oop.focus.common.View;
import oop.focus.finance.controller.BaseController;
import oop.focus.statistics.view.ViewFactory;

import java.util.ArrayList;
import java.util.List;

public class ButtonsBoxImpl implements View {

    private static final double PADDING_RATIO = 0.01;
    private static final double BUTTON_RATIO = 0.05;
    private final Pane pane;

    public ButtonsBoxImpl(final BaseController controller) {
        this.pane = ViewFactory.verticalWithPadding(PADDING_RATIO, PADDING_RATIO, PADDING_RATIO);
        final List<FinanceMenuButton<BaseController>> menuButtons = new ArrayList<>();
        final ButtonFactory factory = new ButtonFactoryImpl();
        menuButtons.add(factory.getTransactions(controller, "Tutte", t -> true, controller.getManager()));
        menuButtons.add(factory.getTransactions(controller, "Uscite", t -> t.getAmount() < 0, controller.getManager()));
        menuButtons.add(factory.getTransactions(controller, "Entrate", t -> t.getAmount() > 0, controller.getManager()));
        menuButtons.add(factory.getStatistics(controller, "Statistiche", controller.getManager()));
        menuButtons.add(factory.getSubscriptions(controller, "Abbonamenti", controller.getManager()));
        menuButtons.add(factory.getGroupTransactions(controller, "Gruppo", controller.getManager()));
        menuButtons.forEach(b -> b.getButton().setPrefWidth(Screen.getPrimary().getBounds().getWidth() * BUTTON_RATIO));
        menuButtons.forEach(b -> this.pane.getChildren().add(b.getButton()));
        menuButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(controller)));
    }

    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
