package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {
  private static final double[] MIN_INCOME = {0, 30390, 90000, 122110, 203390};
  private static final double[] MIN_TAXES = {0, 1625.87, 5828.38, 
                                             8092.13, 14472.61};
  private static final double[] BASIC_TAX_MODIFIER = {0.0535, 0.0705, 0.0705, 
                                                      0.0785, 0.0985};

  public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, 
                                 String status, float income) {
    super(fullname, taxRegistrationNumber, status, income, 
          MIN_INCOME, MIN_TAXES, BASIC_TAX_MODIFIER);
  }
}
