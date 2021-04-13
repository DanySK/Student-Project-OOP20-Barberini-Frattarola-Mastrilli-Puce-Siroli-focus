package oop.focus.diary.view;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Set;

/**
 * This interface can be used to set the icons in the appropriate grid and the "modify" button.
 */
public interface DailyMoodView {

    Set<ImageView> getIcons();

}
