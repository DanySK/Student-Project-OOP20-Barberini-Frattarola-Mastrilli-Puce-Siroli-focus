package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.homepage.model.Person;

public class PersonDetailsWindowImpl extends GenericDetailsWindow<GroupController, Person> {

    @FXML
    private Label titleLabel, descriptionLabel, categoryLabel, dateLabel, accountLabel, amountLabel, subscriptionLabel,
            dataDescriptionLabel, dataCategoryLabel, firstEuroLabel;
    @FXML
    private Button deleteButton;

    public PersonDetailsWindowImpl(final GroupController controller, final Person person) {
        super(controller, person, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI DI " + super.getX().getName());
        this.descriptionLabel.setText("Nome:");
        this.categoryLabel.setText("Parentela:");
        this.dateLabel.setVisible(false);
        this.accountLabel.setVisible(false);
        this.amountLabel.setVisible(false);
        this.subscriptionLabel.setVisible(false);
        this.firstEuroLabel.setVisible(false);
    }

    @Override
    public final void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(super.getX().getName());
        this.dataCategoryLabel.setText(super.getX().getRelationships());
    }

    @Override
    public final void save() {
        var result = super.confirm("Sicuro di voler elminare " + super.getX().getName() + "?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                super.getController().deletePerson(super.getX());
            } catch (Exception e) {
                super.allert("Non e' possibile eliminare " + super.getX().getName() + " perche' ha ancora dei debiti.");
            }
        }
        this.close();
    }
}
