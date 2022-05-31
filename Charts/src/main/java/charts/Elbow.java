package charts;

import org.jfree.chart.*;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.util.Args;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.BiFunction;

public class Elbow {
    private static final String NAME = "Elbow";

    public static void main(String[] args) {
        var m = new Elbow();
        m.run();
    }

    private void run() {
        var panel = createGraph();
    }

    private XYSeries makeSeries(String label) {
        var series = new XYSeries(label);

        series.add(1, 1);
        series.add(2, 1.1);
        series.add(4, 1.2);
        series.add(6, 1.3);
        series.add(8, 4);
        series.add(10, 15);

        return series;
    }

    private JPanel createGraph() {
        var panel = new JPanel();

        var dataset = new XYSeriesCollection();
        dataset.addSeries(makeSeries("Observed Scaling"));

        var chart = createXYLineChart(
                "Performance Elbow",
                "Load",
                "Response time (ms)",
                dataset,
                PlotOrientation.VERTICAL
        );
        chart.getXYPlot().setRenderer(new XYSplineRenderer());

        SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
        Rectangle r = new Rectangle(0, 0, 600, 400);
        chart.draw(g2, r);
        File f = new File(NAME + ".svg");
        try {
            SVGUtils.writeToSVG(f, g2.getSVGElement());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        var chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);

        // PNG
        OutputStream out = null;
        try {
            out = new FileOutputStream(NAME + ".png");
            ChartUtils.writeChartAsPNG(out,
                    chart,
                    512,
                    512);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return panel;
    }

    private static JFreeChart createXYLineChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation) {
        Args.nullNotPermitted(orientation, "orientation");
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);

        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartTheme currentTheme = new StandardChartTheme("JFree");
        currentTheme.apply(chart);
        return chart;
    }

}
