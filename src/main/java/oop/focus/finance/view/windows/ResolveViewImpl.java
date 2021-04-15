package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.ResolveController;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.homepage.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResolveViewImpl extends GenericWindow<ResolveController> {

    @FXML
    private VBox resolveVBox;
    @FXML
    private Label resolveLabel;
    @FXML
    private ScrollPane resolveScroll;
    @FXML
    private Button cancelButton, saveButton;

    public ResolveViewImpl(final ResolveController controller) {
        super(controller, FXMLPaths.RESOLVE);
    }

    @Override
    public final void populate() {
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.showResolvingTiles();
    }

    private void showResolvingTiles() {
        this.resolveVBox.getChildren().clear();
        final List<GenericTileView<GroupTransaction>> resolvingTiles = new ArrayList<>();
        super.getX().getResolvingTransactions().forEach(t -> resolvingTiles.add(
                new GenericTileViewImpl<>(t, t.getMadeBy().getName() + " ->"
                        + this.getForListNames(t.getForList()), this.format(t.getAmount()))));
        resolvingTiles.forEach(t -> this.resolveVBox.getChildren().add(t.getRoot()));
    }

    @Override
    public final void save() {
        final var result = super.confirm("Sicuro di voler eseguire le transazioni risolutive?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().resolve();
        }
        this.close();
    }

    private String getForListNames(final List<Person> list) {
        return list.stream().map(Person::getName).collect(Collectors.joining(", "));
    }
}
