package oop.focus.diary.view;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import oop.focus.diary.controller.DiaryPagesImpl;
import oop.focus.diary.controller.UseControllerDiary;
import oop.focus.diary.model.DiaryImpl;

public class PagesViewImpl implements PagesView {
    private static final double TITLED_PANE_DIM = 0.55;
    private final Accordion pages;
    private final DiaryPagesImpl cf;
    private String toRemove;
    private final Button remove;
    private final ObservableSet<DiaryImpl> set;
    public PagesViewImpl(final Button rem, final ReadOnlyDoubleProperty multiply, final ReadOnlyDoubleProperty heightProperty) {
        this.remove = rem;
        this.cf = UseControllerDiary.getCF();
        System.out.println("qui");
        this.set = this.cf.getObservableSet();
        this.pages = new Accordion();
        this.insertPages();
        this.setProperties(multiply, heightProperty);
        this.set.addListener((SetChangeListener<DiaryImpl>) change -> {
            if (change.wasAdded()) {
                this.updateView(change.getElementAdded().getName());
            } else if (change.wasRemoved()) {
                this.pages.getPanes().clear();
                 this.insertPages();
            }
        });
    }

    private void setProperties(final ReadOnlyDoubleProperty width, final ReadOnlyDoubleProperty height) {
       this.pages.prefWidthProperty().bind(width.multiply(TITLED_PANE_DIM));
       this.pages.prefHeightProperty().bind(height.multiply(TITLED_PANE_DIM));
    }
    @Override
    public final Accordion getAccordion() {
        return this.pages;
    }

    private void updateView(final String s) {
        this.pages.getPanes().add(new SingleTitledPaneDiaryImpl().createTitledPane(s));
    }

    private void insertPages() {
        this.cf.filesName().forEach(this::updateView);
        this.remove.setOnMouseClicked((EventHandler<Event>) event -> UseControllerDiary.getCF().removePage(this.toRemove));
        this.pages.getPanes().forEach(e -> e.setOnMouseClicked((EventHandler<Event>) event -> {
            this.toRemove = e.getText();
            this.remove.setDisable(false);
        }));

    }
}

