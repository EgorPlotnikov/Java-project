import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.Color;
import java.util.Map;

public class Plot extends JFrame {

    public Plot(Map<String, Double> economyByCountry) {
        initUI(economyByCountry);
    }

    private void initUI(Map<String, Double> healthByCountry) {

        CategoryDataset dataset = createDataset(healthByCountry);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(Map<String, Double> economyByCountry) {
        var dataset = new DefaultCategoryDataset();
        economyByCountry.forEach((c, h) -> dataset.setValue(h, "Economy", c));
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Economy scores in countries",
                "Countries",
                "Economy score",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }
}