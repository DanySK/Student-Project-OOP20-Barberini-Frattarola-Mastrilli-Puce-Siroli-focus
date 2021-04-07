package oop.focus.finance.view;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.view.AllertGenerator;
import oop.focus.statistics.view.MultiSelectorView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewGroupTransactionViewImpl implements Initializable, FinanceWindow {

    @FXML
    private Pane newGroupTransactionPane;
    @FXML
    private Label titleLabel, descriptionLabel, madeByLabel, forLabel, amountLabel;
    @FXML
    private TextField descriptionTextField, amountTextField;
    @FXML
    private ChoiceBox<Person> madeByChoice;
    @FXML
    private Pane multiPane;
    @FXML
    private Button cancelButton, saveButton;

    private final MultiSelectorView<Person> multiSelector;
    private final GroupController controller;
    private Parent root;

    public NewGroupTransactionViewImpl(final GroupController controller) {
        this.controller = controller;
        this.multiSelector = new MultiSelectorView<>(this.controller.getGroup(), Person::getName);
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.NEWGROUPMOV.getPath()));
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
        this.titleLabel.setText("Nuova transazione di gruppo");
        this.descriptionLabel.setText("Descrizione:");
        this.madeByLabel.setText("Fatta da:");
        this.forLabel.setText("Per:");
        this.amountLabel.setText("Importo:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.madeByChoice.setItems(new ObservableListWrapper<>(new ArrayList<>(this.controller.getGroup())));
        this.multiPane.getChildren().add(this.multiSelector.getRoot());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void close() {
        final Stage stage = (Stage) this.newGroupTransactionPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || !isNumeric(this.amountTextField.getText())
                || this.multiSelector.getSelected().size() == 0 || this.madeByChoice.getValue() == null) {
            final AllertGenerator allert = new AllertGenerator();
            allert.showAllert();
        } else {
            this.controller.newGroupTransaction(this.descriptionTextField.getText(), this.madeByChoice.getValue(),
                    this.multiSelector.getSelected(), Double.parseDouble(this.amountTextField.getText()));
            this.close();
        }
    }

    private static boolean isNumeric(final String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
