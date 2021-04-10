package oop.focus.diary.view;


import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

import oop.focus.diary.controller.ToDoListControllerImpl;
import oop.focus.diary.model.ToDoAction;


public class AnnotationViewImpl implements AnnotationView {
    private static final double CHECKBOX_HEIGHT =  0.05;
    private final ListView<CheckBox> listView;
    private final ObservableList<CheckBox> checkBoxes;
    private final Button remove;
    private final ReadOnlyDoubleProperty height;
    private final ToDoListControllerImpl controller;
    public AnnotationViewImpl(final Button remove, final ReadOnlyDoubleProperty height, final ToDoListControllerImpl controller)  {
        this.controller = controller;
        this.height = height;
        this.remove = remove;
        this.remove.setDisable(true);
        this.checkBoxes =  FXCollections.observableArrayList();
        this.listView = new ListView<>();
        this.updateTDLView();
       controller.allAnnotations().addListener((ListChangeListener<ToDoAction>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    final ToDoAction change = c.getAddedSubList().get(0);
                    this.checkBoxes.add(this.createCheckBox(change));
                }
            }
        });
        this.checkBoxes.forEach(a -> a.setOnAction(event -> controller.changeCheck(a.getText())));
        this.remove.setOnMouseClicked(event -> {
            final String a = this.listView.getSelectionModel().getSelectedItem().getText();
            controller.remove(a);
            this.listView.getItems().clear();
            this.updateTDLView();
        });
        this.listView.setItems(this.checkBoxes);

    }

    private CheckBox createCheckBox(final ToDoAction action) {
        final CheckBox box = new CheckBox(action.getAnnotation());
        box.prefHeightProperty().bind(this.height.multiply(CHECKBOX_HEIGHT));
        box.setSelected(action.isDone());
        return box;
    }

    @Override
    public final ListView<CheckBox> getListView() {
        this.listView.setOnMouseClicked(event -> this.remove.setDisable(false));
        return this.listView;
    }

    private void updateTDLView() {
        controller.allAnnotations().stream().map(this::createCheckBox).forEach(this.checkBoxes::add);
    }

}
