package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {
  private static final double[] MIN_INCOME = {0, 24680, 81080, 90000, 152540};
  private static final double[] MIN_TAXES = {0, 1320.38, 5296.58, 
                                             5996.80, 10906.19}; 
  private static final double[] BASIC_TAX_MODIFIER = {0.0535, 0.0705, 0.0785, 
                                                      0.0785, 0.0985};
    
  public SingleTaxpayer(String fullname, int taxRegistrationNumber, 
                        String status, float income) {
    super(fullname, taxRegistrationNumber, status, income, 
          MIN_INCOME, MIN_TAXES, BASIC_TAX_MODIFIER);
  }

}