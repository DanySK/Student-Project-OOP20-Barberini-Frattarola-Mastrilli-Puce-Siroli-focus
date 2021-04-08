package oop.focus.finance.view.bases;

import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.tiles.GroupTransactionView;
import oop.focus.finance.view.tiles.GroupTransactionViewImpl;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.finance.view.windows.AddPersonViewImpl;
import oop.focus.finance.view.windows.NewGroupTransactionViewImpl;
import oop.focus.finance.view.windows.ResolveViewImpl;
import oop.focus.homepage.model.Person;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GroupViewImpl extends GenericView<GroupController> implements GroupView {

    @FXML
    private ScrollPane groupMovementsScroll;
    @FXML
    private Button peopleButton, groupTransactionsButton, newPersonButton, resolveButton, newGroupTransactionButton;

    public GroupViewImpl(final GroupController controller, final FXMLPaths path) {
        super(controller, path);
    }

    @Override
    public final void populate() {
        this.peopleButton.setOnAction(event -> super.getX().showPeople());
        this.groupTransactionsButton.setOnAction(event -> super.getX().showTansactions());
        this.newPersonButton.setOnAction(event -> this.showWindow(new AddPersonViewImpl(super.getX(), FXMLPaths.ADDPERSON)));
        this.newGroupTransactionButton.setOnAction(event -> this.showWindow(
                new NewGroupTransactionViewImpl(super.getX(), FXMLPaths.NEWGROUPMOV)));
        this.resolveButton.setOnAction(event -> this.showWindow(new ResolveViewImpl(super.getX(), FXMLPaths.RESOLVE)));
    }

    @Override
    public final void showPeople(final ObservableSet<Person> group) {
        final List<GenericTileView<Person>> personTiles = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#0.00");
        group.forEach(p -> personTiles.add(new GenericTileViewImpl<>(p, p.getName(),
                "E " + df.format((double) super.getX().getCredit(p) / 100))));
        final VBox box = new VBox();
        personTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        personTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> super.getX().deletePerson(t.getElement())));
        this.groupMovementsScroll.setContent(box);
    }

    @Override
    public final void showTransactions(final ObservableSet<GroupTransaction> transactions) {
        final List<GroupTransactionView> transactionTiles = new ArrayList<>();
        transactions.forEach(t -> transactionTiles.add(new GroupTransactionViewImpl(t, FXMLPaths.GROUPTILE)));
        final VBox box = new VBox();
        transactionTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        transactionTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> super.getX().deleteTransaction(t.getTransaction())));
        this.groupMovementsScroll.setContent(box);
    }

    private void showWindow(final View impl) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) impl.getRoot()));
        stage.show();
    }
}
