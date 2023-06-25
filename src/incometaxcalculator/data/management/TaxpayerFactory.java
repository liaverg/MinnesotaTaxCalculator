package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerFactory {
  
  public static Taxpayer createTaxpayer(String fullname, 
                                        int taxRegistrationNumber, String status,
                                        float income) 
                                            throws WrongTaxpayerStatusException {
    if (status.equals("Married Filing Jointly")) {
      return new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, 
                                              status, income);
    } else if (status.equals("Married Filing Separately")) {
      return new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber,
                                                 status, income);
    } else if (status.equals("Single")) {
      return new SingleTaxpayer(fullname, taxRegistrationNumber, 
                                status, income);
    } else if (status.equals("Head of Household")) {
      return new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, 
                                         status, income);
    } else {
      throw new WrongTaxpayerStatusException();
    }
  }
}