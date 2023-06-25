package testing;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.FileReaderFactory;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class FileReaderTest {

  private static FileReader txtFileReader;
  private static FileReader xmlFileReader;
  private static TaxpayerManager taxpayerManager;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    txtFileReader = FileReaderFactory.createFileReader("txt");
    xmlFileReader = FileReaderFactory.createFileReader("xml");
    taxpayerManager = new TaxpayerManager();
  }
  

  @Test
  public void testTXTReadFile() 
      throws IOException, WrongReceiptDateException, WrongReceiptKindException, 
      WrongFileFormatException, WrongTaxpayerStatusException, WrongFileEndingException{
    txtFileReader.readFile(".//junitResources//123456780_INFO.txt");
    
    assertEquals(taxpayerManager.getTaxpayerName(123456780), "Apostolos Zarras");
    assertEquals(taxpayerManager.getTaxpayerStatus(123456780), "Married Filing Jointly");
    assertEquals(taxpayerManager.getTaxpayerIncome(123456780), "22570.0");

    assertEquals(taxpayerManager.getReceiptId(123456780, 1), 1);
    assertEquals(taxpayerManager.getReceiptIssueDate(123456780, 1), "10/5/1996");
    assertEquals(taxpayerManager.getReceiptAmount(123456780, 1), 251.0, 0);
    assertEquals(taxpayerManager.getReceiptKind(123456780, 1), "Basic");
    assertEquals(taxpayerManager.getReceiptCompanyName(123456780, 1), "Parta");
    assertEquals(taxpayerManager.getReceiptCompanyCounty(123456780, 1), "Jakusa");
    assertEquals(taxpayerManager.getReceiptCompanyCity(123456780, 1), "Drama");
    assertEquals(taxpayerManager.getReceiptCompanyStreet(123456780, 1), "Fukosima");
    assertEquals(taxpayerManager.getReceiptCompanyNumber(123456780, 1), 5);
    
    assertEquals(taxpayerManager.getReceiptId(123456780, 6), 6);
    assertEquals(taxpayerManager.getReceiptIssueDate(123456780, 6), "11/12/2021");
    assertEquals(taxpayerManager.getReceiptAmount(123456780, 6), 4535.0, 0);
    assertEquals(taxpayerManager.getReceiptKind(123456780, 6), "Basic");
    assertEquals(taxpayerManager.getReceiptCompanyName(123456780, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyCounty(123456780, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyCity(123456780, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyStreet(123456780, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyNumber(123456780, 6), 4);
    }
  
  @Test
  public void testXMLReadFile() 
      throws IOException, WrongReceiptDateException, WrongReceiptKindException, 
      WrongFileFormatException, WrongTaxpayerStatusException, WrongFileEndingException{
    xmlFileReader.readFile(".//junitResources//123456700_INFO.xml");    
   
    assertEquals(taxpayerManager.getTaxpayerName(123456700), "Apostolos Zarras");
    assertEquals(taxpayerManager.getTaxpayerStatus(123456700), "Married Filing Jointly");
    assertEquals(taxpayerManager.getTaxpayerIncome(123456700), "22570.0");

    assertEquals(taxpayerManager.getReceiptId(123456700, 1), 1);
    assertEquals(taxpayerManager.getReceiptIssueDate(123456700, 1), "10/5/1996");
    assertEquals(taxpayerManager.getReceiptAmount(123456700, 1), 251.0, 0);
    assertEquals(taxpayerManager.getReceiptKind(123456700, 1), "Basic");
    assertEquals(taxpayerManager.getReceiptCompanyName(123456700, 1), "Parta");
    assertEquals(taxpayerManager.getReceiptCompanyCounty(123456700, 1), "Jakusa");
    assertEquals(taxpayerManager.getReceiptCompanyCity(123456700, 1), "Drama");
    assertEquals(taxpayerManager.getReceiptCompanyStreet(123456700, 1), "Fukosima");
    assertEquals(taxpayerManager.getReceiptCompanyNumber(123456700, 1), 5);
    
    assertEquals(taxpayerManager.getReceiptId(123456700, 6), 6);
    assertEquals(taxpayerManager.getReceiptIssueDate(123456700, 6), "11/12/2021");
    assertEquals(taxpayerManager.getReceiptAmount(123456700, 6), 4535.0, 0);
    assertEquals(taxpayerManager.getReceiptKind(123456700, 6), "Basic");
    assertEquals(taxpayerManager.getReceiptCompanyName(123456700, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyCounty(123456700, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyCity(123456700, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyStreet(123456700, 6), "wer");
    assertEquals(taxpayerManager.getReceiptCompanyNumber(123456700, 6), 4);
    }
}
