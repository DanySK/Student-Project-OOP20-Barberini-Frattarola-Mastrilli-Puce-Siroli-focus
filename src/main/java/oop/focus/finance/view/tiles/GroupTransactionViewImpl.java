package oop.focus.finance.view.tiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.bases.GenericView;
import oop.focus.homepage.model.Person;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

public class GroupTransactionViewImpl extends GenericView<GroupTransaction> implements GroupTransactionView {

    @FXML
    private Label madeByLabel, forListLabel, descriptionLabel, amountLabel;

    public GroupTransactionViewImpl(final GroupTransaction transaction) {
        super(transaction, FXMLPaths.GROUPTILE);
    }

    @Override
    public final void populate() {
        this.madeByLabel.setText(super.getX().getMadeBy().getName());
        this.forListLabel.setText(this.getForListNames());
        this.descriptionLabel.setText(super.getX().getDescription());
        this.amountLabel.setText(this.format(super.getX().getAmount()));
    }

    @Override
    public final GroupTransaction getTransaction() {
        return super.getX();
    }

    private String getForListNames() {
        return super.getX().getForList().stream().map(Person::getName).collect(Collectors.joining(", "));
    }

    private String format(final int amount) {
        final DecimalFormat df = new DecimalFormat("#0.00");
        return df.format((double) amount / 100);
    }
}
