package oop.focus.finance.view;

import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GroupViewImpl implements Initializable, GroupView {

    @FXML
    private ScrollPane groupMovementsScroll;
    @FXML
    private Button peopleButton;
    @FXML
    private Button groupTransactionsButton;
    @FXML
    private Button newPersonButton;
    @FXML
    private Button resolveButton;
    @FXML
    private Button newGroupTransactionButton;

    private final GroupController controller;
    private Parent root;

    public GroupViewImpl(final GroupController controller) {
        this.controller = controller;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.GROUP.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    private void populate() {
        this.peopleButton.setOnAction(event -> this.controller.showPeople());
        this.groupTransactionsButton.setOnAction(event -> this.controller.showTansactions());
    }

    @Override
    public final void showPeople(final ObservableSet<Person> group) {
        List<PersonView> personTiles = new ArrayList<>();
        group.forEach(p -> personTiles.add(new PersonViewImpl(this.controller, p)));
        VBox box = new VBox();
        personTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        personTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.controller.deletePerson(t.getPerson())));
        this.groupMovementsScroll.setContent(box);
    }

    @Override
    public final void showTransactions(final ObservableSet<GroupTransaction> transactions) {
        List<GroupTransactionView> transactionTiles = new ArrayList<>();
        transactions.forEach(t -> transactionTiles.add(new GroupTransactionViewImpl(this.controller, t)));
        VBox box = new VBox();
        transactionTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        transactionTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.controller.deleteTransaction(t.getTransaction())));
        this.groupMovementsScroll.setContent(box);
    }
}
