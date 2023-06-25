package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public abstract class InfoWriter{
  private String[] taxpayerPrefixes;
  private String[] taxpayerSuffixes;
  private String[] receiptPrefixes;
  private String[] receiptSuffixes;
  private TaxpayerManager taxpayerManager = new TaxpayerManager();
  
  public InfoWriter(String[] taxpayerPrefixes, String[] taxpayerSuffixes, 
                    String[] receiptPrefixes, String[] receiptSuffixes) {
    this.taxpayerPrefixes = taxpayerPrefixes;
    this.taxpayerSuffixes = taxpayerSuffixes;
    this.receiptPrefixes= receiptPrefixes;
    this.receiptSuffixes = receiptSuffixes;
  }
  
  public void generateInfoFile(int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = 
        new PrintWriter(new java.io.FileWriter(
                         taxpayerManager.getInfoFilePath(taxRegistrationNumber)));
    outputStream.println(taxpayerPrefixes[0] + 
                         taxpayerManager.getTaxpayerName(taxRegistrationNumber) + 
                         taxpayerSuffixes[0]);
    outputStream.println(taxpayerPrefixes[1] + 
                         taxRegistrationNumber + 
                         taxpayerSuffixes[1]);
    outputStream.println(taxpayerPrefixes[2] + 
                         taxpayerManager.getTaxpayerStatus(taxRegistrationNumber) + 
                         taxpayerSuffixes[2]);
    outputStream.println(taxpayerPrefixes[3] + 
                         taxpayerManager.getTaxpayerIncome(taxRegistrationNumber) + 
                         taxpayerSuffixes[3]);
    outputStream.println("");
    outputStream.println(taxpayerPrefixes[4]);
    outputStream.println("");
    generateTaxpayerReceipts(taxRegistrationNumber, taxpayerManager, outputStream);
    outputStream.close();
  }
  
  private void generateTaxpayerReceipts(int taxRegistrationNumber, 
                                        TaxpayerManager taxpayerManager, 
                                        PrintWriter outputStream) {
    for ( Integer receiptId : 
          taxpayerManager.getReceiptHashMap(taxRegistrationNumber).keySet()) {
      outputStream.println(
          receiptPrefixes[0] + 
          taxpayerManager.getReceiptId(taxRegistrationNumber, receiptId) + 
          receiptSuffixes[0]);
      outputStream.println(
          receiptPrefixes[1] + 
          taxpayerManager.getReceiptIssueDate(taxRegistrationNumber, receiptId) +
          receiptSuffixes[1]);
      outputStream.println(
          receiptPrefixes[2] + 
          taxpayerManager.getReceiptKind(taxRegistrationNumber, receiptId) +
          receiptSuffixes[2]);
      outputStream.println(
          receiptPrefixes[3] + 
          taxpayerManager.getReceiptAmount(taxRegistrationNumber, receiptId) +
          receiptSuffixes[3]);
      outputStream.println(
          receiptPrefixes[4] + 
          taxpayerManager.getReceiptCompanyName(taxRegistrationNumber, receiptId) +
          receiptSuffixes[4]);
      outputStream.println(
          receiptPrefixes[5] + 
          taxpayerManager.getReceiptCompanyCounty(taxRegistrationNumber, receiptId) +
          receiptSuffixes[5]);
      outputStream.println(
          receiptPrefixes[6]+ 
          taxpayerManager.getReceiptCompanyCity(taxRegistrationNumber, receiptId) +
          receiptSuffixes[6]);
      outputStream.println(
          receiptPrefixes[7] + 
          taxpayerManager.getReceiptCompanyStreet(taxRegistrationNumber, receiptId) +
          receiptSuffixes[7]);
      outputStream.println(
          receiptPrefixes[8] + 
          taxpayerManager.getReceiptCompanyNumber(taxRegistrationNumber, receiptId) +
          receiptSuffixes[8]);
      outputStream.println("");
    }
  }
}
