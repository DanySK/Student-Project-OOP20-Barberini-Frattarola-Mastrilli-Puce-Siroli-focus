package oop.focus.diary.view;

import java.io.IOException;

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import oop.focus.diary.controller.DiaryPagesImpl;
import oop.focus.diary.controller.UseControllerDiary;
import oop.focus.diary.model.DiaryImpl;

public class PagesViewImpl implements PagesView {
    private static final String BASE_DIARY = "/layouts/diary/pagesDiaryScheme.fxml";
    private final Accordion pages;
    private final DiaryPagesImpl cf;
    private String toRemove;
    private final Button remove;
    private final ObservableSet<DiaryImpl> set;
    public PagesViewImpl(final Button rem) {
        this.cf = UseControllerDiary.getCF();
        this.pages = new Accordion();
        this.set = this.cf.getObservableList();
        this.remove = rem;
        this.insertPages();
        this.set.addListener((SetChangeListener<DiaryImpl>) change -> {
            if(change.wasAdded()) {
                updateView(change.getElementAdded().getName());
            } else if (change.wasRemoved()) {
                pages.getPanes().clear();
                insertPages();
            }
        });
    }

    @Override
    public final Accordion getAccordion() {
        return this.pages;
    }

    private void updateView(final String s) {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource(BASE_DIARY));
        try {
            final AnchorPane pane = fxmlLoader.load();
            final InsertNewDiaryPageImpl sic  = fxmlLoader.getController();
            sic.setData(s);
            this.pages.getPanes().add(sic.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

