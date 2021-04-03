package oop.focus.homepage.view;

import javafx.scene.control.ComboBox;
import oop.focus.finance.model.Repetition;
import oop.focus.homepage.model.HotKeyType;

public class ComboBoxFiller {

    private ComboBox<String> comboBox;

    public ComboBoxFiller() {
        this.comboBox = new ComboBox<>();
    }

    public final ComboBox<String> getRepetition() {
        this.comboBox.getItems().clear();
        for (final Repetition repetition : Repetition.values()) {
            this.comboBox.getItems().add(repetition.getName());
        }
        return this.comboBox;
    }

    public final ComboBox<String> getHourAndMinute(final int range) {
        this.comboBox.getItems().clear();
        for (int i = 00; i < range; i++) {
            if (i < 10) {
                this.comboBox.getItems().add("0" + i);
            } else {
                this.comboBox.getItems().add(String.valueOf(i));
            }
        }
        return this.comboBox;
    }

    public final ComboBox<String> getHotKey() {
        this.comboBox.getItems().clear();
        for (final HotKeyType type : HotKeyType.values()) {
            this.comboBox.getItems().add(type.getType());
        }
        return this.comboBox;
    }

}
