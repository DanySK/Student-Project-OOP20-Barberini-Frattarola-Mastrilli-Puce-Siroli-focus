package oop.focus.statistics.view;

import javafx.collections.FXCollections;
import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PieChartView {
    private static final double SPACING = 20;
    private final Pane container;
    private final CategoryAxis xAxis;
    private final NumberAxis yAxis;
    private final List<XYChart.Series<String, Number>> series;
    private final LineChart<String, Number> lineChart;
    private final NumberFormat format = new DecimalFormat("#.#E0");
    private final Label title;


    public PieChartView(final Dimension2D dimension) {
        VBox vBox = new VBox(SPACING);
        vBox.setAlignment(Pos.TOP_CENTER);
        this.container = vBox;
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();
        this.title = new Label("Titolo");
        this.series = new ArrayList<>();

        this.lineChart = new LineChart<>(this.xAxis, this.yAxis);
        this.lineChart.getData().addAll(this.series);
        this.container.getChildren().addAll(this.title, this.lineChart);
        this.setProperties(dimension);
    }

    private void setProperties(final Dimension2D dimension) {
        this.lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);
        this.xAxis.setAutoRanging(true);
        this.xAxis.setStartMargin(10);
        this.yAxis.setAutoRanging(true);
        this.xAxis.setAnimated(false);
        this.container.setPrefSize(dimension.getWidth(), dimension.getHeight());
    }

    public final void updateData(final List<Set<Pair<String, Double>>> items, final List<String> names) {
        this.series.clear();
        System.out.println("Data size ->" + items);
        items.forEach(i -> this.series.add(new XYChart.Series<>(
                FXCollections.observableList(i.stream()
                        .map(this::getPair)
                        .collect(Collectors.toList())))));
        this.lineChart.getData().addAll(this.series);

        for (int i = 0; i < names.size(); i++) {
            if (i < this.series.size()) {
                this.series.get(i).setName(names.get(i));
                System.out.println("Data size ->" + this.series.get(i).getData());
            }
        }
    }

    private XYChart.Data<String, Number> getPair(final Pair<String, Double> i) {
        try {
            return new XYChart.Data<>(i.getKey(), this.format.parse(i.getValue().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final void setXAxisName(final String name) {
        this.xAxis.setLabel(name);
    }

    public final void setYAxisName(final String name) {
        this.yAxis.setLabel(name);
    }

    public final void setTitle(final String name) {
        this.title.setText(name);
    }

    public final Pane getChart() {
        return this.container;
    }
}
