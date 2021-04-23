package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
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
import oop.focus.finance.view.StaticAlerts;
import oop.focus.finance.view.StaticFormats;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.finance.view.windows.AddPersonViewImpl;
import oop.focus.finance.view.windows.GroupTransactionDetailsWindowImpl;
import oop.focus.finance.view.windows.NewGroupTransactionViewImpl;
import oop.focus.finance.view.windows.PersonDetailsWindowImpl;
import oop.focus.finance.view.windows.ResolveViewImpl;
import oop.focus.calendar.persons.model.Person;
import oop.focus.statistics.view.ViewFactory;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that implements the view of group and group transactions.
 */
public class GroupViewImpl extends GenericView implements GroupView {

    @FXML
    private BorderPane mainPane;
    @FXML
    private ScrollPane groupMovementsScroll;
    @FXML
    private Button peopleButton, groupTransactionsButton, newPersonButton, resolveButton, newGroupTransactionButton;

    private final GroupController controller;
    private final ViewFactory viewFactory;

    public GroupViewImpl(final GroupController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.GROUP);
        this.viewFactory = new ViewFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.peopleButton.setOnAction(event -> this.controller.showPeople());
        this.groupTransactionsButton.setOnAction(event -> this.controller.showTransactions());
        this.resolveButton.setOnAction(event -> this.showWindow(new ResolveViewImpl(
                new ResolveControllerImpl(this.controller.getManager()))));
        this.newGroupTransactionButton.setOnAction(event -> this.showWindow(
                new NewGroupTransactionViewImpl(new NewGroupTransactionControllerImpl(this.controller.getManager()))));
        this.setPref();
    }

    /**
     * Method that takes care of the resize of the view.
     */
    private void setPref() {
        final Rectangle2D screen = Screen.getPrimary().getBounds();
        this.peopleButton.setPrefWidth(screen.getWidth());
        this.groupTransactionsButton.setPrefWidth(screen.getWidth());
        this.newPersonButton.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.resolveButton.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.newGroupTransactionButton.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.groupMovementsScroll.setFitToWidth(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void showPeople() {
        this.newPersonButton.setText("Aggiungi persona al gruppo");
        this.newPersonButton.setOnAction(event -> this.showWindow(new AddPersonViewImpl(
                new AddPersonControllerImpl(this.controller.getManager()))));
        final List<GenericTileView<Person>> personTiles = new ArrayList<>();
        this.controller.getSortedGroup().forEach(p -> personTiles.add(
                new GenericTileViewImpl<>(p, p.getName(), this.controller.getCredit(p))));
        final View vbox = this.viewFactory.createVerticalAutoResizingWithNodes(personTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        personTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                        this.showWindow(new PersonDetailsWindowImpl(this.controller, t.getElement()))));
        this.groupMovementsScroll.setContent(vbox.getRoot());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void showTransactions() {
        this.newPersonButton.setText("Reset");
        this.newPersonButton.setOnAction(event -> this.reset());
        final List<GenericTileView<GroupTransaction>> transactionTiles = new ArrayList<>();
        this.controller.getSortedGroupTransactions().forEach(t -> transactionTiles.add(
                new GenericTileViewImpl<>(t, t.getDescription(), t.getMadeBy().getName() + " -> "
                        + StaticFormats.formatPersonList(t.getForList()), (double) t.getAmount() / 100)));
        final View vbox = this.viewFactory.createVerticalAutoResizingWithNodes(transactionTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        transactionTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                        this.showWindow(new GroupTransactionDetailsWindowImpl(this.controller, t.getElement()))));
        this.groupMovementsScroll.setContent(vbox.getRoot());
    }

    /**
     * Method that displays a window on the screen.
     *
     * @param view to be shown
     */
    private void showWindow(final View view) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot()));
        stage.show();
    }

    /**
     * Method that after confirmation deletes all group transactions and group persons.
     */
    private void reset() {
        final Optional<ButtonType> result = StaticAlerts.confirm("Sicuro di voler eliminare il gruppo e le relative transazioni?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                this.controller.reset();
            } catch (final IllegalStateException e) {
                StaticAlerts.alert("Impossibile resettare: alcune persone devono ancora saldare dei debiti.");
            }
        }
    }
}
