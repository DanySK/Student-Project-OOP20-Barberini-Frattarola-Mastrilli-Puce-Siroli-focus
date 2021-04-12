package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.view.tiles.GroupTransactionView;
import oop.focus.finance.view.tiles.GroupTransactionViewImpl;

import java.util.ArrayList;
import java.util.List;

public class ResolveViewImpl extends GenericWindow<GroupController> {

    @FXML
    private VBox resolveVBox;
    @FXML
    private Label resolveLabel;
    @FXML
    private ScrollPane resolveScroll;
    @FXML
    private Button cancelButton, saveButton;

    public ResolveViewImpl(final GroupController groupController) {
        super(groupController, FXMLPaths.RESOLVE);
    }

    @Override
    public final void populate() {
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.showResolvingTiles();
    }

    private void showResolvingTiles() {
        this.resolveVBox.getChildren().clear();
        final List<GroupTransactionView> resolvingTiles = new ArrayList<>();
        super.getX().getResolvingTransactions().forEach(t -> resolvingTiles.add(new GroupTransactionViewImpl(t)));
        resolvingTiles.forEach(t -> this.resolveVBox.getChildren().add(t.getRoot()));
    }

    @Override
    public final void save() {
        var result = super.confirm("Sicuro di voler eseguire le transazioni risolutive?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().resolve();
        }
        this.close();
    }

}
