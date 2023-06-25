package incometaxcalculator.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class WindowComponentsInitializer {
  
  public JButton initializeButton(JFrame frame, String buttonName, 
                                  int[] bounds, boolean flagEnable) {
    JButton btnTemp = new JButton(buttonName);
    btnTemp.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnTemp.setBackground(Color.WHITE);
    btnTemp.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    frame.getContentPane().add(btnTemp);
    btnTemp.setEnabled(flagEnable); 
    return btnTemp;    
  }

  public JButton initializeButton(JPanel panel, String buttonName, 
                                  int[] bounds, boolean flagEnable) {
    JButton btnTemp = new JButton(buttonName);
    btnTemp.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnTemp.setBackground(Color.WHITE);
    btnTemp.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    panel.add(btnTemp);
    btnTemp.setEnabled(flagEnable); 
    return btnTemp;       
  }

  public void initializeTextPane(JPanel panel, String textPaneName, int[] bounds) { 
    JTextPane txtpnTemp = new JTextPane();
    txtpnTemp.setText(textPaneName);
    txtpnTemp.setFont(new Font("Tahoma", Font.PLAIN, 18));
    txtpnTemp.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    panel.add(txtpnTemp);
    txtpnTemp.setEditable(false);
  }

  public JTextArea initializeTextArea(JPanel panel, String txtAreaContent, int[] bounds) { 
    JTextArea txtArea = new JTextArea();
    txtArea.setText(txtAreaContent);
    txtArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
    txtArea.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    panel.add(txtArea);
    txtArea.setEditable(false);
    return txtArea;
  }

  public boolean initializeConfirmDialogWindow(int id, String actionPhrase, 
                                               String actionNoun){
    JLabel confirmDialogText = new JLabel("<html>"+ actionPhrase + id +
         "<br/>Are you sure?</html>");
    confirmDialogText.setFont(new Font("Tahoma", Font.PLAIN, 18));
    int option = JOptionPane.showConfirmDialog(null, confirmDialogText, 
            "Confirm "+ actionNoun, 
            JOptionPane.YES_NO_OPTION);
    if(option == JOptionPane.YES_OPTION)
      return true;
    return false;
  }

  public JCheckBox initializeCheckBox(JPanel panel, String chckbxName, int[] bounds) {
    panel.setLayout(null);
    JCheckBox chckbxTemp = new JCheckBox(chckbxName);
    chckbxTemp.setFont(new Font("Tahoma", Font.PLAIN, 18));
    chckbxTemp.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    panel.add(chckbxTemp);
    return chckbxTemp;       
  }
}
