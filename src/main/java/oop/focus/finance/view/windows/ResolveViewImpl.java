package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.ResolveController;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.statistics.view.ViewFactory;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that implements the view of all generated group transactions to quickly resolve all debts.
 */
public class ResolveViewImpl extends GenericWindow<ResolveController> {

    @FXML
    private Label resolveLabel;
    @FXML
    private ScrollPane resolveScroll;
    @FXML
    private Button cancelButton, saveButton;

    public ResolveViewImpl(final ResolveController controller) {
        super(controller, FXMLPaths.RESOLVE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.showResolvingTiles();
        this.resolveScroll.setFitToWidth(true);
    }

    /**
     * Method showing all resolving transactions.
     */
    private void showResolvingTiles() {
        final ViewFactory viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<GroupTransaction>> resolvingTiles = new ArrayList<>();
        super.getX().getResolvingTransactions().forEach(t -> resolvingTiles.add(
                new GenericTileViewImpl<>(t, t.getMadeBy().getName() + " -> "
                        + t.getForList().get(0).getName(), (double) t.getAmount() / 100)));
        final View vbox = viewFactory.createVerticalAutoResizingWithNodes(resolvingTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        this.resolveScroll.setContent(vbox.getRoot());
    }

    /**
     * {@inheritDoc}
     * If the user confirms this, all resolving transactions are performed.
     */
    @Override
    public final void save() {
        final Optional<ButtonType> result = super.confirm("Sicuro di voler eseguire le transazioni risolutive?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().resolve();
        }
        this.close();
    }
}
