package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {

    private XYSeries humiditySeries = new XYSeries("Humidité");
    private XYSeries indoorTemperatureSeries = new XYSeries("Temperature Intérieur");
    private XYSeries outdoorTemperatureSeries = new XYSeries("Temperature Exterieur");
    private XYSeries dewPointSeries = new XYSeries("Point de rosée");

    public GraphFrame() {
        setTitle("Graphique en temps réel");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créez des séries et ajoutez-les à la collection
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(humiditySeries);
        dataset.addSeries(indoorTemperatureSeries);
        dataset.addSeries(outdoorTemperatureSeries);
        dataset.addSeries(dewPointSeries);

        // Créez un graphique en fonction du dataset
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graphique en temps réel",
                "Temps",
                "Grandeur physique",
                dataset,  // Utilisez XYSeriesCollection ici
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Créez un panneau de graphique et ajoutez-le à l'interface utilisateur
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphFrame graphFrame = new GraphFrame();
            graphFrame.setVisible(true);
        });
    }
    public void updateChartData(float humidity, float indoorTemperature, float outdoorTemperature, float dewPoint) {
        humiditySeries.addOrUpdate(System.currentTimeMillis(), humidity);
        indoorTemperatureSeries.addOrUpdate(System.currentTimeMillis(), indoorTemperature);
        outdoorTemperatureSeries.addOrUpdate(System.currentTimeMillis(), outdoorTemperature);
        dewPointSeries.addOrUpdate(System.currentTimeMillis(), dewPoint);
    }}

