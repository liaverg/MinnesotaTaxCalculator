package incometaxcalculator.data.io;

public class TXTLogWriter extends LogWriter {
  private static String fileFormat = "_LOG.txt";
  private static final String[] PREFIXES = 
    {"Name: ", "AFM: ", "Income: ", "BasicTax: ", "TaxIncrease: ",
      "TaxDecrease: ", "TotalTax: ", "Receipts: ", 
      "Entertainment: ", "Basic: ", "Travel: ", "Health: ", "Other: "};
  private static final String[] SUFFIXES = 
    {"", "", "", "", "", "", "", "", "", "", "", "", ""};

  public TXTLogWriter() {
    super(fileFormat, PREFIXES, SUFFIXES);
  }

}
