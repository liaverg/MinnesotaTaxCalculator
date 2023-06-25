package testing;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerFactory;

public class TaxpayerTest {
  private static Taxpayer singleTaxpayer;
  private static Taxpayer marriedFillingJointlyTaxpayer;
  private static Taxpayer marriedFillingSeparatelyTaxpayer;
  private static Taxpayer headOfHouseholdTaxpayer;

  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception{
    singleTaxpayer = 
        TaxpayerFactory.createTaxpayer("John Single", 123456789,
                                       "Single", 85000);
    marriedFillingJointlyTaxpayer = 
        TaxpayerFactory.createTaxpayer("John Jointly", 123456780, 
                                      "Married Filing Jointly", 85000);
    marriedFillingSeparatelyTaxpayer = 
        TaxpayerFactory.createTaxpayer("John Separately", 123456700, 
                                       "Married Filing Separately", 85000);
    headOfHouseholdTaxpayer = 
        TaxpayerFactory.createTaxpayer("John Head", 123456000, 
                                       "Head of Household", 85000);
  }  
 
  @Test
  public void testCalculateBasicTax() {
    double resultSingleTaxpayer = singleTaxpayer.calculateBasicTax();
    double resultMarriedFillingJointlyTaxpayer = 
                           marriedFillingJointlyTaxpayer.calculateBasicTax();
    double resultMarriedFillingSeparatelyTaxpayer = 
                           marriedFillingSeparatelyTaxpayer.calculateBasicTax();
    double resultHeadOfHouseholdTaxpayer = 
                           headOfHouseholdTaxpayer.calculateBasicTax();
    assertEquals(resultSingleTaxpayer, 5296.58 + 0.0785 * (85000 - 81080), 0);
    assertEquals(resultMarriedFillingJointlyTaxpayer, 1930.28 + 0.0705 * (85000 - 36080), 0);
    assertEquals(resultMarriedFillingSeparatelyTaxpayer, 4746.76 + 0.0785 * (85000 - 71680), 0);
    assertEquals(resultHeadOfHouseholdTaxpayer, 1625.87 + 0.0705 * (85000 - 30390), 0);
  }
 }
