package oop.focus.diary.view;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

import oop.focus.diary.controller.UseTDLController;
import oop.focus.diary.model.ToDoAction;


public class AnnotationViewImpl implements AnnotationView {
    private final ListView<CheckBox> listView;
    private final ObservableList<CheckBox> checkBoxes;
    private final Button remove;
    public AnnotationViewImpl(final Button remove)  {
        this.remove = remove;
        this.remove.setDisable(true);
        this.checkBoxes =  FXCollections.observableArrayList();
        this.listView = new ListView<>();
        this.updateTDLView();
        UseTDLController.getCF().allAnnotations().addListener((ListChangeListener<ToDoAction>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    final ToDoAction change = c.getAddedSubList().get(0);
                    checkBoxes.add(createCheckBox(change));
                }
            }
        });
        this.checkBoxes.forEach(a -> a.setOnAction(event -> UseTDLController.getCF().changeCheck(a.getText())));
        this.remove.setOnMouseClicked(event -> {
            final String a = listView.getSelectionModel().getSelectedItem().getText();
            UseTDLController.getCF().remove(a);
            listView.getItems().clear();
            updateTDLView();
        });
        listView.setItems(this.checkBoxes);
    }

    private CheckBox createCheckBox(final ToDoAction action) {
        final CheckBox box = new CheckBox(action.getAnnotation());
        box.setSelected(action.isDone());
        return box;
    }

    @Override
    public final ListView<CheckBox> getListView() {
        this.listView.setOnMouseClicked(event -> remove.setDisable(false));
        return this.listView;
    }

    private void updateTDLView() {
        UseTDLController.getCF().allAnnotations().stream().map(this::createCheckBox).forEach(this.checkBoxes::add);
    }

}
