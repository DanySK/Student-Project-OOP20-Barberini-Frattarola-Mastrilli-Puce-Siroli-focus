package oop.focus.statistics.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import oop.focus.common.View;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


public class LineChartView implements View {
    private static final double SPACING = 20;
    private final Pane container;
    private final CategoryAxis xAxis;
    private final NumberAxis yAxis;
    private final ObservableList<XYChart.Series<String, Number>> series;
    private final LineChart<String, Number> lineChart;
    private final NumberFormat format = new DecimalFormat("#.#E0");
    private final Label title;


    public LineChartView() {
        VBox vBox = new VBox(SPACING);
        vBox.setAlignment(Pos.TOP_CENTER);
        this.container = vBox;
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();
        this.title = new Label("Titolo");
        this.series = FXCollections.observableArrayList();
        this.lineChart = new LineChart<>(this.xAxis, this.yAxis);
        this.lineChart.getData().addAll(this.series);
        this.container.getChildren().addAll(this.title, this.lineChart);
        this.setProperties();
    }

    private void setProperties() {
        this.lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);
        this.xAxis.setAutoRanging(true);
        this.xAxis.setStartMargin(10);
        this.xAxis.setGapStartAndEnd(true);
        this.yAxis.setAutoRanging(true);
        this.xAxis.setAnimated(false);
        this.lineChart.setHorizontalGridLinesVisible(false);
        this.lineChart.setVerticalGridLinesVisible(false);
    }

    public final void updateData(final List<List<Pair<String, Double>>> items, final List<String> names) {
        this.lineChart.getData().clear();
        this.series.clear();
        items.forEach(i -> this.series.add(new XYChart.Series<>(
                FXCollections.observableList(i.stream()
                        .map(this::getPair)
                        .collect(Collectors.toList())))));
        this.lineChart.getData().addAll(this.series);

        for (int i = 0; i < names.size(); i++) {
            if (i < this.series.size()) {
                this.series.get(i).setName(names.get(i));
            }
        }
    }

    public final void setColors(final List<String> colors) {
        int i = 0;
        for (var c : this.lineChart.getData()) {
            final String lineStyle = "-fx-stroke: #" + colors.get(i);
            c.getNode().lookup(".chart-series-line").setStyle(lineStyle);
        }
        final String[] symbolStyles = new String[colors.size()];

        int index = 0;
        for (var c : colors) {
            final String symbolStyle = String.format("-fx-background-color: #%s, whitesmoke;", c);
            symbolStyles[index] = symbolStyle;
            for (var data : this.lineChart.getData().get(index).getData()) {
                data.getNode().lookup(".chart-line-symbol").setStyle(symbolStyle);
            }
            index++;
        }
        Platform.runLater(() -> {
            for (Node node : this.lineChart.lookupAll(".chart-legend-item-symbol")) {
                for (String styleClass : node.getStyleClass()) {
                    if (styleClass.startsWith("series")) {
                        final int ind = Integer.parseInt(styleClass.substring(6));
                        node.setStyle(symbolStyles[ind]);
                        break;
                    }
                }
            }
        });
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

    @Override
    public final Node getRoot() {
        return this.container;
    }
}
