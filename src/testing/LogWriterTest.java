package testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import incometaxcalculator.data.io.LogWriter;
import incometaxcalculator.data.io.LogWriterFactory;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;


public class LogWriterTest {
  
  private static LogWriter txtLogWriter;
  private static LogWriter xmlLogWriter;
  private static TaxpayerManager taxpayerManager;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    txtLogWriter = LogWriterFactory.createLogWriter("txt");
    xmlLogWriter = LogWriterFactory.createLogWriter("xml");
    taxpayerManager = new TaxpayerManager();
    taxpayerManager.createTaxpayer("Apostolos Zarras", 123456780, 
        "Married Filing Jointly" , (float)22570.0);

    taxpayerManager.createReceipt(1, "10/5/1996", (float)251.0, "Basic",
    "Parta", "Jakusa", "Drama", "Fukosima", 5, 123456780);
    
    taxpayerManager.createReceipt(6, "12/12/2021", (float)4535.0, "Basic",
    "wer", "wer", "wer", "wer", 4, 123456780);

  }

  @Test
  public void testGenerateTXTLogFile() 
      throws WrongTaxpayerStatusException, WrongReceiptKindException, 
      WrongReceiptDateException,  IOException, NumberFormatException,
      WrongFileFormatException {
    txtLogWriter.generateLogFile(123456780);
    
    BufferedReader inputStream = new BufferedReader(
        new java.io.FileReader("123456780_LOG.txt"));
    assertEquals(inputStream.readLine(), "Name: Apostolos Zarras");
    assertEquals(inputStream.readLine(), "AFM: 123456780");
    assertEquals(inputStream.readLine(), "Income: 22570.0");
    assertEquals(inputStream.readLine(), "BasicTax: 1207.495");
    assertEquals(inputStream.readLine(), "TaxIncrease: 48.2998");
    assertEquals(inputStream.readLine(), "TotalTax: 1255.7948");
    assertEquals(inputStream.readLine(), "Receipts: 2");
    assertEquals(inputStream.readLine(), "Entertainment: 0.0");
    assertEquals(inputStream.readLine(), "Basic: 4786.0");
    assertEquals(inputStream.readLine(), "Travel: 0.0");
    assertEquals(inputStream.readLine(), "Health: 0.0");
    assertEquals(inputStream.readLine(), "Other: 0.0");
    inputStream.close();
  }
  
  @Test
  public void testGenerateXMLLogFile() 
      throws WrongTaxpayerStatusException, WrongReceiptKindException, 
      WrongReceiptDateException,  IOException, NumberFormatException,
      WrongFileFormatException {
    xmlLogWriter.generateLogFile(123456780);    
   
    BufferedReader inputStream = new BufferedReader(
        new java.io.FileReader("123456780_LOG.xml"));
    assertEquals(inputStream.readLine(), "<Name> Apostolos Zarras </Name> ");
    assertEquals(inputStream.readLine(), "<AFM> 123456780 </AFM> ");
    assertEquals(inputStream.readLine(), "<Income> 22570.0 </Income> ");
    assertEquals(inputStream.readLine(), "<BasicTax> 1207.495 </BasicTax> ");
    assertEquals(inputStream.readLine(), "<TaxIncrease> 48.2998 </TaxIncrease> ");
    assertEquals(inputStream.readLine(), "<TotalTax> 1255.7948 </TotalTax> ");
    assertEquals(inputStream.readLine(), "<Receipts> 2 </Receipts> ");
    assertEquals(inputStream.readLine(), "<Entertainment> 0.0 </Entertainment> ");
    assertEquals(inputStream.readLine(), "<Basic> 4786.0 </Basic> ");
    assertEquals(inputStream.readLine(), "<Travel> 0.0 </Travel> ");
    assertEquals(inputStream.readLine(), "<Health> 0.0 </Health> ");
    assertEquals(inputStream.readLine(), "<Other> 0.0 </Other> ");
    inputStream.close();
  }

}
