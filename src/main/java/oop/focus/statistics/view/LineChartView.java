package oop.focus.statistics.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Line chart represents a line chart that draws a line
 * connecting the data points in a series.
 * The different series are inserted in an orderly manner.
 * The different points of each series are represented by a string (usually date or category)
 * and a numeric value.
 */
public class LineChartView implements MultiValueChart {
    private static final double SPACING = 0.005;
    private static final double XRATIO = 0.005;
    private static final double YRATIO = 0.005;
    private static final String SYMBOL_STYLE = "-fx-background-color: #%s, whitesmoke;";
    private static final String LYNE_STYLE = "-fx-stroke: #%s";
    private final NumberFormat format = new DecimalFormat("#.#E0");
    private final Pane container;
    private final CategoryAxis xAxis;
    private final NumberAxis yAxis;
    private final Label title;
    private final ObservableList<XYChart.Series<String, Number>> series;
    private final LineChart<String, Number> lineChart;

    /**
     * Instantiates a new Line chart view.
     */
    public LineChartView() {
        this.container = this.createBox();
        this.container.getStyleClass().addAll("chart-container");
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();
        this.title = new Label();
        this.series = FXCollections.observableArrayList();
        this.lineChart = new LineChart<>(this.xAxis, this.yAxis);
        this.lineChart.getData().addAll(this.series);
        this.container.getChildren().addAll(this.title, this.lineChart);
        this.setProperties();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateData(final List<List<Pair<String, Double>>> items) {
        this.lineChart.getData().clear();
        this.series.clear();
        items.forEach(i -> this.series.add(new XYChart.Series<>(
                FXCollections.observableList(i.stream()
                        .map(this::getPair)
                        .collect(Collectors.toList())))));
        this.lineChart.getData().addAll(this.series);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setNames(final List<String> names) {
        for (int i = 0; i < names.size(); i++) {
            if (i < this.series.size()) {
                this.series.get(i).setName(names.get(i));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setColors(final List<String> colors) {
        Platform.runLater(() -> {
            int i = 0;
            for (final var c : this.lineChart.getData()) {
                final String lineStyle = String.format(LYNE_STYLE, colors.get(i++));
                c.getNode().lookup(".chart-series-line").setStyle(lineStyle);
            }
            int index = 0;
            final String[] symbolStyles = new String[colors.size()];
            for (final var c : colors) {
                final String symbolStyle = String.format(SYMBOL_STYLE, c);
                symbolStyles[index] = symbolStyle;
                for (final var data : this.lineChart.getData().get(index).getData()) {
                    data.getNode().lookup(".chart-line-symbol").setStyle(symbolStyle);
                }
                index++;
            }

            for (final Node node : this.lineChart.lookupAll(".chart-legend-item-symbol")) {
                for (final String styleClass : node.getStyleClass()) {
                    if (styleClass.startsWith("series")) {
                        final int ind = Integer.parseInt(styleClass.substring(6));
                        node.setStyle(symbolStyles[ind]);
                        break;
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTitle(final String name) {
        this.title.setText(name);
    }

    private XYChart.Data<String, Number> getPair(final Pair<String, Double> i) {
        try {
            return new XYChart.Data<>(i.getKey(), this.format.parse(i.getValue().toString()));
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Pane createBox() {
        return ViewFactory.verticalWithPadding(SPACING, XRATIO, YRATIO);
    }

    private void setProperties() {
        this.lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);
        this.xAxis.setAutoRanging(true);
        this.xAxis.setGapStartAndEnd(true);
        this.yAxis.setAutoRanging(true);
        this.xAxis.setAnimated(false);
        this.lineChart.setHorizontalGridLinesVisible(false);
        this.lineChart.setVerticalGridLinesVisible(false);
        try {
            this.title.getStyleClass().add("title");
        } catch (final UnsupportedOperationException | NullPointerException
                | ClassCastException | IllegalStateException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
