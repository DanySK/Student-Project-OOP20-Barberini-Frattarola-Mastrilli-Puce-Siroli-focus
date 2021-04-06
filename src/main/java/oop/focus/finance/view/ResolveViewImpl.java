package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResolveViewImpl implements Initializable, FinanceWindow {

    @FXML
    private VBox resolveVBox;
    @FXML
    private Label resolveLabel;
    @FXML
    private ScrollPane resolveScroll;
    @FXML
    private Button cancelButton, saveButton, showButton;

    private final GroupController controller;
    private Parent root;

    public ResolveViewImpl(final GroupController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.RESOLVE.getPath()));
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
        this.controller.getResolvingTransactions().forEach(t -> resolvingTiles.add(new GroupTransactionViewImpl(t)));
        final VBox box = new VBox();
        resolvingTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        this.resolveScroll.setContent(box);
    }

    @Override
    public final void close() {
        final Stage stage = (Stage) this.resolveVBox.getScene().getWindow();
        stage.close();
    }

    @Override
    public final void save() {
        this.controller.resolve();
        this.close();
    }

}
