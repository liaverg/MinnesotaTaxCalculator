package incometaxcalculator.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class MainWindow {

  private JFrame mainFrame;
  private TaxpayerManager taxpayerManager = new TaxpayerManager();
  private JTextField loadedTaxpayersTitle;
  private WindowComponentsInitializer windowComponentsInitializer = 
      new WindowComponentsInitializer();


  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          } catch (ClassNotFoundException | InstantiationException | 
                   IllegalAccessException | UnsupportedLookAndFeelException ex) {
             ex.printStackTrace();
          }
          MainWindow window = new MainWindow();
          window.mainFrame.setLocationRelativeTo(null);
          window.mainFrame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public MainWindow() {
    initialize();
  }
  
  private void initialize() {
    mainFrame = new JFrame("Minessota Income Tax Calculator");
    mainFrame.setResizable(false);
    mainFrame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
    mainFrame.getContentPane().setLayout(null); 

    JButton btnLoadTaxpayer = 
        windowComponentsInitializer.initializeButton(mainFrame, "Load Taxpayer", 
                                             new int[]{10, 10, 163, 31}, true);    
    JButton btnSelectTaxpayer = 
        windowComponentsInitializer.initializeButton(mainFrame, "Select Taxpayer",
                                             new int[]{175, 10, 163, 31}, false);    
    JButton btnDeleteTaxpayer = 
        windowComponentsInitializer.initializeButton(mainFrame, "Delete Taxpayer",
                                             new int[]{343, 10, 163, 31}, false);
    
    loadedTaxpayersTitle = new JTextField();
    loadedTaxpayersTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
    loadedTaxpayersTitle.setText("Taxpayers' Registration Numbers:");
    loadedTaxpayersTitle.setEditable(false);
    loadedTaxpayersTitle.setBackground(Color.LIGHT_GRAY);
    loadedTaxpayersTitle.setBounds(20, 51, 475, 28);
    mainFrame.getContentPane().add(loadedTaxpayersTitle);
    loadedTaxpayersTitle.setColumns(10);
    loadedTaxpayersTitle.setBackground(new Color(153, 204, 204));
    
    JSeparator separator = new JSeparator();
    separator.setBackground(Color.BLACK);
    separator.setForeground(Color.BLACK);
    separator.setBounds(20, 79, 475, 2);
    mainFrame.getContentPane().add(separator);
    
    DefaultListModel<Integer> loadedTaxpayersModel = 
        new DefaultListModel<Integer>();
    JList <Integer> loadedTaxpayersList =  new JList <Integer>(loadedTaxpayersModel);
    loadedTaxpayersList.setFont(new Font("Tahoma", Font.PLAIN, 18));
    loadedTaxpayersList.setBounds(20, 81, 475, 237);
    loadedTaxpayersList.setBackground(new Color(153, 204, 204));
    mainFrame.getContentPane().add(loadedTaxpayersList);
    mainFrame.setBounds(100, 100, 527, 388);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loadedTaxpayersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
         
    ListSelectionListener listSelectionListener = new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        List<Integer> selectedTaxpayer = loadedTaxpayersList.getSelectedValuesList();
        if(selectedTaxpayer.size() == 0) {
          btnSelectTaxpayer.setEnabled(false); 
          btnDeleteTaxpayer.setEnabled(false);           
        } else {
          btnSelectTaxpayer.setEnabled(true);
          btnDeleteTaxpayer.setEnabled(true); 
        }
      }
    };
    loadedTaxpayersList.addListSelectionListener(listSelectionListener);        
    
    btnLoadTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                              "TXT & XML Files", "txt", "xml");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new java.io.File("."));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
          String filePath = fileChooser.getSelectedFile().getAbsolutePath();
          int taxRegistrationNumber = 
              taxpayerManager.getTaxRegistrationNumber(filePath);
          try {
            if (taxpayerManager.containsTaxpayer(taxRegistrationNumber)) {
              JOptionPane.showMessageDialog(null,
                  "This taxpayer is already loaded from the following file: \n" +
                  taxpayerManager.getInfoFilePath(taxRegistrationNumber));
            } else {
              taxpayerManager.loadTaxpayer(filePath);
              loadedTaxpayersModel.addElement(taxRegistrationNumber);
            }
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,                
                "The tax registration number must have only digits.");
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "The file doesn't exists."); 
          } catch (WrongFileFormatException e1) {
            JOptionPane.showMessageDialog(null, 
                "Please check your file format and try again.");
          } catch (WrongFileEndingException e1) {
            JOptionPane.showMessageDialog(null, 
                "Please check your file ending and try again.");
          } catch (WrongTaxpayerStatusException e1) {
            JOptionPane.showMessageDialog(null, 
                "Please check taxpayer's status and try again.");
          } catch (WrongReceiptKindException e1) {
            JOptionPane.showMessageDialog(null, 
                "Please check receipts kind and try again.");
          } catch (WrongReceiptDateException e1) {
            JOptionPane.showMessageDialog(null,
                 "Please make sure your date is " + "DD/MM/YYYY and try again.");
          }
        }
      }
    });    
    
    btnSelectTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        List<Integer> selectedTaxpayer = loadedTaxpayersList.getSelectedValuesList();
        int taxRegistrationNumber = selectedTaxpayer.get(0);
        if (windowComponentsInitializer.initializeConfirmDialogWindow(taxRegistrationNumber, 
                                          "Select Taxpayer TRN: ", "Selection")) {
              TaxpayerWindow taxpayerManagementWindow = 
                     new TaxpayerWindow(taxRegistrationNumber, taxpayerManager);
              taxpayerManagementWindow.setVisible(true);
        }
      }
    });      
    
    btnDeleteTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        List<Integer> selectedTaxpayer = loadedTaxpayersList.getSelectedValuesList();
        int taxRegistrationNumber = selectedTaxpayer.get(0);
        if (windowComponentsInitializer.initializeConfirmDialogWindow(taxRegistrationNumber, 
                                          "Delete Taxpayer TRN: ", "Deletion")) {
          taxpayerManager.deleteTaxpayer(taxRegistrationNumber);
          loadedTaxpayersModel.removeElement(taxRegistrationNumber);
        }
      }
    });    
  }
}
