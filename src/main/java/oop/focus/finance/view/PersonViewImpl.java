package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.homepage.model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonViewImpl implements Initializable, PersonView {

    @FXML
    private Label descriptionLabel, amountLabel;

    private final GroupController controller;
    private final Person person;
    private Parent root;

    public PersonViewImpl(final GroupController controller, final Person person) {
        this.person = person;
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.GENERICTILE.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.descriptionLabel.setText(this.person.getName());
        this.amountLabel.setText("E " + (double) this.controller.getCredit(this.person) / 100);
    }

    @Override
    public final Person getPerson() {
        return this.person;
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
