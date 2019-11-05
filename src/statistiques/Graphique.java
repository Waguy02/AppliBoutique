/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistiques;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author ESDRAS
 */
public class Graphique {

    public JFreeChart genererBarChart(Hashtable<String, Integer> dict, String titre, String labelX, String labelY) {
        //generer les barchart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key : dict.keySet()) {
            dataset.setValue(dict.get(key), labelY, key);
        }
        JFreeChart chart = ChartFactory.createBarChart(titre,
                labelX, labelY, dataset, PlotOrientation.VERTICAL,
                false, true, false);
        return chart;
    }

    public JFreeChart genererPieChart(Hashtable<String, Integer> dict, String titre) {
        //generer les pieChart
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (String key : dict.keySet()) {
            pieDataset.setValue(key, dict.get(key));
        }
        JFreeChart chart = ChartFactory.createPieChart(titre, // Title
                pieDataset, // Dataset
                true, // Show legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        return chart;
    }

    public JFreeChart genererTimeSeries(Hashtable<Day, Integer> dict, String titre, String labelX, String labelY) {
        //crée un chart temporel
        TimeSeries pop = new TimeSeries(labelY, Day.class);
        for (Day key : dict.keySet()) {
            pop.add(key, dict.get(key));
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(pop);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                titre,
                labelX,
                labelY,
                dataset,
                true,
                true,
                false);
        
        return chart;
    }

    public JFreeChart genererTimeSeries(Hashtable<Day, Integer> dict, Hashtable<Day, Integer> dict2, String titre, String labelX, String labelY, String legend1, String legend2) {
        //crée un chart temporel
        TimeSeries pop = new TimeSeries(legend1, Day.class);
        for (Day key : dict.keySet()) {
            pop.add(key, dict.get(key));
        }
        TimeSeries pop2 = new TimeSeries(legend2, Day.class);
        for (Day key : dict2.keySet()) {
            pop2.add(key, dict2.get(key));
        }
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(pop);
        dataset.addSeries(pop2);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                titre,
                labelX,
                labelY,
                dataset,
                true,
                true,
                false);
        
        return chart;
    }
    
    public void genererImg(JFreeChart chart, String nomFichier, int dimX, int dimY) {
        //ne mettre que les images jpg
        try {
            ChartUtilities.saveChartAsJPEG(new File(nomFichier), chart, dimX, dimY);
        } catch (IOException e) {
            System.err.println("Un problème s'est posé lors de la création de l'image");
        }
    }

}
