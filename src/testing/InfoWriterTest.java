package testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import incometaxcalculator.data.io.InfoWriter;
import incometaxcalculator.data.io.InfoWriterFactory;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class InfoWriterTest {

  private static InfoWriter txtInfoWriter;
  private static InfoWriter xmlInfoWriter;
  private static TaxpayerManager taxpayerManager;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    txtInfoWriter = InfoWriterFactory.createInfoWriter("txt");
    xmlInfoWriter = InfoWriterFactory.createInfoWriter("xml");
    taxpayerManager = new TaxpayerManager();
  }
  
  
  @Test
  public void testgenerateTXTInfoFile() 
      throws WrongReceiptDateException, WrongReceiptKindException, IOException, 
      NumberFormatException, WrongFileFormatException, WrongFileEndingException, 
      WrongTaxpayerStatusException {
    taxpayerManager.loadTaxpayer(".//junitResources//123456000_INFO.txt");
    taxpayerManager.createReceipt(2, "12/12/2015", (float)15.0, "Basic",
        "wer", "wer", "wer", "wer", 12, 123456000);
    txtInfoWriter.generateInfoFile(123456000);
    
    BufferedReader inputStream = new BufferedReader(
        new java.io.FileReader(".//junitResources//123456000_INFO.txt"));  
    assertEquals(inputStream.readLine(),"Name: Apostolos Zarras");
    assertEquals(inputStream.readLine(),"AFM: 123456000");
    assertEquals(inputStream.readLine(),"Status: Married Filing Jointly");
    assertEquals(inputStream.readLine(),"Income: 22570.0");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"Receipts:");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"Receipt ID: 1");
    assertEquals(inputStream.readLine(),"Date: 10/5/1996");
    assertEquals(inputStream.readLine(),"Kind: Basic");
    assertEquals(inputStream.readLine(),"Amount: 251.0");
    assertEquals(inputStream.readLine(),"Company: Parta");
    assertEquals(inputStream.readLine(),"Country: Jakusa");
    assertEquals(inputStream.readLine(),"City: Drama");
    assertEquals(inputStream.readLine(),"Street: Fukosima");
    assertEquals(inputStream.readLine(),"Number: 5");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"Receipt ID: 6");
    assertEquals(inputStream.readLine(),"Date: 11/12/2021");
    assertEquals(inputStream.readLine(),"Kind: Basic");
    assertEquals(inputStream.readLine(),"Amount: 4535.0");
    assertEquals(inputStream.readLine(),"Company: wer");
    assertEquals(inputStream.readLine(),"Country: wer");
    assertEquals(inputStream.readLine(),"City: wer");
    assertEquals(inputStream.readLine(),"Street: wer");
    assertEquals(inputStream.readLine(),"Number: 4");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"Receipt ID: 2");
    assertEquals(inputStream.readLine(),"Date: 12/12/2015");
    assertEquals(inputStream.readLine(),"Kind: Basic");
    assertEquals(inputStream.readLine(),"Amount: 15.0");
    assertEquals(inputStream.readLine(),"Company: wer");
    assertEquals(inputStream.readLine(),"Country: wer");
    assertEquals(inputStream.readLine(),"City: wer");
    assertEquals(inputStream.readLine(),"Street: wer");
    assertEquals(inputStream.readLine(),"Number: 12");    
    inputStream.close();   
  }  
    
    
  @Test
  public void testgenerateXMLInfoFile() 
      throws WrongReceiptDateException, WrongReceiptKindException, IOException, 
      NumberFormatException, WrongFileFormatException, WrongFileEndingException, 
      WrongTaxpayerStatusException {
    taxpayerManager.loadTaxpayer(".//junitResources//123450000_INFO.xml");
    taxpayerManager.createReceipt(2, "12/12/2015", (float)15.0, "Basic",
        "wer", "wer", "wer", "wer", 12, 123450000);
    xmlInfoWriter.generateInfoFile(123450000);  
         
    BufferedReader inputStream = new BufferedReader(
        new java.io.FileReader(".//junitResources//123450000_INFO.xml"));  
    assertEquals(inputStream.readLine(),"<Name> Apostolos Zarras </Name> ");
    assertEquals(inputStream.readLine(),"<AFM> 123450000 </AFM> ");
    assertEquals(inputStream.readLine(),"<Status> Married Filing Jointly </Status> ");
    assertEquals(inputStream.readLine(),"<Income> 22570.0 </Income> ");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"<Receipts>");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"<ReceiptID> 1 </ReceiptID> ");
    assertEquals(inputStream.readLine(),"<Date> 10/5/1996 </Date> ");
    assertEquals(inputStream.readLine(),"<Kind> Basic </Kind> ");
    assertEquals(inputStream.readLine(),"<Amount> 251.0 </Amount> ");
    assertEquals(inputStream.readLine(),"<Company> Parta </Company> ");
    assertEquals(inputStream.readLine(),"<Country> Jakusa </Country> ");
    assertEquals(inputStream.readLine(),"<City> Drama </City> ");
    assertEquals(inputStream.readLine(),"<Street> Fukosima </Street> ");
    assertEquals(inputStream.readLine(),"<Number> 5 </Number> ");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"<ReceiptID> 6 </ReceiptID> ");
    assertEquals(inputStream.readLine(),"<Date> 11/12/2021 </Date> ");
    assertEquals(inputStream.readLine(),"<Kind> Basic </Kind> ");
    assertEquals(inputStream.readLine(),"<Amount> 4535.0 </Amount> ");
    assertEquals(inputStream.readLine(),"<Company> wer </Company> ");
    assertEquals(inputStream.readLine(),"<Country> wer </Country> ");
    assertEquals(inputStream.readLine(),"<City> wer </City> ");
    assertEquals(inputStream.readLine(),"<Street> wer </Street> ");
    assertEquals(inputStream.readLine(),"<Number> 4 </Number> ");
    assertEquals(inputStream.readLine(),"");
    assertEquals(inputStream.readLine(),"<ReceiptID> 2 </ReceiptID> ");
    assertEquals(inputStream.readLine(),"<Date> 12/12/2015 </Date> ");
    assertEquals(inputStream.readLine(),"<Kind> Basic </Kind> ");
    assertEquals(inputStream.readLine(),"<Amount> 15.0 </Amount> ");
    assertEquals(inputStream.readLine(),"<Company> wer </Company> ");
    assertEquals(inputStream.readLine(),"<Country> wer </Country> ");
    assertEquals(inputStream.readLine(),"<City> wer </City> ");
    assertEquals(inputStream.readLine(),"<Street> wer </Street> ");
    assertEquals(inputStream.readLine(),"<Number> 12 </Number> ");
    inputStream.close();   
  }
}