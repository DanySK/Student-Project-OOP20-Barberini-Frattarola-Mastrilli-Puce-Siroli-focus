package oop.focus.diary.view;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import oop.focus.common.View;
import java.util.List;

public class GeneralCounterView implements View {
    private final GridPane gridPane;
    public GeneralCounterView(final List<List<Node>> list) {
        this.gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        int index = 0;
        for (final List<Node> l : list) {
            for (final Node node : l) {
                GridPane.setHgrow(node, Priority.ALWAYS);
                GridPane.setVgrow(node, Priority.ALWAYS);
                this.gridPane.addRow(index, node);
            }
            index++;
        }
    }


    @Override
    public final Node getRoot() {
        return this.gridPane;
    }
}
