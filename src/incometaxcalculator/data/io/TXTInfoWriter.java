package incometaxcalculator.data.io;

public class TXTInfoWriter extends InfoWriter {
  private static final String[] TAXPAYER_PREFIXES = 
    {"Name: ", "AFM: ", "Status: ", "Income: ", "Receipts:"};
  private static final String[] TAXPAYER_SUFFIXES = 
    {"", "", "", "", ""};
  private static final String[] RECEIPT_PREFIXES = 
    {"Receipt ID: ", "Date: ", "Kind: ", "Amount: " , "Company: ", 
        "Country: ", "City: ", "Street: " ,"Number: "};
  private static final String[] RECEIPT_SUFFIXES = 
    {"", "", "", "" , "","", "", "" ,""};


  public TXTInfoWriter() {
    super(TAXPAYER_PREFIXES, TAXPAYER_SUFFIXES, RECEIPT_PREFIXES, 
          RECEIPT_SUFFIXES);
  }


}