package oop.focus.application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import oop.focus.common.View;



public class CommonView implements View {
    private static final double BOX_HEIGHT = 0.15;
    private static final double BOX_SPACING = 0.07;
    private static final double BOX_PADDING = 20;
    private static final double BUTTON_WIDTH = 0.2;
    private final BorderPane pane;
    private final CommonControllersImpl commonControllers;
    private final HBox box;
    public CommonView() {
        this.pane = new BorderPane();
        this.commonControllers = new CommonControllersImpl();
        this.box = new HBox();
        this.box.prefWidthProperty().bind(this.pane.widthProperty());
        this.box.prefHeightProperty().bind(this.pane.heightProperty().multiply(BOX_HEIGHT));
        this.box.spacingProperty().bind(this.pane.widthProperty().multiply(BOX_SPACING));
        this.box.paddingProperty().setValue(new Insets(BOX_PADDING));
        this.box.setAlignment(Pos.CENTER);
        this.createButtons();
    }

    /**
     * The method creates buttons, which pressed fill the pane with the corresponding sections' root.
     */
    private void createButtons() {
        for (final var elem : Sections.values()) {
            final Button button = new Button(elem.getName());
            this.box.getChildren().add(button);
            button.prefHeightProperty().bind(this.box.heightProperty());
            button.prefWidthProperty().bind(this.box.widthProperty().multiply(BUTTON_WIDTH));
            button.setOnMouseClicked(event -> pane.setCenter(getPane(elem)));
            //final BorderPane borderPane = new BorderPane();
            pane.getStyleClass().add(elem.getStyle());
        }
        this.pane.setTop(this.box);
    }

    /**
     * The method return the node of the section specified by the enum's value in input.
     * @param elem  the value of enum Section of which is returned the root
     * @return  a node representing the root of the section
     */
    private Node getPane(final Enum<Sections> elem) {
        if (elem.equals(Sections.DIARY)) {
            return this.commonControllers.getDiary();
        } else if (elem.equals(Sections.CALENDAR)) {
            return this.commonControllers.getCalendar();
        } else if (elem.equals(Sections.FINANCE)) {
            return this.commonControllers.getFinance();
        }
        return this.commonControllers.getFinance();
    }

    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
