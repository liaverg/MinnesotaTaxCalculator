package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public abstract class FileReader {
  TaxpayerManager taxpayerManager = new TaxpayerManager();

  protected abstract int getReceiptId(String[] values);

  protected abstract String getValueOfField(String fieldsLine) 
                                           throws WrongFileFormatException;
  
  public void readFile(String fileName)
        throws NumberFormatException, IOException, WrongTaxpayerStatusException,
        WrongFileFormatException, WrongReceiptKindException, 
        WrongReceiptDateException {
    BufferedReader inputStream = 
                   new BufferedReader(new java.io.FileReader(fileName));
    String fullname = getValueOfField(inputStream.readLine());
    int taxRegistrationNumber = 
                   Integer.parseInt(getValueOfField(inputStream.readLine()));
    String status = getValueOfField(inputStream.readLine());
    float income = Float.parseFloat(getValueOfField(inputStream.readLine()));
    taxpayerManager.createTaxpayer(fullname, taxRegistrationNumber, 
                                   status, income);
    while (readReceipt(inputStream, taxRegistrationNumber));
  }

  protected boolean readReceipt(BufferedReader inputStream, 
                                int taxRegistrationNumber)
                                  throws WrongFileFormatException, IOException, 
                                  WrongReceiptKindException,
                                  WrongReceiptDateException {
    int receiptId = checkForReceiptId(inputStream);
    if (receiptId < 0) {
      return false;
    }
    String issueDate = getValueOfField(inputStream.readLine());
    String kind = getValueOfField(inputStream.readLine());
    float amount = Float.parseFloat(getValueOfField(inputStream.readLine()));
    String companyName = getValueOfField(inputStream.readLine());
    String country = getValueOfField(inputStream.readLine());
    String city = getValueOfField(inputStream.readLine());
    String street = getValueOfField(inputStream.readLine());
    int number = Integer.parseInt(getValueOfField(inputStream.readLine()));
    taxpayerManager.createReceipt(receiptId, issueDate, amount, kind,
                                  companyName, country, city, street, 
                                  number, taxRegistrationNumber);
    return true;
  }
 
  protected int checkForReceiptId(BufferedReader inputStream) 
                                    throws NumberFormatException, IOException{
    String line;
    while (!isEmpty(line = inputStream.readLine())) {
      String[] receiptIdLineArray = line.split(" ", 3);
      int receiptId = getReceiptId(receiptIdLineArray);
      if(receiptId != -1) 
        return receiptId;
    }
    return -1;
  }

  protected boolean isEmpty(String line) {
    return line == null;
  }

}