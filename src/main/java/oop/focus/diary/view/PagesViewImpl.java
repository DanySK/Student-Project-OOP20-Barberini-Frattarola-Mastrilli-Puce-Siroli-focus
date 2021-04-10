package oop.focus.diary.view;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import oop.focus.diary.controller.DiaryPagesImpl;
import oop.focus.diary.model.DiaryImpl;

public class PagesViewImpl implements PagesView {
    private static final double TITLED_PANE_DIM = 0.474;
    private final Accordion pages;
    private String toRemove;
    private final Button remove;
    private final ObservableSet<DiaryImpl> set;
    private final DiaryPagesImpl controller;
    public PagesViewImpl(final Button rem, final ReadOnlyDoubleProperty width, final ReadOnlyDoubleProperty height,
                         final DiaryPagesImpl controller) {
        this.remove = rem;
        this.controller = controller;
        System.out.println("qui");
        this.set = controller.getObservableSet();
        this.pages = new Accordion();
        this.insertPages();
        this.setProperties(width, height);
        this.set.addListener((SetChangeListener<DiaryImpl>) change -> {
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
    @Override
    public final Accordion getAccordion() {
        return this.pages;
    }

    /**
     * The method adds new Titled Pane to the accordion.
     * @param s the title of new titled pane(which is also the title of the page to add)
     */
    private void updateView(final String s) {
        this.pages.getPanes().add(new SingleTitledPaneDiaryImpl(controller).createTitledPane(s));
    }

    /**
     * The method can be used to add all pages' saved to the accordion.
     */
    private void insertPages() {
        controller.filesName().forEach(this::updateView);
        this.remove.setOnMouseClicked((EventHandler<Event>) event -> controller.removePage(this.toRemove));
        this.pages.getPanes().forEach(e -> e.setOnMouseClicked((EventHandler<Event>) event -> {
            this.toRemove = e.getText();
            this.remove.setDisable(false);
        }));

    }
}

