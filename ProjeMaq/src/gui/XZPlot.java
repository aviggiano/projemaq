package gui;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XZPlot extends JPanel {

	private static final long serialVersionUID = 1L;
	XYSeries series;
	
	public XZPlot(String title) {

        series = new XYSeries("Posição da Ferramenta");
        XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Posição da Ferramenta",
            "X", 
            "Z", 
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        this.add(chartPanel);
    }
	
	public void add (double x, double z) {
		series.add(x,z);
	}
}