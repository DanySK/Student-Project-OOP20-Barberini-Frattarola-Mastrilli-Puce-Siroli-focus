package oop.focus.finance.view.bases;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import oop.focus.common.View;
import oop.focus.finance.controller.ChangeViewController;
import oop.focus.statistics.view.ViewFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that deals with the view of finance main buttons.
 */
public class ButtonsBoxImpl implements View {

    private static final double PADDING_RATIO = 0.01;
    private static final double BUTTON_RATIO = 0.05;

    private final Pane pane;

    public ButtonsBoxImpl(final ChangeViewController controller) {
        this.pane = ViewFactory.verticalWithPadding(PADDING_RATIO, PADDING_RATIO, PADDING_RATIO);
        final List<FinanceMenuButton<ChangeViewController>> menuButtons = new ArrayList<>();
        final ButtonFactory factory = new ButtonFactoryImpl();
        menuButtons.add(factory.getTransactions("Tutte", t -> true, controller.getManager()));
        menuButtons.add(factory.getTransactions("Uscite", t -> t.getAmount() < 0, controller.getManager()));
        menuButtons.add(factory.getTransactions("Entrate", t -> t.getAmount() > 0, controller.getManager()));
        menuButtons.add(factory.getStatistics("Statistiche", controller.getManager()));
        menuButtons.add(factory.getSubscriptions("Abbonamenti", controller.getManager()));
        menuButtons.add(factory.getGroupTransactions("Gruppo", controller.getManager()));
        menuButtons.forEach(b -> b.getButton().setPrefWidth(Screen.getPrimary().getBounds().getWidth() * BUTTON_RATIO));
        menuButtons.forEach(b -> this.pane.getChildren().add(b.getButton()));
        menuButtons.forEach(b -> b.getButton().setOnAction(event -> b.action(controller)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
