package incometaxcalculator.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class TaxpayerWindow extends JFrame {

  private static final long serialVersionUID = 1L;
  JPanel taxpayerPanel;
  private WindowComponentsInitializer windowComponentsInitializer = 
      new WindowComponentsInitializer();
  
  public TaxpayerWindow(int taxRegistrationNumber, 
                        TaxpayerManager taxpayerManager) {
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 468, 510);
    taxpayerPanel = new JPanel();
    taxpayerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(taxpayerPanel);
    setTitle("TRN: " + taxRegistrationNumber);
    taxpayerPanel.setLayout(null);
    setLocationRelativeTo(null);
        
    JButton btnAddReceipt = 
        windowComponentsInitializer.initializeButton(taxpayerPanel, "Add Receipt",
                                              new int[]{0, 10, 131, 31}, true);    
    JButton btnDisplayReceipt = 
        windowComponentsInitializer.initializeButton(taxpayerPanel, "Display Receipt", 
                                             new int[]{135, 10, 163, 31}, false);    
    JButton btnDeleteReceipt = 
        windowComponentsInitializer.initializeButton(taxpayerPanel, "Delete Receipt",                                                  
                                             new int[]{301, 10, 153, 31}, false);    
    JButton btnGenerateCharts = 
        windowComponentsInitializer.initializeButton(taxpayerPanel, "Generate Charts",                                                 
                                              new int[]{49, 49, 163, 31}, true);    
    JButton btnExportLogFile = 
        windowComponentsInitializer.initializeButton(taxpayerPanel, "Export Log File",                                                  
                                              new int[]{222, 49, 163, 31}, true);

    windowComponentsInitializer.initializeTextPane(taxpayerPanel,  "Name :", 
                                                    new int[]{0, 90, 92, 28});
    windowComponentsInitializer.initializeTextPane(taxpayerPanel,  "TRN :", 
                                                    new int[]{0, 128, 92, 28});
    windowComponentsInitializer.initializeTextPane(taxpayerPanel,  "Status :", 
                                                    new int[]{0, 166, 92, 28});    
    windowComponentsInitializer.initializeTextPane(taxpayerPanel,  "Income :", 
                                                    new int[]{0, 204, 92, 28});
    windowComponentsInitializer.initializeTextPane(taxpayerPanel,  "Receipts :",  
                                                     new int[]{0, 242, 92, 28});
    
    windowComponentsInitializer.initializeTextArea(taxpayerPanel, 
                          taxpayerManager.getTaxpayerName(taxRegistrationNumber), 
                          new int[]{92, 90, 338, 28});
    windowComponentsInitializer.initializeTextArea(taxpayerPanel, 
                                                    taxRegistrationNumber + "", 
                                                    new int[]{92, 128, 338, 28});
    windowComponentsInitializer.initializeTextArea(taxpayerPanel,                         
                        taxpayerManager.getTaxpayerStatus(taxRegistrationNumber), 
                        new int[]{92, 166, 338, 28});
    windowComponentsInitializer.initializeTextArea(taxpayerPanel, 
                        taxpayerManager.getTaxpayerIncome(taxRegistrationNumber), 
                        new int[]{92, 204, 338, 28});
   
    DefaultListModel<Integer> receiptsModel = 
        new DefaultListModel<Integer>();
    JList<Integer> receiptsList = new JList<Integer>(receiptsModel);
    receiptsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    receiptsList.setFont(new Font("Tahoma", Font.PLAIN, 18));
    receiptsList.setBackground(new Color(153, 204, 204));
    receiptsList.setBounds(92, 242, 202, 206);
    taxpayerPanel.add(receiptsList);
    for ( Integer receiptId : 
            taxpayerManager.getReceiptHashMap(taxRegistrationNumber).keySet()) {
      receiptsModel.addElement(receiptId);
    }
    ListSelectionListener listSelectionListener = new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        List<Integer> selectedTaxpayer = receiptsList.getSelectedValuesList();
        if(selectedTaxpayer.size() == 0) {
          btnDisplayReceipt.setEnabled(false); 
          btnDeleteReceipt.setEnabled(false);           
        } else {
          btnDisplayReceipt.setEnabled(true);
          btnDeleteReceipt.setEnabled(true); 
        }
      }
    };
    receiptsList.addListSelectionListener(listSelectionListener); 
          
    
    btnAddReceipt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        AddReceiptWindow receiptWindow = 
              new AddReceiptWindow(taxRegistrationNumber, 
                                   receiptsModel, taxpayerManager);
        receiptWindow.setVisible(true);
      }
    });
    
    btnDisplayReceipt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        List<Integer> selectedReceipt = receiptsList.getSelectedValuesList();
        int receiptID = selectedReceipt.get(0);      
        if (windowComponentsInitializer.initializeConfirmDialogWindow(receiptID,
                                                "Display Receipt ID: ", "")) {
          DisplayReceiptWindow receiptWindow = 
              new DisplayReceiptWindow(taxRegistrationNumber, receiptID, 
                                       taxpayerManager);
          receiptWindow.setVisible(true);
        }
        
      }
    });
    
    btnDeleteReceipt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        List<Integer> selectedReceipt = receiptsList.getSelectedValuesList();
        int receiptID = selectedReceipt.get(0);
        if (windowComponentsInitializer.initializeConfirmDialogWindow(receiptID,
                                          "Delete Receipt ID: ", "Deletion")) {
          try {
            taxpayerManager.deleteReceipt(receiptID, taxRegistrationNumber);
            receiptsModel.removeElement(receiptID);
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(null,
                "Problem with opening file.");
          } catch (WrongFileFormatException | WrongFileEndingException e1) {
            e1.printStackTrace();
          }
        }
      }
    });
    
    btnGenerateCharts.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] receiptKinds = {"Entertainment", "Basic", "Travel", 
                                 "Health", "Other"};
        ChartsGenerator pieChartWindow = 
                    new ChartsGenerator(taxRegistrationNumber, taxpayerManager);
        pieChartWindow.generatePieChart(receiptKinds);
        pieChartWindow.generateBarChart();
      }     
    });
    
    btnExportLogFile.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LogChoiceWindow logChoiceWindow = 
            new LogChoiceWindow(taxRegistrationNumber, taxpayerManager);
        logChoiceWindow.setVisible(true);
      }
    });     
  }
}
