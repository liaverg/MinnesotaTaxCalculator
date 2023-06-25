package incometaxcalculator.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import incometaxcalculator.data.management.TaxpayerManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayReceiptWindow extends JFrame {

  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel = new JPanel(); 
  private WindowComponentsInitializer windowComponentsInitializer = 
      new WindowComponentsInitializer();  

  public DisplayReceiptWindow(int taxRegistrationNumber, 
                              int receiptId,
                              TaxpayerManager taxpayerManager) {
    setResizable(false);
    setBounds(100, 100, 465, 432);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    setTitle("TRN: " + taxRegistrationNumber + 
             " - Receipt ID: " + receiptId);
    contentPanel.setLayout(null);
    setLocationRelativeTo(null);
     
 
    windowComponentsInitializer.initializeTextPane(contentPanel, "ID :", 
                                                   new int[]{0, 10, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "Date :", 
                                                   new int[]{0, 48, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "Amount :", 
                                                   new int[]{0, 86, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "Kind :", 
                                                   new int[]{0, 124, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "Company :", 
                                                   new int[]{0, 162, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "Country :", 
                                                   new int[]{0, 200, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "City :", 
                                                   new int[]{0, 238, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "Street :", 
                                                   new int[]{0, 276, 102, 28});
    windowComponentsInitializer.initializeTextPane(contentPanel, "Number :", 
                                                   new int[]{0, 314, 102, 28});
    
    

    windowComponentsInitializer.initializeTextArea(contentPanel, 
        String.valueOf(taxpayerManager.getReceiptId(taxRegistrationNumber, receiptId)), 
        new int[]{99, 10, 338, 28});    
    windowComponentsInitializer.initializeTextArea(contentPanel, 
        taxpayerManager.getReceiptIssueDate(taxRegistrationNumber, receiptId), 
        new int[]{99, 48, 338, 28});
    windowComponentsInitializer.initializeTextArea(contentPanel,
        String.valueOf(taxpayerManager.getReceiptAmount(taxRegistrationNumber, receiptId)), 
        new int[]{99, 86, 338, 28});
    windowComponentsInitializer.initializeTextArea(contentPanel, 
        taxpayerManager.getReceiptKind(taxRegistrationNumber, receiptId), 
        new int[]{99, 124, 338, 28});
    windowComponentsInitializer.initializeTextArea(contentPanel, 
        taxpayerManager.getReceiptCompanyName(taxRegistrationNumber, receiptId), 
        new int[]{99, 162, 338, 28});
    windowComponentsInitializer.initializeTextArea(contentPanel, 
        taxpayerManager.getReceiptCompanyCounty(taxRegistrationNumber, receiptId), 
        new int[]{99, 200, 338, 28});
    windowComponentsInitializer.initializeTextArea(contentPanel, 
        taxpayerManager.getReceiptCompanyCity(taxRegistrationNumber, receiptId), 
        new int[]{99, 238, 338, 28});
    windowComponentsInitializer.initializeTextArea(contentPanel, 
        taxpayerManager.getReceiptCompanyStreet(taxRegistrationNumber, receiptId), 
        new int[]{99, 276, 338, 28});
    windowComponentsInitializer.initializeTextArea(contentPanel, 
        String.valueOf(taxpayerManager.getReceiptCompanyNumber(taxRegistrationNumber, receiptId)), 
        new int[]{99, 314, 338, 28});
    
    JButton btnClose = 
        windowComponentsInitializer.initializeButton(contentPanel, "Close", 
                                            new int[]{168, 352, 131, 31}, true);

    btnClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
  }
}
