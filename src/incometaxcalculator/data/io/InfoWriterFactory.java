package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileEndingException;

public class InfoWriterFactory {
  
  public static InfoWriter createInfoWriter(String fileFormat) 
                                            throws WrongFileEndingException { 
    if (fileFormat.equals("txt")) {
      return new TXTInfoWriter();
    } else if (fileFormat.equals("xml")) {
      return new XMLInfoWriter();
    } else {
      throw new WrongFileEndingException();
    }
  }    
}
