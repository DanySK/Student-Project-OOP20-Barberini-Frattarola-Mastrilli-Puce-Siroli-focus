package oop.focus.statistics.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * The type Pie chart represents a pies chart.
 * The chart content is populated by pie slices
 * based on input data set on the PieChart.
 */
public class PieChartView implements SingleValueChart {
    private static final double SPACING = 0.02;
    private static final double XRATIO = 0.02;
    private static final double YRATIO = 0.02;
    private static final double TICK_RATIO = 0.01;
    private static final String PIE_COLOR_STYLE = "-fx-pie-color: #%s;";
    private final Pane container;
    private final ObservableList<PieChart.Data> data;
    private final PieChart pieChart;
    private final NumberFormat format = new DecimalFormat("#.##");
    private final Label title;

    /**
     * Instantiates a new Pie chart view.
     */
    public PieChartView() {
        this.container = this.createBox();
        this.title = new Label();
        this.data = FXCollections.observableArrayList();
        this.pieChart = new PieChart(this.data);
        this.container.getChildren().addAll(this.title, this.pieChart);
        this.pieChart.setLabelLineLength(ViewFactoryImpl.SCREEN_BOUNDS.getHeight() * TICK_RATIO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Pane getRoot() {
        return this.container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateData(final List<Pair<String, Double>> items) {
        this.data.clear();
        double sum = items.stream().map(Pair::getValue).mapToDouble(Double::doubleValue).sum();
        items.forEach(i -> this.data.add(new PieChart.Data(i.getKey()
                + this.percentage(i.getValue(), sum),
                i.getValue())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setColors(final List<String> colors) {
        int ind = 0;
        for (String c : colors) {
            String style = String.format(PIE_COLOR_STYLE, c);
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
                    n.setStyle(String.format(PIE_COLOR_STYLE, colors.get(i)));
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

    private String percentage(final Double value, final double sum) {
        return " " + this.format.format((value / sum) * 100) + "%";
    }

    private Pane createBox() {
        return ViewFactory.verticalWithPadding(SPACING, XRATIO, YRATIO);
    }
}
