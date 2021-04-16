package oop.focus.finance.view.bases;

import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.AddPersonControllerImpl;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.controller.NewGroupTransactionControllerImpl;
import oop.focus.finance.controller.ResolveControllerImpl;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.finance.view.windows.AddPersonViewImpl;
import oop.focus.finance.view.windows.GroupTransactionDetailsWindowImpl;
import oop.focus.finance.view.windows.NewGroupTransactionViewImpl;
import oop.focus.finance.view.windows.PersonDetailsWindowImpl;
import oop.focus.finance.view.windows.ResolveViewImpl;
import oop.focus.homepage.model.Person;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupViewImpl extends GenericView<GroupController> implements GroupView {

    @FXML
    private BorderPane mainPane;
    @FXML
    private ScrollPane groupMovementsScroll;
    @FXML
    private Button peopleButton, groupTransactionsButton, newPersonButton, resolveButton, newGroupTransactionButton;

    public GroupViewImpl(final GroupController controller) {
        super(controller, FXMLPaths.GROUP);
    }

    @Override
    public final void populate() {
        this.peopleButton.setOnAction(event -> super.getX().showPeople());
        this.groupTransactionsButton.setOnAction(event -> super.getX().showTansactions());
        this.resolveButton.setOnAction(event -> this.showWindow(new ResolveViewImpl(
                new ResolveControllerImpl(super.getX().getManager()))));
        this.newGroupTransactionButton.setOnAction(event -> this.showWindow(
                new NewGroupTransactionViewImpl(new NewGroupTransactionControllerImpl(super.getX().getManager()))));
        this.setPref();
    }

    private void setPref() {
        this.peopleButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.groupTransactionsButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.newPersonButton.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.resolveButton.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.newGroupTransactionButton.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.groupMovementsScroll.setFitToWidth(true);
    }

    @Override
    public final void showPeople(final ObservableSet<Person> group) {
        this.newPersonButton.setText("Aggiungi persona al gruppo");
        this.newPersonButton.setOnAction(event -> this.showWindow(new AddPersonViewImpl(
                new AddPersonControllerImpl(super.getX().getManager()))));
        var viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<Person>> personTiles = new ArrayList<>();
        super.getX().getSortedGroup().forEach(p -> personTiles.add(
                new GenericTileViewImpl<>(p, p.getName(), this.format(super.getX().getCredit(p)))));
        var vbox = viewFactory.createVerticalAutoResizingWithNodes(personTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        personTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                        this.showWindow(new PersonDetailsWindowImpl(super.getX(), t.getElement()))));
        this.groupMovementsScroll.setContent(vbox.getRoot());
    }

    @Override
    public final void showTransactions(final ObservableSet<GroupTransaction> transactions) {
        this.newPersonButton.setText("Reset");
        this.newPersonButton.setOnAction(event -> this.reset());
        var viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<GroupTransaction>> transactionTiles = new ArrayList<>();
        super.getX().getSortedGroupTransactions().forEach(t -> transactionTiles.add(
                new GenericTileViewImpl<>(t, t.getDescription(), t.getMadeBy().getName() + " -> "
                        + this.getForListNames(t.getForList()), this.format((double) t.getAmount() / 100))));
        var vbox = viewFactory.createVerticalAutoResizingWithNodes(transactionTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        transactionTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                        this.showWindow(new GroupTransactionDetailsWindowImpl(super.getX(), t.getElement()))));
        this.groupMovementsScroll.setContent(vbox.getRoot());
    }

    private void showWindow(final View impl) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) impl.getRoot()));
        stage.show();
    }

    private void reset() {
        var result = super.confirm("Sicuro di voler eliminare il gruppo e le relative transazioni?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                super.getX().reset();
            } catch (Exception e) {
                super.allert("Impossibile resettare: alcune persone devono ancora saldare dei debiti.");
            }
        }
    }

    private String getForListNames(final List<Person> list) {
        return list.stream().map(Person::getName).collect(Collectors.joining(", "));
    }
}
