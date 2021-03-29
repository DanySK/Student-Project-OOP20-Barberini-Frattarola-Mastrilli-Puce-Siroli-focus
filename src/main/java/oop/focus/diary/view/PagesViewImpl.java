package oop.focus.diary.view;

import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
    private final ObservableList<DiaryImpl> list;
    public PagesViewImpl(final Button rem) {
        this.cf = UseControllerDiary.getCF();
        this.pages = new Accordion();
        this.list = this.cf.getObservableList();
        this.remove = rem;
        this.insertPages();
        this.list.addListener((ListChangeListener<DiaryImpl>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    this.updateView(c.getAddedSubList().get(0).getName());
                } else if (c .wasRemoved()) {
                    this.pages.getPanes().clear();
                    this.insertPages();
                }
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

