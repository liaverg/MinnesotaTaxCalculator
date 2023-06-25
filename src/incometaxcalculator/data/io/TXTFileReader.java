package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {

  protected int getReceiptId(String[] receiptIdLineArray) {
      if (receiptIdLineArray[0].equals("Receipt")) {
        if (receiptIdLineArray[1].equals("ID:")) {
          int receiptId = Integer.parseInt(receiptIdLineArray[2].trim());
          return receiptId;
        }
      }
    return -1;
  }

  protected String getValueOfField(String fieldsLine) 
      throws WrongFileFormatException {
    try {
      return fieldsLine.split(" ", 2)[1].trim();
    } catch (NullPointerException e) {
      throw new WrongFileFormatException();
    }
  }
}