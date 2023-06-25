package incometaxcalculator.data.management;

import java.io.IOException;
import java.util.HashMap;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.FileReaderFactory;
import incometaxcalculator.data.io.InfoWriter;
import incometaxcalculator.data.io.InfoWriterFactory;
import incometaxcalculator.data.io.LogWriter;
import incometaxcalculator.data.io.LogWriterFactory;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManager {
  private static HashMap<Integer, Taxpayer> taxpayerHashMap = 
                 new HashMap<Integer, Taxpayer>(0);
  private static HashMap<Integer, String> infoFileHashMap = 
      new HashMap<Integer, String>(0);
  
  public void loadTaxpayer(String filePath)
                            throws NumberFormatException, IOException, 
                            WrongFileFormatException, WrongFileEndingException, 
                            WrongTaxpayerStatusException, 
                            WrongReceiptKindException, WrongReceiptDateException {
    int taxRegistrationNumber = getTaxRegistrationNumber(filePath);
    FileReader reader = FileReaderFactory.createFileReader(getFileFormat(filePath));
    reader.readFile(filePath);
    infoFileHashMap.put(taxRegistrationNumber, filePath);
  }
   
  public void createTaxpayer(String fullname, int taxRegistrationNumber,
                             String status, float income) 
                                 throws WrongTaxpayerStatusException {    
    taxpayerHashMap.put(taxRegistrationNumber,  
                        TaxpayerFactory.createTaxpayer(fullname, 
                                                       taxRegistrationNumber, 
                                                       status, income));
  }
  
  public void addReceipt(int receiptId, String issueDate, float amount, 
                         String kind, String companyName, String country, 
                         String city, String street, int number,
                         int taxRegistrationNumber) 
                             throws IOException, WrongReceiptKindException,
                             WrongReceiptDateException, 
                             ReceiptAlreadyExistsException, 
                             WrongFileFormatException, WrongFileEndingException {
    if (containsReceipt(receiptId, taxRegistrationNumber)) {
      throw new ReceiptAlreadyExistsException();
    }
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, 
                  city, street, number, taxRegistrationNumber);
    updateInfoFile(taxRegistrationNumber);
}

  public void createReceipt(int receiptId, String issueDate, float amount, 
                            String kind, String companyName, String country, 
                            String city, String street, int number,
                            int taxRegistrationNumber) 
                                throws WrongReceiptDateException, 
                                      WrongReceiptKindException {
    Receipt receipt = new Receipt(receiptId, issueDate, amount, kind, 
                                  companyName, country, city, street, number);
    taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
  }

  public void deleteTaxpayer(int taxRegistrationNumber) {
    taxpayerHashMap.remove(taxRegistrationNumber);
    infoFileHashMap.remove(taxRegistrationNumber);
  }

  public void deleteReceipt(int receiptId, int taxRegistrationNumber) 
                            throws IOException, WrongFileFormatException, 
                            WrongFileEndingException {
    taxpayerHashMap.get(taxRegistrationNumber).deleteReceipt(receiptId);
    updateInfoFile(taxRegistrationNumber);
  }
  
  private void updateInfoFile(int taxRegistrationNumber) 
                                  throws IOException, WrongFileFormatException, 
                                  WrongFileEndingException {
    String filePath = getInfoFilePath(taxRegistrationNumber);
    InfoWriter writer = InfoWriterFactory.createInfoWriter(getFileFormat(filePath));
    writer.generateInfoFile(taxRegistrationNumber);
  }
   
  public void exportLogFile(int taxRegistrationNumber, String fileFormat)
      throws IOException, WrongFileFormatException {
    LogWriter writer = LogWriterFactory.createLogWriter(fileFormat);
    writer.generateLogFile(taxRegistrationNumber);
  }

  public boolean containsTaxpayer(int taxRegistrationNumber) {
    return taxpayerHashMap.containsKey(taxRegistrationNumber);
  }
  
  public boolean containsReceipt(int receiptId, int taxRegistrationNumber) {
    return getReceiptHashMap(taxRegistrationNumber).containsKey(receiptId);
  }
  
  public int getTaxRegistrationNumber(String filePath) {
    return Integer.valueOf(
        filePath.substring(filePath.length() - 18, filePath.length() - 9));
  }
  
  public String getFileFormat(String filePath) {
    return filePath.substring(filePath.length() - 3);
  }
  
  public String getInfoFilePath(int taxRegistrationNumber) {
    return infoFileHashMap.get(taxRegistrationNumber);
  }

  public Taxpayer getTaxpayer(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber);
  }
   
  public String getTaxpayerName(int taxRegistrationNumber) {
    return getTaxpayer(taxRegistrationNumber).getFullname();
  }

  public String getTaxpayerStatus(int taxRegistrationNumber) {
    return "" + getTaxpayer(taxRegistrationNumber).getStatus();
  }

  public String getTaxpayerIncome(int taxRegistrationNumber) {
    return "" + getTaxpayer(taxRegistrationNumber).getIncome();
  }
  
  public int getTaxpayerTotalReceiptsGathered(int taxRegistrationNumber) {
    return getTaxpayer(taxRegistrationNumber).getTotalReceiptsGathered();
  }
  
  public float getTaxpayerTotalAmountOfReceipts(int taxRegistrationNumber) {
    return getTaxpayer(taxRegistrationNumber).getTotalAmountOfReceipts();
  }

  public float getTaxpayerAmountOfReceiptKind(int taxRegistrationNumber, int kind) {
    return getTaxpayer(taxRegistrationNumber).getAmountOfReceiptKind(kind);
  }
  
  public double getTaxpayerTotalTax(int taxRegistrationNumber) {
    return getTaxpayer(taxRegistrationNumber).getTotalTax();
  }

  public double getTaxpayerBasicTax(int taxRegistrationNumber) {
    return getTaxpayer(taxRegistrationNumber).getBasicTax();
  }

  public double getTaxpayerVariationTaxOnReceipts(int taxRegistrationNumber) {
    return getTaxpayer(taxRegistrationNumber).getVariationTaxOnReceipts();
  }

  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    return getTaxpayer(taxRegistrationNumber).getReceiptHashMap();
  }
  
  public Receipt getReceipt(int taxRegistrationNumber, int receiptId) {
    return getTaxpayer(taxRegistrationNumber).getReceiptHashMap().get(receiptId);
  }
  
  public int getReceiptId(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getId();
  }
  
  public String getReceiptIssueDate(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getIssueDate();
  }
  
  public float getReceiptAmount(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getAmount();
  }
  
  public String getReceiptKind(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getKind();
  }
  
  public String getReceiptCompanyName(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getCompanyName();
  }
  
  public String getReceiptCompanyCounty(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getCompanyCountry();
  }
  
  public String getReceiptCompanyCity(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getCompanyCity();
  }
  
  public String getReceiptCompanyStreet(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getCompanyStreet();
  }
  
  public int getReceiptCompanyNumber(int taxRegistrationNumber, int receiptId) {
    return getReceipt(taxRegistrationNumber, receiptId).getCompanyNumber();
  }
}