package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public abstract class LogWriter{
  private String fileFormat;
  private String[] receiptKinds = {"Entertainment", "Basic", "Travel",
                                    "Health", "Other"};
  private String[] prefixes;
  private String[] suffixes;
  private int STARTING_INDEX = 8;
  protected TaxpayerManager taxpayerManager = new TaxpayerManager();
  
  
  public LogWriter(String fileFormat, String[] prefixes, String[] suffixes) {
    this.fileFormat = fileFormat;
    this.prefixes = prefixes;
    this.suffixes = suffixes;
  }
  
  public void generateLogFile(int taxRegistrationNumber) throws IOException {
    double taxpayerVariationTaxOnReceipts = 
        taxpayerManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber);
    PrintWriter outputStream = 
        new PrintWriter(new java.io.FileWriter(taxRegistrationNumber + fileFormat));    
    outputStream.println(prefixes[0] + 
                         taxpayerManager.getTaxpayerName(taxRegistrationNumber) + 
                         suffixes[0]);
    outputStream.println(prefixes[1] + 
                         taxRegistrationNumber + 
                         suffixes[1]);
    outputStream.println(prefixes[2] + 
                         taxpayerManager.getTaxpayerIncome(taxRegistrationNumber) + 
                         suffixes[2]);
    outputStream.println(prefixes[3] + 
                         taxpayerManager.getTaxpayerBasicTax(taxRegistrationNumber) + 
                         suffixes[3]);
    if (taxpayerVariationTaxOnReceipts > 0) {
      outputStream.println(prefixes[4] + 
                           taxpayerVariationTaxOnReceipts + 
                           suffixes[4]);       
    } else {
      outputStream.println(prefixes[5] +
                           taxpayerVariationTaxOnReceipts +
                           suffixes[5]);
    }
    outputStream.println(
        prefixes[6] +
        taxpayerManager.getTaxpayerTotalTax(taxRegistrationNumber) +
        suffixes[6]);
    outputStream.println(
        prefixes[7] + 
        taxpayerManager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) +
        suffixes[7]);
    for(int kind = 0; kind < receiptKinds.length; kind++) {
      outputStream.println(
          prefixes[STARTING_INDEX + kind] + 
          taxpayerManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, kind) +
          suffixes[STARTING_INDEX + kind]);
    } 
    outputStream.close();
  }
}
