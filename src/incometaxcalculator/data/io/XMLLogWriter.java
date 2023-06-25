package incometaxcalculator.data.io;

public class XMLLogWriter extends LogWriter {
  private static String fileFormat = "_LOG.xml";
  private static final String[] PREFIXES = 
    {"<Name> ", "<AFM> ", "<Income> ", "<BasicTax> ", "<TaxIncrease> ",
      "<TaxDecrease> ", "<TotalTax> ", "<Receipts> ", 
      "<Entertainment> ", "<Basic> ", "<Travel> ", "<Health> ", "<Other> "};
  private static final String[] SUFFIXES = 
    {" </Name> ", " </AFM> ", " </Income> ", " </BasicTax> ", " </TaxIncrease> ",
      " </TaxDecrease> ", " </TotalTax> ", " </Receipts> ", 
      " </Entertainment> ", " </Basic> ", " </Travel> ", 
      " </Health> ", " </Other> "};

  public XMLLogWriter() {
    super(fileFormat, PREFIXES, SUFFIXES);
  }
}
