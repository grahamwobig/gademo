import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

/**
 * Graph class for displaying output of function
 */
public class LineChart01 implements ExampleChart<XYChart> {

    public static void main(String[] arg) throws IOException {
        LineChart01 exampleChart = new LineChart01();
        XYChart chart = exampleChart.getChartNew();
        new SwingWrapper<XYChart>(chart).displayChart();
        BitmapEncoder.saveBitmapWithDPI(chart, "./sample300_DPI", BitmapFormat.PNG, 300);
    }

    public XYChart getChartNew() {

        // generates Log data
        List<Double> xData = new ArrayList<Double>();
        List<Double> yData = new ArrayList<Double>();
        for (int i = -3; i <= 3; i++) {
            double u = (double) i;
            double u2 = u + 0.5;
            xData.add(u);
            if (u != 0) {
                yData.add(u * Math.exp((-1 * u)));
            } else {
                yData.add(u);
            }
            xData.add(u2);
            if (u2 != 0) {
                yData.add(u2 * Math.exp((-1 * u2)));
            } else {
                yData.add(u2);
            }
        }

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Genetic Algorithim Demo")
                .xAxisTitle("f(x) = x . e ^ ( -x)").yAxisTitle("Value").theme(ChartTheme.Matlab).build();

        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);
        // Customize Chart
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        // chart.getStyler().setYAxisLogarithmic(true);
        chart.getStyler().setXAxisLabelRotation(45);

        // chart.getStyler().setXAxisLabelAlignment(TextAlignment.Right);
        // chart.getStyler().setXAxisLabelRotation(90);
        // chart.getStyler().setXAxisLabelRotation(0);

        // Series
        XYSeries series = chart.addSeries("Calculation", xData, yData);
        series.setMarker(SeriesMarkers.NONE);

        return chart;
    }

}