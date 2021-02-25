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
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.util.Args;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.function.BiFunction;

public class Main {
    private static BiFunction<Double, Integer, Double> AMDAHL =
            (serial, n) -> 1 / (serial + (1 - serial) / n);

    public static void main(String[] args) {
        var m = new Main();
        m.run();
    }

    private void run() {
        var panel = createGraph();

//        JFreeChart chart = createChart(createDataset());
    }

    private XYSeries makeSeries(double serial, String label) {
        var series = new XYSeries(label);

        for (var cpus = 1; cpus <= 512; cpus = cpus * 2) {
            series.add(cpus, AMDAHL.apply(serial, cpus));
        }

        return series;
    }

    private JPanel createGraph() {
        var panel = new JPanel();

        var dataset = new XYSeriesCollection();
        dataset.addSeries(makeSeries(0.02, "2% serial"));
        dataset.addSeries(makeSeries(0.05, "5% serial"));
        dataset.addSeries(makeSeries(0.10, "10% serial"));
        dataset.addSeries(makeSeries(0.25, "25% serial"));

        var chart = createXYLineChart(
                "Amdahl's Law",
                "CPUs used",
                "Speedup",
                dataset,
                PlotOrientation.VERTICAL
        );
        chart.getXYPlot().setRenderer(new XYSplineRenderer());

        SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
        Rectangle r = new Rectangle(0, 0, 600, 400);
        chart.draw(g2, r);
        File f = new File("Amdahl.svg");
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
            out = new FileOutputStream("Amdahl.png");
            ChartUtils.writeChartAsPNG(out,
                    chart,
                    512,
                    512);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return panel;
    }

    public static JFreeChart createXYLineChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation) {
        Args.nullNotPermitted(orientation, "orientation");
        NumberAxis xAxis = new LogarithmicAxis(xAxisLabel);
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
