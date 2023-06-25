package incometaxcalculator.gui;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendLayout;
import org.knowm.xchart.style.Styler.LegendPosition;

import incometaxcalculator.data.management.TaxpayerManager;

class ChartsGenerator{
  private int taxRegistrationNumber;
  private TaxpayerManager taxpayerManager;
  
  public ChartsGenerator(int taxRegistrationNumber, 
                        TaxpayerManager taxpayerManager) {
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.taxpayerManager = taxpayerManager;
    
  }
  
  @SuppressWarnings("rawtypes")
  public void generatePieChart(String[] receiptKinds) {
      SwingWorker swingWorker = new SwingWorker() {        
          @SuppressWarnings("unchecked")
          @Override
          protected String doInBackground(){
            float totalAmountOfReceipts = taxpayerManager.
                getTaxpayerTotalAmountOfReceipts(taxRegistrationNumber);
            String title = "Percentage of Receipt Amount Per Category";
            PieChart chart = new PieChartBuilder().width(800).height(600)
                                             .title(title)
                                             .theme(ChartTheme.GGPlot2).build();
            
            chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
            chart.getStyler().setLegendLayout(LegendLayout.Horizontal);
            chart.getStyler().setLabelsDistance(1.15);
            chart.getStyler().setPlotContentSize(.7);
            chart.getStyler().setStartAngleInDegrees(90);
            chart.getStyler().setLegendFont(new Font("Tahoma", Font.PLAIN, 12));

            for (int kind = 0; kind < receiptKinds.length; kind++) {
              float totalAmountOfReceiptKind = taxpayerManager.
                    getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, kind);
              int receiptKindPercentage = 
                  (int) (100 * totalAmountOfReceiptKind / totalAmountOfReceipts);
              chart.addSeries(receiptKinds[kind], receiptKindPercentage);              
            }

            JFrame pieChartFrame = new JFrame("Pie Chart");
            pieChartFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JPanel chartPanel = new XChartPanel(chart);
            pieChartFrame.add(chartPanel);            
            pieChartFrame.pack();
            pieChartFrame.setLocationRelativeTo(null);
            pieChartFrame.setVisible(true);

            return "completed";
          }
      };      
      swingWorker.execute();
  }
  
  @SuppressWarnings("rawtypes")
  public void generateBarChart() {
      SwingWorker swingWorker = new SwingWorker() {        
          @SuppressWarnings("unchecked")
          @Override
          protected String doInBackground(){            
            String title = "Tax Details Bar Chart";
            String taxVariationName = "Tax Increase"; 
            double taxVariationValue = taxpayerManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber);

            CategoryChart chart = new CategoryChartBuilder().width(800).height(600).
                                  title(title).yAxisTitle("Tax analysis in $").
                                  theme(ChartTheme.GGPlot2).build();
 
            chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
            chart.getStyler().setLegendLayout(LegendLayout.Horizontal);
            chart.getStyler().setAvailableSpaceFill(.55);
            chart.getStyler().setOverlapped(true);
            
            if(taxVariationValue < 0) {
              taxVariationName = "Tax Decrease";
              taxVariationValue *= -1;
            }

            List<String> barNames = Arrays.asList("Basic Tax", taxVariationName, 
                                                  "Total Tax");
            List<Double> basicTaxBarValues = 
                Arrays.asList(taxpayerManager.getTaxpayerBasicTax(taxRegistrationNumber) , 
                              0.0 , 0.0);
            List<Double> taxVariationBarValues = 
                Arrays.asList(0.0 , taxVariationValue, 0.0);
            List<Double> totalTaxBarValues = 
                Arrays.asList(0.0 , 0.0, 
                              taxpayerManager.getTaxpayerTotalTax(taxRegistrationNumber));

            chart.addSeries("Basic Tax", barNames, basicTaxBarValues);
            chart.addSeries(taxVariationName, barNames, taxVariationBarValues);
            chart.addSeries("Total Tax", barNames, totalTaxBarValues);

            JFrame barChartFrame = new JFrame("Bar Chart");
            barChartFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JPanel chartPanel = new XChartPanel(chart);
            barChartFrame.add(chartPanel);            
            barChartFrame.pack();
            barChartFrame.setLocationRelativeTo(null);
            barChartFrame.setVisible(true);

            return "completed";
          }
      };      
      swingWorker.execute();
  }
}