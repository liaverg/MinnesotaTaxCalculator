package incometaxcalculator.data.management;

import java.util.HashMap;


public abstract class Taxpayer {
  protected final String fullname;
  protected final int taxRegistrationNumber;
  private String status; 
  protected final float income;
  private double[] minIncome;
  private double[] minTaxes;
  private double[] basicTaxModifier;
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = 
          new HashMap<Integer, Receipt>(0);
  private float[] amountPerReceiptsKind = new float[5];
  private String[] receiptKinds = {"Entertainment", "Basic", "Travel", 
                                    "Health", "Other"};
  private static final double[] VARIATION_INCOME_PERCENTAGE = {0, 0.2, 
                                                               0.4, 0.6};
  private static final double[] VARIATION_TAX_MODIFIER = {0.08, 0.04, 
                                                         -0.15, -0.3};

  protected Taxpayer(String fullname, int taxRegistrationNumber, String status, 
                     float income, double[] minIncome, double[] minTaxes, 
                     double[] basicTaxModifier) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.status = status;
    this.income = income;
    this.minIncome = minIncome;
    this.minTaxes = minTaxes;
    this.basicTaxModifier = basicTaxModifier;
  }

  public void addReceipt(Receipt receipt) {
     for(int kind = 0; kind < receiptKinds.length; kind++) {
      if (receipt.getKind().equals(receiptKinds[kind])) {
        amountPerReceiptsKind[kind] += receipt.getAmount();
      }      
    }
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }

  public void deleteReceipt(int receiptId) {  
    Receipt receipt = receiptHashMap.get(receiptId);
     for(int kind = 0; kind < receiptKinds.length; kind++) {
      if (receipt.getKind().equals(receiptKinds[kind])) {
        amountPerReceiptsKind[kind] -= receipt.getAmount() ;
      }      
    }
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }
  
  public String getFullname() {
    return fullname;
  }
  
  public String getStatus() {
    return status;
  }

  public float getIncome() {
    return income;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }
   
  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }
  
  public float getAmountOfReceiptKind(int kind) {
    return amountPerReceiptsKind[kind];
  }  
  
  public double getTotalTax() {
    return getBasicTax() + getVariationTaxOnReceipts();
  }
  
  public double getBasicTax() {
    return calculateBasicTax();
  }
  
  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for(int taxLevel = (VARIATION_INCOME_PERCENTAGE.length - 1); taxLevel>0; taxLevel--){      
      if(totalAmountOfReceipts >= income * VARIATION_INCOME_PERCENTAGE[taxLevel])
        return calculateBasicTax() * VARIATION_TAX_MODIFIER[taxLevel];
    }
    return calculateBasicTax() * VARIATION_TAX_MODIFIER[0];
  }
  
  public float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int receiptKind = 0; receiptKind < amountPerReceiptsKind.length; receiptKind++){
      sum += amountPerReceiptsKind[receiptKind];
    }
    return sum;
  }
  
  public double calculateBasicTax() {
    for(int taxLevel = (minIncome.length - 1); taxLevel>0; taxLevel--) {
      if(income>=minIncome[taxLevel]) {
        return minTaxes[taxLevel] + 
               basicTaxModifier[taxLevel] * (income - minIncome[taxLevel]);
      }
    }
    return basicTaxModifier[0] * income;
  }  
}