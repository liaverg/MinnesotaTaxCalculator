package incometaxcalculator.data.management;

import java.time.Year;

import incometaxcalculator.exceptions.WrongReceiptDateException;

public class Receipt {
  private final int id;
  private final String issueDate;
  private final float amount;
  private final String kind;
  private final Company company;

  public Receipt(int id, String issueDate, float amount, String kind, 
                 String companyName, String country, String city, 
                 String street, int number) throws WrongReceiptDateException {
    this.id = id;
    this.issueDate = validateIssueDate(issueDate);
    this.amount = amount;
    this.kind = kind;
    this.company = new Company(companyName, country, city, street, number);
  }

  private String validateIssueDate(String issueDate) 
      throws WrongReceiptDateException {
    String[] token = issueDate.split("/");
    int[] dateContraints = {31, 12, Year.now().getValue()};
    if (token.length != 3) {
      throw new WrongReceiptDateException();
    }
    for (int i = 0; i < token.length; i++) {
      if (Integer.valueOf(token[i]) > dateContraints[i]) {
        throw new WrongReceiptDateException();
      }
    }
    return issueDate;
  }

  public int getId() {
    return id;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public float getAmount() {
    return amount;
  }

  public String getKind() {
    return kind;
  }

  public Company getCompany() {
    return company;
  }
  
  public String getCompanyName() {
    return company.getName();
  }
  
  public String getCompanyCountry() {
    return company.getCountry();
  }
  
  public String getCompanyCity() {
    return company.getCity();
  }
  
  public String getCompanyStreet() {
    return company.getStreet();
  }
  
  public int getCompanyNumber() {
    return company.getNumber();
  }
}