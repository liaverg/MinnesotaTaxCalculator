package incometaxcalculator.gui;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class AddReceiptWindow extends JDialog {

  private static final long serialVersionUID = 1L;
  private final JPanel contentPanel = new JPanel();
  private WindowComponentsInitializer windowComponentsInitializer = 
      new WindowComponentsInitializer();
  
  private JTextArea initializeTextArea(String txtAreaPrompt, int[] bounds) { 
    JTextArea txtArea = new JTextArea();
    txtArea.setText((String) null);
    txtArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
    txtArea.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    contentPanel.add(txtArea);      
    PromptSupport.setPrompt(txtAreaPrompt, txtArea);
    PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, 
                                    txtArea);
    return txtArea;
  } 
 
  public AddReceiptWindow(int taxRegistrationNumber,                          
                          DefaultListModel<Integer> receiptsModel,
                          TaxpayerManager taxpayerManager) {
    setResizable(false);
      setBounds(100, 100, 497, 442);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      setTitle("TRN: " + taxRegistrationNumber + 
               " - Add Receipt");
      contentPanel.setLayout(null);
      setLocationRelativeTo(null); 
      setModal(true);
      
      windowComponentsInitializer.initializeTextPane(contentPanel, "ID :", 
                                                      new int[]{10, 10, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "Date :", 
                                                      new int[]{10, 48, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "Amount :", 
                                                      new int[]{10, 86, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "Kind :", 
                                                      new int[]{10, 124, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "Company :", 
                                                      new int[]{10, 162, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "Country :", 
                                                      new int[]{10, 200, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "City :", 
                                                      new int[]{10, 238, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "Street :", 
                                                      new int[]{10, 276, 102, 28});
      windowComponentsInitializer.initializeTextPane(contentPanel, "Number :", 
                                                      new int[]{10, 314, 102, 28});

      JTextArea receiptID = initializeTextArea("Enter ID Number", 
                                                new int[]{134, 10, 338, 28});
      JTextArea receiptDate = initializeTextArea("dd/MM/yyyy", 
                                                new int[]{134, 48, 338, 28});
      JTextArea receiptAmount = initializeTextArea("Enter Receipt's Amount", 
                                                new int[]{134, 86, 338, 28});
      JTextArea receiptCompany = initializeTextArea("Enter Company's Name", 
                                                new int[]{134, 162, 338, 28});
      JTextArea receiptCountry = initializeTextArea("Enter Company's Country", 
                                                new int[]{134, 200, 338, 28});      
      JTextArea receiptCity = initializeTextArea("Enter Company's City", 
                                                new int[]{134, 238, 338, 28});      
      JTextArea receiptStreet = initializeTextArea("Enter Company's Street", 
                                                new int[]{134, 276, 338, 28});
      JTextArea receiptNumber = initializeTextArea("Enter Company's Number", 
                                                new int[]{134, 314, 338, 28});
      
      DefaultComboBoxModel<String> kindModel = 
          new DefaultComboBoxModel <String>(
              new String[] {"Select Kind", "Entertainment", "Basic", "Travel", 
                            "Health", "Other"}); 
      JComboBox<String> kindComboBox = new JComboBox<String>();
      kindComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
      kindComboBox.setModel(kindModel);
      kindComboBox.setBackground(Color.WHITE);
      kindComboBox.setBounds(134, 124, 338, 28);
      contentPanel.add(kindComboBox);     
      
      JButton btnAddReceipt = 
          windowComponentsInitializer.initializeButton(contentPanel, "Add Receipt", 
                                             new int[]{114, 362, 131, 31}, true);
      JButton btnCancel = 
          windowComponentsInitializer.initializeButton(contentPanel, "Cancel", 
                                              new int[]{255, 362, 131, 31}, true); 
      
        
      btnAddReceipt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {          
          int receiptIDValue = Integer.parseInt(receiptID.getText());
          String dateValue = receiptDate.getText();
          String kindValue = kindComboBox.getSelectedItem().toString();
          float amountValue = Float.parseFloat(receiptAmount.getText());
          String companyValue = receiptCompany.getText();
          String countryValue = receiptCountry.getText();
          String cityValue = receiptCity.getText();
          String streetValue = receiptStreet.getText();
          int numberValue = Integer.parseInt(receiptNumber.getText());
            try {
              taxpayerManager.addReceipt(receiptIDValue, dateValue, amountValue, 
                                         kindValue, companyValue, countryValue, 
                                         cityValue, streetValue, numberValue,
                                         taxRegistrationNumber);
              receiptsModel.addElement(receiptIDValue);
              dispose(); 
            } catch (IOException e1) {
              JOptionPane.showMessageDialog(null,
                  "Problem with opening file.");
            } catch (WrongReceiptKindException e1) {
              JOptionPane.showMessageDialog(null, 
                  "Please check receipts kind and try again.");
            } catch (WrongReceiptDateException e1) {
              JOptionPane.showMessageDialog(null,
                  "Please make sure your date " + "is DD/MM/YYYY and try again.");
            } catch (ReceiptAlreadyExistsException e1) {
              JOptionPane.showMessageDialog(null, "Receipt ID already exists.");
            } catch (WrongFileFormatException e1) {
              e1.printStackTrace();
            } catch (WrongFileEndingException e1) {
              e1.printStackTrace();
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
