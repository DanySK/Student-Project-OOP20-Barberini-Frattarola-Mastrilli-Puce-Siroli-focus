package oop.focus.homepage.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import oop.focus.common.Repetition;
import oop.focus.homepage.model.HotKeyType;

public class ComboBoxFiller {

    private ComboBox<String> comboBox;

    public ComboBoxFiller() {
        this.comboBox = new ComboBox<>();
    }

    public final ObservableList<String> getRepetition() {
        final ObservableList<String> list = FXCollections.observableArrayList();
        for (final Repetition repetition : Repetition.values()) {
            list.add(repetition.getName());
        }
        return list;
    }

    public final ObservableList<String> getHourAndMinute(final int range) {
        final ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < range; i++) {
            if (i < 10) {
                list.add("0" + i);
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    public final ObservableList<String> getHotKey() {
        final ObservableList<String> list = FXCollections.observableArrayList();
        for (final HotKeyType type : HotKeyType.values()) {
            list.add(type.getType());
        }
        return list;
    }

}
