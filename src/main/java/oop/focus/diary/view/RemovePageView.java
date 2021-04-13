package oop.focus.diary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Label;
import oop.focus.common.View;
import oop.focus.diary.controller.DiaryPages;

public class RemovePageView implements View {
    private final View view;
    private final ObservableMap<Label, String> map;
    public RemovePageView(final DiaryPages controller) {
        this.map = FXCollections.observableHashMap();
        controller.getObservableSet().forEach(s -> this.map.put(new Label(s.getName()), s.getName()));
        this.view = new WindowRemoveAnnotation<>(controller, this.map);
    }
    @Override
    public final Node getRoot() {
        return this.view.getRoot();
    }
}
