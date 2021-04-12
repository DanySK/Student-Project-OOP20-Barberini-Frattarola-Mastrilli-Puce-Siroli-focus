package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.DetailsController;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.homepage.model.Person;

public class PersonDetailsWindowImpl extends GenericWindow<DetailsController<Person>> {

    @FXML
    private Label titleLabel, descriptionLabel, categoryLabel, dateLabel, accountLabel, amountLabel, subscriptionLabel,
            dataDescriptionLabel, dataCategoryLabel, firstEuroLabel;
    @FXML
    private Button closeButton, deleteButton;

    public PersonDetailsWindowImpl(final DetailsController<Person> controller) {
        super(controller, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void populate() {
        this.populateStaticLabels();
        this.populateDynamicLabels();
        this.populateButtons();
    }

    private void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI DI " + super.getX().getElement().getName());
        this.descriptionLabel.setText("Nome:");
        this.categoryLabel.setText("Parentela:");
        this.dateLabel.setVisible(false);
        this.accountLabel.setVisible(false);
        this.amountLabel.setVisible(false);
        this.subscriptionLabel.setVisible(false);
        this.firstEuroLabel.setVisible(false);
    }

    private void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(super.getX().getElement().getName());
        this.dataCategoryLabel.setText(super.getX().getElement().getRelationships());
    }

    private void populateButtons() {
        this.deleteButton.setText("Elimina");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.save());
        this.closeButton.setOnAction(event -> this.close());
    }

    @Override
    public final void save() {
        final var result = super.confirm("Sicuro di voler elminare " + super.getX().getElement().getName() + "?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                super.getX().deleteElement(super.getX().getElement());
            } catch (IllegalStateException e) {
                super.allert("Non e' possibile eliminare " + super.getX().getElement().getName()
                        + " perche' ha ancora dei debiti.");
            }
        }
        this.close();
    }
}
