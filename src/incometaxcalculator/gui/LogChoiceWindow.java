package incometaxcalculator.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

public class LogChoiceWindow extends JDialog {
 
  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel = new JPanel();
  private String fileFormat;
  private WindowComponentsInitializer windowComponentsInitializer = 
      new WindowComponentsInitializer();  
  
  public LogChoiceWindow(int taxRegistrationNumber,  
                         TaxpayerManager taxpayerManager) {
    setBounds(100, 100, 353, 300);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    setTitle("TRN: " + taxRegistrationNumber);
    contentPanel.setLayout(null);
    setLocationRelativeTo(null);
    setModal(true);
    
    JLabel lblExplanation = new JLabel("Choose the Log's File Format");
    lblExplanation.setFont(new Font("Tahoma", Font.PLAIN, 18));
    lblExplanation.setBounds(57, 10, 243, 31);
    contentPanel.add(lblExplanation);
    
    JCheckBox chckbxTxt = windowComponentsInitializer.initializeCheckBox(contentPanel, 
                                            "Txt", new int[]{129, 67, 112, 31});   
    JCheckBox chckbxXML = windowComponentsInitializer.initializeCheckBox(contentPanel, 
                                            "XML", new int[]{129, 108, 66, 31});
    
    chckbxTxt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chckbxXML.setSelected(false);
        fileFormat = "txt";
      }
    });

    chckbxXML.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chckbxTxt.setSelected(false);
        fileFormat = "xml";
      }
    });

    JButton btnExportLogFile = 
        windowComponentsInitializer.initializeButton(contentPanel, "Export Log File", 
                                              new int[]{10, 168, 310, 31}, true);
    JButton btnCancel = 
        windowComponentsInitializer.initializeButton(contentPanel, "Cancel", 
                                              new int[]{97, 215, 131, 31}, true);
    
    
    btnExportLogFile.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!chckbxTxt.isSelected() && !chckbxXML.isSelected()) {
          JOptionPane.showMessageDialog(null, 
              "Please choose a file format.");
        } else {
          try {
            taxpayerManager.exportLogFile(taxRegistrationNumber, fileFormat);
            dispose();
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(null,
                "Problem with opening file ." + taxRegistrationNumber + 
                "_LOG." + fileFormat);
          } catch (WrongFileFormatException e1) {
            JOptionPane.showMessageDialog(null, "Wrong file format");
          }
        }
      }
    });
    
    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
  }
}
