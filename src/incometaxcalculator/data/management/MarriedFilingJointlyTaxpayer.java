package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {
  private static final double[] MIN_INCOME = {0, 36080, 90000, 143350, 254240};
  private static final double[] MIN_TAXES = {0, 1930.28, 5731.64,
                                             9492.82, 18197.69};
  private static final double[] BASIC_TAX_MODIFIER = {0.0535, 0.0705, 0.0705, 
                                                      0.0785, 0.985};

  public MarriedFilingJointlyTaxpayer(String fullname, int taxRegistrationNumber, 
                                      String status, float income) {
    super(fullname, taxRegistrationNumber, status, income, 
          MIN_INCOME, MIN_TAXES, BASIC_TAX_MODIFIER);
  }

}