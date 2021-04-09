package oop.focus.finance.view.windows;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.homepage.model.Person;
import oop.focus.statistics.view.MultiSelectorView;

import java.util.ArrayList;

public class NewGroupTransactionViewImpl extends GenericWindow<GroupController> {

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

    private MultiSelectorView<Person> multiSelector;

    public NewGroupTransactionViewImpl(final GroupController groupController) {
        super(groupController, FXMLPaths.NEWGROUPMOV);
    }

    @Override
    public final void populate() {
        this.titleLabel.setText("Nuova transazione di gruppo");
        this.descriptionLabel.setText("Descrizione:");
        this.madeByLabel.setText("Fatta da:");
        this.forLabel.setText("Per:");
        this.amountLabel.setText("Importo:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.madeByChoice.setItems(new ObservableListWrapper<>(new ArrayList<>(super.getX().getGroup())));
        this.multiSelector = new MultiSelectorView<>(super.getX().getGroup(), Person::getName);
        this.multiPane.getChildren().add(this.multiSelector.getRoot());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || isNotNumeric(this.amountTextField.getText())
                || this.multiSelector.getSelected().size() == 0 || this.madeByChoice.getValue() == null) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().newGroupTransaction(this.descriptionTextField.getText(), this.madeByChoice.getValue(),
                    this.multiSelector.getSelected(), Double.parseDouble(this.amountTextField.getText()));
            this.close();
        }
    }

}
