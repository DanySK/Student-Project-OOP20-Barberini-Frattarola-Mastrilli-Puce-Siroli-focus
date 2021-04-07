package oop.focus.statistics.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import oop.focus.common.View;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class PieChartView implements View {
    private static final double SPACING = 20;
    private final Pane container;
    private final ObservableList<PieChart.Data> data;
    private final PieChart pieChart;
    private final NumberFormat format = new DecimalFormat("#.#E0");
    private final Label title;

    public PieChartView() {
        VBox vBox = new VBox(SPACING);
        vBox.setAlignment(Pos.TOP_CENTER);
        this.container = vBox;
        this.title = new Label("Titolo");
        this.data = FXCollections.observableArrayList();
        this.pieChart = new PieChart(this.data);
        this.container.getChildren().addAll(this.title, this.pieChart);
    }

    public final void updateData(final List<Pair<String, Double>> items) {
        this.data.clear();
        items.forEach(i -> this.data.add(new PieChart.Data(i.getKey(), i.getValue())));
    }

    public final void setColors(final List<String> colors) {
        int ind = 0;
        for (String c : colors) {
            String style = "-fx-pie-color: #" + c + ";";
            System.out.println(style);
            this.pieChart.getData().get(ind++).getNode().setStyle(style);
        }
        Platform.runLater(() -> {
            for (int i = 0; i < this.pieChart.getData().size(); i++) {
                PieChart.Data d = this.pieChart.getData().get(i);
                String colorClass = "";
                for (String cls : d.getNode().getStyleClass()) {
                    if (cls.startsWith("default-color")) {
                        colorClass = cls;
                        break;
                    }
                }
                for (var n : this.pieChart.lookupAll("." + colorClass)) {
                    n.setStyle("-fx-pie-color: #" + colors.get(i));
                }
            }
        });
    }

    public final void setTitle(final String name) {
        this.title.setText(name);
    }

    @Override
    public final Pane getRoot() {
        return this.container;
    }
}
