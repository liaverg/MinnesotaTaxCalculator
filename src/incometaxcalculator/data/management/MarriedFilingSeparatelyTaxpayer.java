package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {
  private static final double[] MIN_INCOME = {0, 18040, 71680, 90000, 127120};
  private static final double[] MIN_TAXES = {0, 965.14, 4746.76, 
                                             6184.88, 9098.80};  
  private static final double[] BASIC_TAX_MODIFIER = {0.0535, 0.0705, 0.0785, 
                                                      0.0785, 0.0985};

  public MarriedFilingSeparatelyTaxpayer(String fullname, 
                                         int taxRegistrationNumber, 
                                         String status, float income) {
    super(fullname, taxRegistrationNumber, status, income, 
          MIN_INCOME, MIN_TAXES, BASIC_TAX_MODIFIER);
  }
}