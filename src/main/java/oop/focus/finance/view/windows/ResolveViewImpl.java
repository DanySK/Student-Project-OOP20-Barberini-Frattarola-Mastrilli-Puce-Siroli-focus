package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button cancelButton, saveButton, showButton;

    public ResolveViewImpl(final GroupController groupController, final FXMLPaths path) {
        super(groupController, path);
    }

    @Override
    protected final void populate() {
        this.resolveLabel.setText("Risolvi");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.showButton.setText("Mostra");
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.showButton.setOnAction(event -> this.showResolvingTiles());
    }

    private void showResolvingTiles() {
        final List<GroupTransactionView> resolvingTiles = new ArrayList<>();
        super.getX().getResolvingTransactions().forEach(t -> resolvingTiles.add(new GroupTransactionViewImpl(t, FXMLPaths.GROUPTILE)));
        final VBox box = new VBox();
        resolvingTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        this.resolveScroll.setContent(box);
    }

    @Override
    public final void save() {
        super.getX().resolve();
        this.close();
    }

}
