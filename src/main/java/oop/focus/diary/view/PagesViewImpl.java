package oop.focus.diary.view;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.SetChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import oop.focus.common.View;
import oop.focus.diary.controller.DiaryPagesImpl;
import oop.focus.diary.model.DiaryImpl;

public class PagesViewImpl implements View {
    private static final double TITLED_PANE_DIM = 0.474;
    private final Accordion pages;

    private final DiaryPagesImpl controller;
    public PagesViewImpl(final DiaryPagesImpl controller) {
        this.controller = controller;
        this.pages = new Accordion();
        this.insertPages();
        //this.setProperties(width, height);
        controller.getObservableSet().addListener((SetChangeListener<DiaryImpl>) change -> {
            if (change.wasAdded()) {
                this.updateView(change.getElementAdded().getName());
            } else if (change.wasRemoved()) {
                this.pages.getPanes().clear();
                 this.insertPages();
            }
        });
    }

    /**
     * The method sets the dimension of the accordion, adapting them to dimensions in input.
     * @param width the width of base pane.
     * @param height the height of base pane.
     */
    private void setProperties(final ReadOnlyDoubleProperty width, final ReadOnlyDoubleProperty height) {
       this.pages.prefWidthProperty().bind(width.multiply(TITLED_PANE_DIM));
       this.pages.prefHeightProperty().bind(height.multiply(TITLED_PANE_DIM));
    }
    /**
     * The method adds new Titled Pane to the accordion.
     * @param s the title of new titled pane(which is also the title of the page to add)
     */
    private void updateView(final String s) {
        this.pages.getPanes().add(new SingleTitledPaneDiaryImpl(this.controller).createTitledPane(s));
    }

    /**
     * The method can be used to add all pages' saved to the accordion.
     */
    private void insertPages() {
        this.controller.getFileName().forEach(this::updateView);
    }

    @Override
    public final Node getRoot() {
        return this.pages;
    }
}

